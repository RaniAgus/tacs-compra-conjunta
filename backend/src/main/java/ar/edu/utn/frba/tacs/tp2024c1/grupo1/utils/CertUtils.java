package ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils;

import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@UtilityClass
public class CertUtils {
    public static String getFingerprintFromFile(String path) {
        try (InputStream inStream = new FileInputStream(path)) {
            // Load the certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);

            // Get the SHA256 fingerprint
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] publicKey = md.digest(cert.getEncoded());

            // Convert the fingerprint to hex format
            return new BigInteger(1, publicKey).toString(16);
        } catch (IOException | CertificateException | NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Error reading certificate", e);
        }
    }
}
