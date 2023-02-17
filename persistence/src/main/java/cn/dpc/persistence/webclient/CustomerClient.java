package cn.dpc.persistence.webclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/customers")
public interface CustomerClient {

    @GetExchange("/{id}")
    Mono<Customer> getById(@PathVariable("id") String id);
}
