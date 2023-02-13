package cn.dpc.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Account {
    private AccountId id;

    private AccountDescription description;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class AccountId {
        private String id;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountDescription {
        private String name;

        private LocalDateTime createdAt;

        private LocalDateTime lastUpdatedAt;
    }
}
