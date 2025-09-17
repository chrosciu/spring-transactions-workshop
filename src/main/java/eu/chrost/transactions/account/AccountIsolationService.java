package eu.chrost.transactions.account;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountIsolationService {
    private final AccountRepository repository;
    private final EntityManager entityManager;

    // Transaction A: reads balance
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @SneakyThrows
    public void readBalanceReadUncommitted(Long accountId) {
        Thread.sleep(2000);
        log.info("[A] Loading account with id {}", accountId);
        Account account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
        Thread.sleep(2000);
        entityManager.detach(account);
        log.info("[A] Loading account with id {}", accountId);
        account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
    }

    // Transaction A: reads balance
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @SneakyThrows
    public void readBalanceReadCommitted(Long accountId) {
        Thread.sleep(2000);
        log.info("[A] Loading account with id {}", accountId);
        Account account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
        Thread.sleep(2000);
        entityManager.detach(account);
        log.info("[A] Loading account with id {}", accountId);
        account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
    }

    // Transaction A: reads balance
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @SneakyThrows
    public void readBalanceRepeatableRead(Long accountId) {
        Thread.sleep(2000);
        log.info("[A] Loading account with id {}", accountId);
        Account account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
        Thread.sleep(2000);
        entityManager.detach(account);
        log.info("[A] Loading account with id {}", accountId);
        account = repository.findById(accountId).orElseThrow();
        log.info("[A] Account: {}", account);
    }

    // Transaction B: updates balance
    @Transactional
    @SneakyThrows
    public void updateBalance(Long accountId, Double newBalance) {
        Thread.sleep(1000);
        log.info("[B] Loading account with id {}", accountId);
        Account acc = repository.findById(accountId).orElseThrow();
        log.info("[B] Updating balance of account {} to {}", accountId, newBalance);
        acc.setBalance(newBalance);
        repository.saveAndFlush(acc);
        Thread.sleep(2000);
//        if (1 == 1) {
//            throw new RuntimeException("[B] Rolling back transaction");
//        }
        log.info("[B] Committing transaction");
    }
}
