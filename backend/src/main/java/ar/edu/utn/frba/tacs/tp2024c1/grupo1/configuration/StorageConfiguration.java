package ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.net.URI;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws.s3")
@Slf4j
public class StorageConfiguration {
    private String region;
    private String endpoint;
    private String publicEndpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .forcePathStyle(true)
                .build();
    }

    @Bean
    public CommandLineRunner checkBucket(S3Client s3) {
        return args -> {
            try {
                if (s3.listBuckets().buckets().stream().noneMatch(bucket -> bucket.name().equals(bucketName))) {
                    throw new IllegalStateException("Bucket '" + bucketName + "' not found");
                }
            } catch (S3Exception e) {
                log.warn("Could not list buckets: {}", e.getMessage());
            }
        };
    }
}
