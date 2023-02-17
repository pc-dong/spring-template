package cn.dpc.persistence;

import cn.dpc.domain.AbstractAccounts;
import cn.dpc.domain.Account;
import cn.dpc.persistence.db.AccountDB;
import cn.dpc.persistence.db.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountsImpl extends AbstractAccounts {
    private final AccountRepository repository;

    @Override
    protected Mono<Boolean> isExist(Account.AccountId id, String name) {
        return repository.countByNameAndIdNot(name,
                Optional.ofNullable(id).map(Account.AccountId::getId).orElse("0"))
                .map(count -> count > 0);
    }

    @Override
    protected Mono<Account> addRaw(Account account) {
        return repository.save(AccountDB.from(account))
                .map(AccountDB::to);
    }

    @Override
    protected Mono<Account> updateRaw(Account account) {
        return repository.save(AccountDB.from(account))
                .map(AccountDB::to);
    }

    @Override
    public Flux<Account> findAll() {
        return repository.findAll()
                .map(AccountDB::to);
    }
}
