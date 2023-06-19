package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.UsersDataValidation.ValidPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidPassword
public class User {

    private String name;

    private String password;

    private String matchingPassword;


}
