package fyuizee.com.ankizletbe.persistance.domain.collectionroleusers;

import fyuizee.com.ankizletbe.persistance.domain.collectionroleusers.enums.CollectionUserRole;
import fyuizee.com.ankizletbe.persistance.domain.users.UserEntity;
import fyuizee.com.ankizletbe.persistance.domain.wordcollections.WordCollectionEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "collection_role_users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CollectionRoleUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_collection_id", nullable = false)
    private WordCollectionEntity wordCollectionEntity;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private CollectionUserRole collectionUserRole;
}
