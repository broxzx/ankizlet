package fyuizee.com.ankizletbe.persistance.repository;

import fyuizee.com.ankizletbe.persistance.domain.wordcollections.WordCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordCollectionRepository extends JpaRepository<WordCollectionEntity, UUID> {
}
