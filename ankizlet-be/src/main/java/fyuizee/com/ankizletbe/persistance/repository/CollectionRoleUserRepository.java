package fyuizee.com.ankizletbe.persistance.repository;

import fyuizee.com.ankizletbe.persistance.domain.collectionroleusers.CollectionRoleUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRoleUserRepository extends JpaRepository<CollectionRoleUsersEntity, Integer> {
}
