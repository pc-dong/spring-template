package cn.dpc.persistence.db;

import cn.dpc.domain.Account;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Optional;

@Table("account")
@Data
public class AccountDB {

    @Version
    private long version;

    @Id
    private String id;

    @Column("name")
    private String name;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("last_updated_at")
    private LocalDateTime lastUpdatedAt;

    public static AccountDB from(Account account) {
        AccountDB db = new AccountDB();

        db.setId(Optional.ofNullable(account.getId()).map(Account.AccountId::getId).orElse(null));
        db.setName(account.getDescription().getName());
        db.setCreatedAt(account.getDescription().getCreatedAt());
        db.setLastUpdatedAt(account.getDescription().getLastUpdatedAt());
        return db;
    }

    public Account to() {
        return new Account(Account.AccountId.builder().id(this.getId()).build(),
                Account.AccountDescription.builder()
                        .name(this.getName())
                        .createdAt(this.getCreatedAt())
                        .lastUpdatedAt(this.getLastUpdatedAt())
                        .build());
    }
}
