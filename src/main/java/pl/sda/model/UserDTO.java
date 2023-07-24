package pl.sda.model;


import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private String password;

    private String matchingPassword;

}
