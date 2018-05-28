package carRentalApp.business.service.user;

import carRentalApp.business.dto.UserDTO;
import carRentalApp.data.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    List<User> getAll();
    @Transactional
    User createUser(UserDTO userDto);
    @Transactional
    void deleteUser(UserDTO userDto);

    User getUser(UserDTO userDto);

    @Transactional
    void updateUser(UserDTO userDto);

}
