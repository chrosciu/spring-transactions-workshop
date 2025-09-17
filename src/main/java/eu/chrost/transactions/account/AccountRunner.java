package eu.chrost.transactions.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class AccountRunner implements CommandLineRunner {
    private final AccountOuterService accountOuterService;
    private final AccountIsolationService accountIsolationService;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
//        runCatching(() -> {
//            accountOuterService.saveRequired();
//            accountOuterService.saveRequiresNew();
//            accountOuterService.saveNested();
//        });
//        log.info("{}", accountRepository.findAll());

        accountRepository.save(Account.builder().owner("Alice").balance(100.0).build());
        accountRepository.save(Account.builder().owner("Bob").balance(200.0).build());

//        new Thread(() -> accountIsolationService.updateBalance(1L, 300.0)).start();
//        accountIsolationService.readBalanceReadUncommitted(1L);
//        accountIsolationService.readBalanceReadCommitted(1L);
//        accountIsolationService.readBalanceRepeatableRead(1L);

    }
}
