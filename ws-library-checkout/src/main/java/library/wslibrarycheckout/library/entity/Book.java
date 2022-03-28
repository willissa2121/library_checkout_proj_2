package library.wslibrarycheckout.library.entity;


import library.wslibrarycheckout.library.enumeration.Availabilty;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
@Data
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String authorEmail;
    private String edition;
    @Enumerated(EnumType.STRING)
    private Availabilty availabilty;
    private Date expectedReturnDate;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false)
    private Author author;

}
