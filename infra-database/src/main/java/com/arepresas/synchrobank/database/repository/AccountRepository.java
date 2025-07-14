package com.arepresas.synchrobank.database.repository;

import com.arepresas.synchrobank.database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByUserId(Long userId);
  Optional<Account> findByIban(String iban);
}
