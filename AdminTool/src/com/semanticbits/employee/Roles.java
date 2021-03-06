package com.semanticbits.employee;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="roles"
    ,catalog="employeedetails"
)
public class Roles  implements java.io.Serializable {


     private Integer roleid;
     private String role;
     private Set<Employee> employees = new HashSet<Employee>(0);

    public Roles() {
    }

    public Roles(String role, Set<Employee> employees) {
       this.role = role;
       this.employees = employees;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="roleid", unique=true, nullable=false)
    public Integer getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    @Column(name="role", length=30)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="roleses")
    public Set<Employee> getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }




}


