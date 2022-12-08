package pl.uz.domian.user;

import com.wf.captcha.SpecCaptcha;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.uz.domian.user.dto.UserCredentialsDto;
import pl.uz.domian.user.dto.UserRegistrationDto;

import java.util.Optional;

@Service
public class UserService {
    private static final String DEFAULT_USER_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email).map(UserCredentialsDtoMapper::map);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //    podczas rejestracji nowy uzytkownik dostanie rolę USER
    @Transactional
    public void registerUserWithDefaultRole(UserRegistrationDto userRegistration) {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.getRoles().add(defaultRole);
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setStreet(userRegistration.getStreet());
        user.setHouseNumber(userRegistration.getHouseNumber());
        user.setCity(userRegistration.getCity());
        user.setCity(userRegistration.getCity());
        user.setZip(userRegistration.getZip());

        SpecCaptcha captcha = new SpecCaptcha(130, 48);
        captcha.text();
        
        userRepository.save(user);
    }

    public Optional<User> getCurrentlyLoggedInUser(Authentication authentication) {
        if (authentication == null){
            return Optional.empty();
        }
        String currentUserEmail = authentication.getName();
        return findByEmail(currentUserEmail);
    }
}