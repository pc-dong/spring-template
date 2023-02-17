package cn.dpc.persistence.webclient;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockitoExtension.class)
class CustomerClientTest {

    WebClients webClients;

    MockServerClient mockServerClient;
    ClientAndServer clientAndServer;

    @BeforeEach
    @SneakyThrows
    public void init() {
        mockServerClient = new MockServerClient("127.0.0.1", 9000);
        clientAndServer = ClientAndServer.startClientAndServer(9000);
        webClients = new WebClients(WebClient.builder().baseUrl("http://127.0.0.1:9000").build());
    }

    @AfterEach
    public void destroy() {
        clientAndServer.stop();
    }

    @Test
    void getById() {
        String id = "customerId";
        String body = "{\"id\":\"" + id + "\",\"name\":\"TEST\"}";
        mockServerClient
                .when(request()
                        .withMethod("GET")
                        .withPath("/customers/" + id))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(Header.header("content-type", "application/json"))
                                .withBody(body));

        webClients.getCustomerClient().getById(id)
                .as(StepVerifier::create)
                .expectNextMatches(customer -> Objects.equals(customer.getName(), "TEST"))
                .verifyComplete();
    }
}