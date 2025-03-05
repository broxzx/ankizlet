package fyuizee.com.ankizletbe.service;

import fyuizee.com.ankizletbe.config.JwtUtil;
import fyuizee.com.ankizletbe.persistance.domain.users.enums.RegistrationSourceType;
import fyuizee.com.ankizletbe.persistance.domain.users.dto.UserLoginRequest;
import fyuizee.com.ankizletbe.persistance.domain.users.dto.UserRegisterRequest;
import fyuizee.com.ankizletbe.persistance.domain.users.UserEntity;
import fyuizee.com.ankizletbe.persistance.domain.users.enums.UserRole;
import fyuizee.com.ankizletbe.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public UserEntity register(UserRegisterRequest userRegisterRequest) {
        // todo: check if user already exists
        UserEntity userEntity = UserEntity.builder()
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .username(userRegisterRequest.getUsername())
                .password(this.encoder.encode(userRegisterRequest.getPassword()))
                .email(userRegisterRequest.getEmail())
                .registrationSourceType(Objects.nonNull(userRegisterRequest.getEmail()) ? RegistrationSourceType.WEB : RegistrationSourceType.TELEGRAM)
                .telegramChatId(null)
                .role(UserRole.USER)
                .build();
        return this.userRepository.save(userEntity);
    }

    public String validate(UserLoginRequest userLoginRequest) {

        String login = userLoginRequest.getLogin();
        UserEntity userEntity = isEmail(login)
                ? this.userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + login))
                : this.userRepository.findByUsername(login).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + login));

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userLoginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return this.jwtUtil.generateToken(userEntity.getUsername());
        }

        return "Fail";
    }

    private boolean isEmail(String input) {
        return EmailValidator.getInstance().isValid(input);
    }

}
