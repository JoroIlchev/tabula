package bg.softuni.tabula.user;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity getOrCreateUser(String email) {

    Optional<UserEntity> userEntityOpt =
        userRepository.findOneByEmail(email);

    return userEntityOpt.
        orElseGet(() -> createUser(email));
  }

  private UserEntity createUser(String email) {

    LOGGER.info("Creating a new user with email [PROTECTED].");

    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(email);
    return userRepository.save(userEntity);
  }
}
