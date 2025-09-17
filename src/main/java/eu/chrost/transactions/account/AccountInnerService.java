package eu.chrost.transactions.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AccountInnerService {
    private final AccountRepository accountRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRequired() {
        accountRepository.save(Account.builder().owner("Inner REQUIRED").balance(10.0).build());
        // Uncomment to test rollback
        throw new RuntimeException("Forcing rollback in REQUIRED");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRequiresNew() {
        accountRepository.save(Account.builder().owner("Inner REQUIRES_NEW").balance(20.0).build());
        // Uncomment to test rollback
        throw new RuntimeException("Forcing rollback in REQUIRES_NEW");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveNested() {
        accountRepository.save(Account.builder().owner("Inner NESTED").balance(30.0).build());
        // Uncomment to test rollback
        // throw new RuntimeException("Forcing rollback in NESTED");
    }
}
