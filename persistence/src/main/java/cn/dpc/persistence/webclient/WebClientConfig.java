package cn.dpc.persistence.webclient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final WebClients webClients;

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://localhost:7000")
                .build();
    }

    @SneakyThrows
    @Bean
    CustomerClient postClient() {
       return webClients.getCustomerClient();
    }
}
