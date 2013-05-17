package com.semanticbits.employee;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee"
    ,catalog="employeedetails"
)
public class Employee  implements java.io.Serializable {


     private String emailid;
     private String password;
     private String firstname;
     private String lastname;
     private String usertype;
     private Double salary;
     private Set<Roles> roleses = new HashSet<Roles>(0);

    public Employee() {
    }

	
    public Employee(String emailid) {
        this.emailid = emailid;
    }
    public Employee(String emailid, String password, String firstname, String lastname, String usertype, Double salary, Set<Roles> roleses) {
       this.emailid = emailid;
       this.password = password;
       this.firstname = firstname;
       this.lastname = lastname;
       this.usertype = usertype;
       this.salary = salary;
       this.roleses = roleses;
    }
   
     @Id 
    
    @Column(name="emailid", unique=true, nullable=false, length=30)
    public String getEmailid() {
        return this.emailid;
    }
    
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    
    @Column(name="password", length=30)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="firstname", length=30)
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    @Column(name="lastname", length=30)
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    @Column(name="usertype", length=30)
    public String getUsertype() {
        return this.usertype;
    }
    
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    
    @Column(name="salary", precision=22, scale=0)
    public Double getSalary() {
        return this.salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="userroles", catalog="employeedetails", joinColumns = { 
        @JoinColumn(name="emailid", updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="roleid", updatable=false) })
    public Set<Roles> getRoleses() {
        return this.roleses;
    }
    
    public void setRoleses(Set<Roles> roleses) {
        this.roleses = roleses;
    }




}


