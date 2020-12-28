package javaldz26.demo.miniblog26.services;

import javaldz26.demo.miniblog26.dao.UserDao;
import javaldz26.demo.miniblog26.dao.UserRepository;
import javaldz26.demo.miniblog26.dtos.RegisteredUserDto;
import javaldz26.demo.miniblog26.dtos.UserDetailsDto;
import javaldz26.demo.miniblog26.dtos.UserShortInfoDto;
import javaldz26.demo.miniblog26.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserDao userDao, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisteredUserDto registeredUser(String email, String nickname, String  password) {
        final User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));

//        userDao.save(user);

        userRepository.save(user);
        return null;
    }

    public List<UserShortInfoDto> getAllUsers() {
//    final Iterable<User> users = userRepository.findAll();
//    List<UserShortInfoDto> userDtos = new ArrayList<>();
//    for(User user : users) {
//        var dto = new UserShortInfoDto(user.getId(), user.getEmail(), user.getNickname());
//        userDtos.add(dto);
//    }
//    return userDtos;
        return userRepository.findAll().stream()
                .map(user -> new UserShortInfoDto(user.getId(), user.getEmail(), user.getNickname()))
                .collect(Collectors.toList());
    }


    public List<UserShortInfoDto> getUsersByEmail(String email) {
   return userRepository.findByEmail(email)
    .stream()
    .map(user -> new UserShortInfoDto(user.getId(), user.getEmail(), user.getNickname()))
    .collect(Collectors.toList());
    }

//    public UserDataDto getUsersById(Long id) {
//        return userRepository.findById(id)
//                .stream()
//                .map(user -> new UserDataDto(user.getEmail(), user.getNickname(), user.getCreated()))
//                .;
//    }

//    public Optional<UserDetailsDto> getUserDetails(Long userId) {
//    return userRepository.findById(userId)
//           .map(user -> new UserDetailsDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreated()));
//    }

//    public UserDetailsDto getUserDetails(Long userId) {
//        return userRepository.findById(userId)
//                .map(user -> new UserDetailsDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreated()))
//                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist for given id: " + userId));
//    }

    public Optional<UserDetailsDto> getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDetailsDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreated()));
//
    }

    public List<UserShortInfoDto> findByEmailContaining(String email) {
        return userRepository.findByEmailContaining(email)
                .stream()
                .map(user -> new UserShortInfoDto(user.getId(), user.getEmail(), user.getNickname()))
                .collect(Collectors.toList());

    }

}
