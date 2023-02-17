package cn.dpc.persistence.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataR2dbcTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository repository;

    @Test
    public void countByNameAndIdNot() {
        repository.countByNameAndIdNot("name", "0")
                .as(StepVerifier::create)
                .expectNext(0L)
                .verifyComplete();

        AccountDB accountDB = new AccountDB();
        accountDB.setId(UUID.randomUUID().toString());
        accountDB.setName("test");
        accountDB.setCreatedAt(LocalDateTime.now());
        accountDB.setLastUpdatedAt(LocalDateTime.now());

        repository.save(accountDB)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        repository.countByNameAndIdNot("test", "0")
                .as(StepVerifier::create)
                .expectNext(1L)
                .verifyComplete();
    }
}