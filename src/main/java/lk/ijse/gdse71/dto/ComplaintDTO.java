package lk.ijse.gdse71.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/17/2025 10:32 PM
 * Project: CMS
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComplaintDTO {
    private String complaint_id;
    private String user_id;
    private String title;
    private String description;
    //private LocalDate date_submitted;
    private LocalDateTime date_submitted;
    private String status;
    private String admin_remarks;
}
