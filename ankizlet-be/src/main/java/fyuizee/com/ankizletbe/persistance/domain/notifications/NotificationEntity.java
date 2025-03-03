package fyuizee.com.ankizletbe.persistance.domain.notifications;

import fyuizee.com.ankizletbe.persistance.domain.notifications.enums.EventType;
import fyuizee.com.ankizletbe.persistance.domain.users.UserEntity;
import fyuizee.com.ankizletbe.persistance.domain.wordcollections.WordCollectionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "notifications", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "word_collection_id")
    private WordCollectionEntity wordCollectionEntity;

}
