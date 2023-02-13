package cn.dpc.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Accounts {
    Flux<Account> findAll();

    Mono<Account> add(Account account);

    Mono<Account> update(Account account);
}
