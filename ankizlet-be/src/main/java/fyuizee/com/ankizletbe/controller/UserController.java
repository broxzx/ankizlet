package fyuizee.com.ankizletbe.controller;

import fyuizee.com.ankizletbe.persistance.domain.users.dto.UserLoginRequest;
import fyuizee.com.ankizletbe.persistance.domain.users.dto.UserRegisterRequest;
import fyuizee.com.ankizletbe.persistance.domain.users.UserEntity;
import fyuizee.com.ankizletbe.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserEntity register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return this.userService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return this.userService.validate(userLoginRequest);
    }

}
