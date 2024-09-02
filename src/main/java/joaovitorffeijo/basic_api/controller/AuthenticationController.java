package joaovitorffeijo.basic_api.controller;

import joaovitorffeijo.basic_api.model.common.APIResponse;
import joaovitorffeijo.basic_api.model.user.AuthenticationDTO;
import joaovitorffeijo.basic_api.model.user.RegisterDTO;
import joaovitorffeijo.basic_api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public APIResponse<Void> login(@RequestBody AuthenticationDTO dto) {
        return authenticationService.login(dto);
    }

    @PostMapping("/register")
    public APIResponse<Void> register(@RequestBody RegisterDTO dto) {
        return authenticationService.register(dto);
    }
}
