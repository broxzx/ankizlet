package fyuizee.com.ankizletbe.persistance.repository;

import fyuizee.com.ankizletbe.persistance.domain.words.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordRepository extends JpaRepository<WordEntity, UUID> {
}
