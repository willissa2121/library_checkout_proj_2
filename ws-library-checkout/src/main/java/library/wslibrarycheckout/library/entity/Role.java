package library.wslibrarycheckout.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Librarian librarian;

    @ManyToMany
    @JoinTable(
            name = "role_priv",
            joinColumns = @JoinColumn(name = "priv_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Privilege> privileges;
}
