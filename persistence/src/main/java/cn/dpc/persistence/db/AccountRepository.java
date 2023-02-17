package cn.dpc.persistence.db;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountDB, String> {
    Mono<Long> countByNameAndIdNot(String name, String id);
}
