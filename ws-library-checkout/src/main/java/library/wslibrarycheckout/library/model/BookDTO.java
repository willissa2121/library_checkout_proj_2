package library.wslibrarycheckout.library.model;

import library.wslibrarycheckout.library.enumeration.Availabilty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Availabilty availability;

    private Date expectedReturnDate;

    private String authorFullName;
}
