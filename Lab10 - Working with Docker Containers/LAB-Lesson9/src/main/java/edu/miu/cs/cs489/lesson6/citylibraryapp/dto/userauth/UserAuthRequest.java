package edu.miu.cs.cs489.lesson6.citylibraryapp.dto.userauth;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest {
    @NotBlank(message = "Username cannot be null, empty or blankspace(s)")
    private String username;
    @NotBlank(message = "Password cannot be null, empty or blankspace(s)")
//    @Min(value = 8 characters")
    private String password;
}
