package ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CertUtilsTest {
    @Test
    void testGetFingerprintFromFile() {
        assertThat(CertUtils.getFingerprintFromFile("src/test/resources/certificate.crt"))
                .isEqualTo("29abcfaab648bdfd2d5bead67c54da7a7a92e5ddab90f44014e44aa225fc13a8");
    }
}
