package fyuizee.com.ankizletbe.persistance.domain.users;

import fyuizee.com.ankizletbe.model.security.enums.RegistrationSourceType;
import fyuizee.com.ankizletbe.persistance.domain.collectionroleusers.CollectionRoleUsersEntity;
import fyuizee.com.ankizletbe.persistance.domain.users.enums.UserRole;
import fyuizee.com.ankizletbe.persistance.domain.wordcollections.WordCollectionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "registration_source_type", nullable = false)
    private RegistrationSourceType registrationSourceType;
    @Column(name = "telegram_chat_id", unique = true)
    private String telegramChatId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
