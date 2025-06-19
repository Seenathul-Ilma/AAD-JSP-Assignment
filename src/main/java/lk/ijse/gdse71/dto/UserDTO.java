package lk.ijse.gdse71.dto;

import lombok.*;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/20/2025 1:28 AM
 * Project: CMS
 * --------------------------------------------
 **/


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;
}
