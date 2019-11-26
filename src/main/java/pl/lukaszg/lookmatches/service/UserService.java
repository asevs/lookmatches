package pl.lukaszg.lookmatches.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.stereotype.Service;
import pl.lukaszg.lookmatches.model.User;
import pl.lukaszg.lookmatches.model.UserLoginType;
import pl.lukaszg.lookmatches.model.UserRepository;

import java.time.LocalDateTime;


@Service("userService")
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String principalId = (String) map.get("id");
            User user = userRepository.findByEmail(map.get("email"));
            if (user == null) {
                LOGGER.info("No user found, generating profile for {}", principalId);
                user = new User();
                user.setPrincipalId(principalId);
                user.setCreatedUserDate(LocalDateTime.now());
                user.setEmail((String) map.get("email"));
                user.setFullName((String) map.get("name"));
                user.setPhoto((String) map.get("picture"));
                user.setLoginType(UserLoginType.GOOGLE);
                user.setLastLoginDate(LocalDateTime.now());

            } else {
                user.setLastLoginDate(LocalDateTime.now());
            }
            userRepository.save(user);
            return user;
        };
    }

}
