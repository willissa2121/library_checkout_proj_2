package library.wslibrarycheckout.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;

    @OneToMany(mappedBy="student")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private Registration registration;



}
