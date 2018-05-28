package carRentalApp.data.repository;


import carRentalApp.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);

    User findByUserNameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query("update User u set u.password= ?1, u.role= ?2 where u.userName = ?3")
    void updateUser(String password, String role, String userName);
}
