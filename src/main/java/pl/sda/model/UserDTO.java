package pl.sda.model;


import lombok.Data;
import pl.sda.UsersDataValidation.ValidPassword;

@Data
@ValidPassword
public class UserDTO {

    private String name;

    private String password;

    private String matchingPassword;

}
