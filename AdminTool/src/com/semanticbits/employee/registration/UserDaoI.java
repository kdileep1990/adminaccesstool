package com.semanticbits.employee.registration;

import java.util.List;

import com.semanticbits.employee.Employee;

public interface UserDaoI {
	public boolean saveUserInfo(Employee user);
	public boolean isAuthenticated(Employee user);
	public List getRoles(String firstName);
	public boolean assignRoles(String []role,String firstName);
	public List getAssignedRoleId(String firstName);
}
