package bg.softuni.tabula.user;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;

  private final UserDetailsService userDetailsService;

  private final PasswordEncoder passwordEncoder;

  public boolean exists(String email) {
    Optional<UserEntity> userEntityOpt =
        userRepository.findOneByEmail(email);

    return userEntityOpt.isPresent();
  }

  public UserEntity getOrCreateUser(String email) {

    Optional<UserEntity> userEntityOpt =
        userRepository.findOneByEmail(email);

    return userEntityOpt.
        orElseGet(() -> createUser(email));
  }

  public void registerAndLoginUser(String userEmail, String userPassword) {
    UserEntity userEntity = createUser(userEmail, userPassword);

    UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

    Authentication authentication = new
        UsernamePasswordAuthenticationToken(
        userDetails,
        userEntity.getPasswordHash(),
        userDetails.getAuthorities()
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private UserEntity createUser(String userEmail, String userPassword) {
    LOGGER.info("Creating a new user with email [PROTECTED].");

    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(userEmail);
    if (userPassword != null) {
      userEntity.setPasswordHash(passwordEncoder.encode(userPassword));
    }

    RoleEntity userRole = new RoleEntity().setRole("ROLE_USER");
    userEntity.setRoles(List.of(userRole));

    return userRepository.save(userEntity);
  }

  private UserEntity createUser(String email) {
    return this.createUser(email, null);
  }
}
