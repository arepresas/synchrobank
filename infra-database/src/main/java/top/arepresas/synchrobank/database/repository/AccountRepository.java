package top.arepresas.synchrobank.database.repository;

import top.arepresas.synchrobank.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  List<AccountEntity> findByUserId(Long userId);
  Optional<AccountEntity> findByIban(String iban);
}
