package cn.dpc.api.dto;

import cn.dpc.domain.Account;
import lombok.Data;

@Data
public class AccountResponse {
    private String id;

    private Account.AccountDescription description;

    public static AccountResponse from(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId().getId());
        accountResponse.setDescription(account.getDescription());
        return accountResponse;
    }
}
