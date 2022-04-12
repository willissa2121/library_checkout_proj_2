package library.wslibrarycheckout.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Librarian {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "librarian")
    private List<Role> roles;

    @OneToMany(mappedBy = "librarian")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private Registration registration;

}
