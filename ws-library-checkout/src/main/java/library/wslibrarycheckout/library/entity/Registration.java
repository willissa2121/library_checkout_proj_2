package library.wslibrarycheckout.library.entity;

import javax.persistence.*;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String email;

    @OneToOne(mappedBy = "registration")
    private Librarian librarian;

    @OneToOne(mappedBy = "registration")
    private Student student;
}
