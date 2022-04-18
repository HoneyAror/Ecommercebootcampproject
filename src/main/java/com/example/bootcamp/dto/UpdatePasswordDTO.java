package com.example.bootcamp.dto;


        import lombok.Data;
        import javax.validation.constraints.Pattern;
        import javax.validation.constraints.Size;

@Data
public class UpdatePasswordDTO {
    @Pattern(regexp = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%*!^&+=])" + "(?=\\S+$).{8,15}$",
            message = "Password must contain 8-15 Characters with atleast 1 Lower case, 1 Upper case, 1 Special Character, 1 Number")
    private String password;
    private String confirmPassword;
}
