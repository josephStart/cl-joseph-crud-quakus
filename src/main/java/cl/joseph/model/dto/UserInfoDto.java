package cl.joseph.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoDto {

    private String name;
    private String lastName;
    private String email;
    private Integer age;

}
