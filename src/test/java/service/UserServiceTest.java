package service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

    UserService1 userService;

    @Mock
    UserRepository1 userRepository;

    @Before
    public void setup(){
        userService = new UserServiceImpl1(userRepository);
        List<User1> users = new ArrayList<User1>();

        User1 user = new User1("userName","password123","user");
        users.add(user);
        Mockito.when(userRepository.findByUserName("userName")).thenReturn(user);
    }

    @Test
    public void getAllUsers(){
        Assert.assertTrue(userService.getAll().size()==0);
    }

    @Test
    public void getByUserName(){
       // UserDto1 user = new UserDto1("userName","password123","user");
        UserDto1 userDto1 = new UserDto1();
        userDto1.setUserName("userName");
        userDto1.setPassword("password123");
        userDto1.setRole("user");
        User1 user1 =userService.getUser(userDto1);

        Assert.assertTrue(user1.getUserName().equals("userName"));
    }
}
