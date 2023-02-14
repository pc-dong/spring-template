package cn.dpc.main;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void should_createAccount_success() {
        Account account = new Account(Account.AccountId.builder().id(UUID.randomUUID().toString()).build(), Account.AccountDescription.builder()
                .name("1111")
                .createdAt(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .build());

        assertEquals("1111", account.getDescription().getName());
    }
}