package com.semanticbits.employee;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="userroles"
    ,catalog="employeedetails"
)
public class Userroles  implements java.io.Serializable {


     private UserrolesId id;

    public Userroles() {
    }

    public Userroles(UserrolesId id) {
       this.id = id;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="emailid", column=@Column(name="emailid", length=30) ), 
        @AttributeOverride(name="roleid", column=@Column(name="roleid") ) } )
    public UserrolesId getId() {
        return this.id;
    }
    
    public void setId(UserrolesId id) {
        this.id = id;
    }




}


