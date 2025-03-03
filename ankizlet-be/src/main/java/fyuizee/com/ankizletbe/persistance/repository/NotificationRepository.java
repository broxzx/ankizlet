package fyuizee.com.ankizletbe.persistance.repository;

import fyuizee.com.ankizletbe.persistance.domain.notifications.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
