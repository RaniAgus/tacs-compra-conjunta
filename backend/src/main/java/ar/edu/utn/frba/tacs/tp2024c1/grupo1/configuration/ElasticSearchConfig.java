package ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils.CertUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.lang.NonNull;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {
    @Value("${spring.elasticsearch.uris}")
    private String uri;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Value("${spring.elasticsearch.ca-cert-path}")
    private String caCertPath;

    @Override
    @NonNull
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(uri)
                .usingSsl(CertUtils.getFingerprintFromFile(caCertPath))
                .withBasicAuth(username, password)
                .build();
    }
}
