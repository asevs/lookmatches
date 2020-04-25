package pl.lukaszg.lookmatches.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lukaszg.lookmatches.model.*;

import java.time.LocalDateTime;
import java.util.*;


@Service("userService")
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);
    BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    TeamRepository teamRepository;
    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;


    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String principalId = (String) map.get("sub");
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

                Role role = roleRepository.findByRole("ROLE_USER");
                user.setRoles(new HashSet<Role>(Arrays.asList(role)));

                Skill skill = skillRepository.findBySkill("SKILL_AMATEUR");
                user.setSkills(new HashSet<Skill>(Arrays.asList(skill)));
            } else {
                user.setLastLoginDate(LocalDateTime.now());
            }
            userRepository.save(user);
            return user;
        };
    }

    public User addBasicUser(User user) {

        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            user.setLoginType(UserLoginType.BASIC);
            user.setCreatedUserDate(localDateTime);
            user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();
            user1.setLastLoginDate(localDateTime);
            user1.setLoginType(UserLoginType.BASIC);
            user1.setPassword(bcryptPasswordEncoder.encode(user1.getPassword()));
            return userRepository.save(user1);

        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // TODO: 13.04.2020  sprawdzic czy user jest w bazie i czy pw dobre

    public String login(User user) {
        if (user.getEmail() == null) {
            return "User is null";
        } else if (checkUser(user)) {


            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("roles", user.getRoles())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 20000))
                    .signWith(SignatureAlgorithm.HS512, "aRA1+y%1*V>i/x")
                    .compact();
        }
        return "Wrong email or password";
    }

    public Boolean checkUser(User user) {
        User checkerUser = userRepository.findByEmail(user.getEmail());
        return bcryptPasswordEncoder.matches(user.getPassword(), checkerUser.getPassword());

    }

    public String addUserToTeamById(Long teamId, Long userId) {
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<User> user = userRepository.findById(userId);

        if (team.isPresent() && user.isPresent()) {
            if (team.get().getSlots() > team.get().getUsers().size()) {
                user.get().setTeam(team.get());
                userRepository.save(user.get());
                return "added user";
            } else return "max users";
        } else return "not added user";
    }

    public String addUserToRoomById(Long roomId, Long userId, Long teamId) {
        Optional<Room> room = roomRepository.findById(roomId);
        Optional<User> user = userRepository.findById(userId);


        if (room.isPresent() && user.isPresent()) {
            if (room.get().getSlots() > room.get().getUsers().size()) {
                List<Room> rooms = user.get().getRooms();
                rooms.add(room.get());
                user.get().setRooms(rooms);
                userRepository.save(user.get());
                return "added user";
            } else return "max users";

        } else return "not added user";
    }
}
