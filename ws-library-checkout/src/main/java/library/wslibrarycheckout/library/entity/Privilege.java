package library.wslibrarycheckout.library.entity;



import library.wslibrarycheckout.library.enumeration.PrivilegeType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private PrivilegeType privilegeType;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
