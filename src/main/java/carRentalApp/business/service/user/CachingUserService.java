package carRentalApp.business.service.user;

import carRentalApp.business.dto.UserDTO;
import carRentalApp.data.entity.User;
import org.hibernate.boot.model.Caching;

import java.util.List;

public class CachingUserService implements UserService {

    private UserService origin;

    public CachingUserService(UserService origin){
        this.origin=origin;
    }

    @Override
    public List<User> getAll() {
        return origin.getAll();
    }

    @Override
    public User createUser(UserDTO userDto) {
        return origin.createUser(userDto);
    }

    @Override
    public void deleteUser(UserDTO userDto) {
        origin.deleteUser(userDto);
    }

    @Override
    public User getUser(UserDTO userDto) {
        return origin.getUser(userDto);
    }

    @Override
    public void updateUser(UserDTO userDto) {
        origin.updateUser(userDto);
    }
}
