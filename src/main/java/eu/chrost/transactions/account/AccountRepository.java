package eu.chrost.transactions.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends JpaRepository<Account, Long> {}
