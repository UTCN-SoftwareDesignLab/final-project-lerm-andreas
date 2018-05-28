package carRentalApp.business.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    @Size(min = 6, message = "The username must have at least 6 characters")
    private String userName;


    private String role;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "The password must have minimum eight characters, at least one upper case letter and one number")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
