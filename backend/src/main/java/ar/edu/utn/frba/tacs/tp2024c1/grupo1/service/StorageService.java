package ar.edu.utn.frba.tacs.tp2024c1.grupo1.service;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration.StorageConfiguration;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Imagen;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageConfiguration storageConfiguration;

    private final S3Client s3Client;

    public Imagen guardarImagen(String base64String) {
        var parts = base64String.replace("data:image/", "").split(";base64,");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid base64 string");
        }

        var key = UUID.randomUUID() + "." + parts[0];
        var contentType = "image/" + parts[0];
        var content = Base64.decodeBase64(parts[1]);

        s3Client.putObject(r -> r
                        .bucket(storageConfiguration.getBucketName())
                        .key(key)
                        .contentType(contentType),
                RequestBody.fromBytes(content)
        );

        return Imagen.builder()
                .bucketName(storageConfiguration.getBucketName())
                .key(key)
                .contentType(contentType)
                .build();
    }

    public void borrarImagen(Imagen imagen) {
        s3Client.deleteObject(r -> r
                .bucket(imagen.bucketName())
                .key(imagen.key())
        );
    }
}
