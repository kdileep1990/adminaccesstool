package com.semanticbits.employee;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserrolesId  implements java.io.Serializable {


     private String emailid;
     private Integer roleid;

    public UserrolesId() {
    }

    public UserrolesId(String emailid, Integer roleid) {
       this.emailid = emailid;
       this.roleid = roleid;
    }
   

    @Column(name="emailid", length=30)
    public String getEmailid() {
        return this.emailid;
    }
    
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    @Column(name="roleid")
    public Integer getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserrolesId) ) return false;
		 UserrolesId castOther = ( UserrolesId ) other; 
         
		 return ( (this.getEmailid()==castOther.getEmailid()) || ( this.getEmailid()!=null && castOther.getEmailid()!=null && this.getEmailid().equals(castOther.getEmailid()) ) )
 && ( (this.getRoleid()==castOther.getRoleid()) || ( this.getRoleid()!=null && castOther.getRoleid()!=null && this.getRoleid().equals(castOther.getRoleid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEmailid() == null ? 0 : this.getEmailid().hashCode() );
         result = 37 * result + ( getRoleid() == null ? 0 : this.getRoleid().hashCode() );
         return result;
   }   


}


