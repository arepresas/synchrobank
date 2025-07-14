package com.arepresas.synchrobank.database.repository;

import com.arepresas.synchrobank.database.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
