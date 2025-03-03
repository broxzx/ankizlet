package fyuizee.com.ankizletbe.persistance.domain.words;

import fyuizee.com.ankizletbe.persistance.domain.users.UserEntity;
import fyuizee.com.ankizletbe.persistance.domain.wordcollections.WordCollectionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "words", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(name = "front")
    private String front;
    @Column(name = "back")
    private String back;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createAt;
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
    @Column(name = "repeat_at")
    private Instant repeatAt;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_collection_id", nullable = false)
    private WordCollectionEntity wordCollectionEntity;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}
