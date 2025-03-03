package fyuizee.com.ankizletbe.persistance.repository;

import fyuizee.com.ankizletbe.persistance.domain.daystreaks.DayStreakEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayStreakRepository extends JpaRepository<DayStreakEntity, Integer> {
}
