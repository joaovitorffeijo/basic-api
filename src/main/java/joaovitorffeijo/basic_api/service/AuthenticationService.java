package joaovitorffeijo.basic_api.service;

import joaovitorffeijo.basic_api.config.Constants;
import joaovitorffeijo.basic_api.model.common.APIResponse;
import joaovitorffeijo.basic_api.model.user.User;
import joaovitorffeijo.basic_api.model.user.AuthenticationDTO;
import joaovitorffeijo.basic_api.model.user.RegisterDTO;
import joaovitorffeijo.basic_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public APIResponse<Void> login(AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new APIResponse<>(HttpStatus.OK.value(), "User successfully logged", Constants.TOKEN_PREFIX + token);
    }

    public APIResponse<Void> register(RegisterDTO dto) {
        if (this.userRepository.findByLogin(dto.login()) != null) {
            return new APIResponse<>(HttpStatus.BAD_REQUEST.value(), "User already registered", null);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User(dto.name(), dto.login(), encryptedPassword, dto.role());

        return new APIResponse<>(HttpStatus.OK.value(), "User successfully created", this.userRepository.save(user));
    }
}
