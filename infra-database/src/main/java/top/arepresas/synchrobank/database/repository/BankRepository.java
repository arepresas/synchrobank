package top.arepresas.synchrobank.database.repository;

import top.arepresas.synchrobank.database.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
}
