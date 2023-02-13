package cn.dpc.domain;

import cn.dpc.domain.exceptions.DuplicatedRecordException;
import reactor.core.publisher.Mono;

public abstract class AbstractAccounts implements Accounts {
    @Override
    public Mono<Account> add(Account account) {
        return isExist(account.getId(), account.getDescription().getName())
                .flatMap(exist -> !exist
                        ? Mono.error(new DuplicatedRecordException(account.getId().getId(), Account.class))
                        : Mono.just(true))
                .flatMap(ignore -> addRaw(account));
    }

    @Override
    public Mono<Account> update(Account account) {
        return null;
    }

    protected abstract Mono<Boolean> isExist(Account.AccountId id, String name);

    protected abstract Mono<Account> addRaw(Account account);

    protected abstract Mono<Account> updateRaw(Account account);
}
