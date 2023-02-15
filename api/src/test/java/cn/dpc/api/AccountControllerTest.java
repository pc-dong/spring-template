package cn.dpc.api;

import cn.dpc.api.dto.AccountResponse;
import cn.dpc.domain.Account;
import cn.dpc.domain.Accounts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(value = AccountController.class, properties = "spring.main.lazy-initialization=true")
@ContextConfiguration(classes = TestConfiguration.class)
class AccountControllerTest {

    @Autowired
    WebTestClient webClient;

    @MockBean
    Accounts accounts;

    @Test
    public void should_add_new_configuration_success() {
        when(accounts.findAll()).thenReturn(Flux.just(new Account(Account.AccountId.builder().id(UUID.randomUUID().toString()).build(), new Account.AccountDescription())));
        webClient.get()
                .uri("/accounts")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(AccountResponse.class)
                .value(response -> {
                    assertTrue(response.get(0).getId().length() > 0);
                });
    }
}