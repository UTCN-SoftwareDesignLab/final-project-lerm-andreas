package carRentalApp.business.service.user;

import carRentalApp.business.dto.UserDTO;
import carRentalApp.business.service.user.UserService;
import carRentalApp.data.entity.User;
import carRentalApp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDto) {
       // BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        User user = new User(userDto.getUserName(),userDto.getPassword(),userDto.getRole());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UserDTO userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
        userRepository.delete(user);
    }

    @Override
    public User getUser(UserDTO userDto) {
        return userRepository.findByUserName(userDto.getUserName());
    }

    @Override
    public void updateUser(UserDTO userDto) {
        userRepository.updateUser(userDto.getPassword(),userDto.getRole(),userDto.getUserName());
    }
}
