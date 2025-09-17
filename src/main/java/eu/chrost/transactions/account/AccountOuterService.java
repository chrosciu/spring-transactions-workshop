package eu.chrost.transactions.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static eu.chrost.transactions.common.Utils.runCatching;

@Service
@RequiredArgsConstructor
class AccountOuterService {
    private final AccountRepository accountRepository;
    private final AccountInnerService accountInnerService;

    @Transactional
    public void saveRequired() {
        accountRepository.save(Account.builder().owner("Outer REQUIRED").balance(50.0).build());
        runCatching(() -> accountInnerService.saveRequired());

    }

    @Transactional
    public void saveRequiresNew() {
        accountRepository.save(Account.builder().owner("Outer REQUIRES_NEW").balance(60.0).build());
        runCatching(() -> accountInnerService.saveRequiresNew());
    }

    @Transactional
    public void saveNested() {
        accountRepository.save(Account.builder().owner("Outer NESTED").balance(70.0).build());
        runCatching(() -> accountInnerService.saveNested());
    }
}
