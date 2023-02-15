package cn.dpc.api;

import cn.dpc.api.dto.AccountResponse;
import cn.dpc.domain.Account;
import cn.dpc.domain.Accounts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    private final Accounts accounts;

    @GetMapping()
    public Flux<AccountResponse> findAll() {
        return accounts.findAll()
                .map(AccountResponse::from);
    }
}
