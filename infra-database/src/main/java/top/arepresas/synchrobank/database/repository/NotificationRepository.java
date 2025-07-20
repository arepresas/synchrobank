package top.arepresas.synchrobank.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.arepresas.synchrobank.database.entity.NotificationEntity;
import top.arepresas.synchrobank.database.entity.NotificationStatus;
import top.arepresas.synchrobank.database.entity.NotificationType;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
  List<NotificationEntity> findByStatus(NotificationStatus status);

  List<NotificationEntity> findAllByStatusAndType(NotificationStatus status, NotificationType type);
}