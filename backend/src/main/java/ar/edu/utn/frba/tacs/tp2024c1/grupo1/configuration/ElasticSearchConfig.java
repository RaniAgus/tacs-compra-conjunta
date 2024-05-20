package ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .usingSsl("dcf8253e9a6e405714cc8e2de3eee44ee270cb925f58e841aadfc7ebc7dec260") //add the generated sha-256 fingerprint
                .withBasicAuth("elastic", "HXWMKmJk1DcN+AWYvS6f") //add your username and password
                .build();
    }
}
