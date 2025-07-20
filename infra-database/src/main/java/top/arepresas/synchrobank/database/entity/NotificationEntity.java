package top.arepresas.synchrobank.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime modifiedAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private NotificationType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private NotificationStatus status;

  private String message;

  public void isSent() {
    this.setModifiedAt(LocalDateTime.now());
    this.status = NotificationStatus.SENT;
  }

  public void isFailed() {
    this.setModifiedAt(LocalDateTime.now());
    this.status = NotificationStatus.FAILED;
  }
}
