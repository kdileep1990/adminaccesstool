package com.semanticbits.employee.search;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.semanticbits.employee.Employee;

@Repository("searchDao")
public class SearchDao {

    @Resource
    private AutoSuggestions autoSuggestions;
    @Resource
    private SessionFactory sessionFactory;
    private Employee[] employees;

    public AutoSuggestions getAutoSuggestions() {
        return autoSuggestions;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    // public void setAutoSuggestions(AutoSuggestions autoSuggestions) {
    // this.autoSuggestions = autoSuggestions;
    // }
    public void generateAutoSuggestions() {
        Session session = sessionFactory.openSession();
        String hql = "SELECT E.firstname,E.lastname FROM Employee E";
        System.out.println(hql);
        Query query = session.createQuery(hql);
        List results = query.list();
//		Iterator iterator = results.iterator();
        String[] firstNames = new String[results.size()];
        String[] lastNames = new String[results.size()];
        String[] roles = new String[results.size()];
        for (int iterator = 0; iterator < results.size(); iterator++) {
            Object[] row = (Object[]) results.get(iterator);
            firstNames[iterator] = (String) row[0];
            lastNames[iterator] = (String) row[1];
        }
        autoSuggestions.setFirstNames(firstNames);
        autoSuggestions.setLastNames(lastNames);
    }

    public void searchEmployeeDetails(String firstName, String lastName, String role, String userType) {
        Session session = sessionFactory.openSession();
        String hql="";
        if (!role.equals("")) {
            hql = "Select DISTINCT E FROM Employee E, Userroles UR, Roles R where E.emailid=UR.id.emailid and UR.id.roleid=R.roleid and ";
            if (!firstName.equals("")) {
                hql = hql + "E.firstname='" + firstName + "' and ";
            }
            if (!lastName.equals("")) {
                hql = hql + "E.lastname='" + lastName + "' and ";
            }
            if (!role.equals("")) {
                hql = hql + "R.role='" + role + "' and ";
            }
        }
        else {
            hql = "Select DISTINCT E FROM Employee E where ";
            if (!firstName.equals("")) {
                hql = hql + "E.firstname='" + firstName + "' and ";
            }
            if (!lastName.equals("")) {
                hql = hql + "E.lastname='" + lastName + "' and ";
            }
            if (!role.equals("")) {
                hql = hql + "R.role='" + role + "' and ";
            }
        }
        hql = hql + "E.usertype='" + userType + "'";
        System.out.println(hql);
        Query query = session.createQuery(hql);
        List results = query.list();
        employees = new Employee[results.size()];
        for (int iterator = 0; iterator < results.size(); iterator++) {
            employees[iterator] = (Employee) results.get(iterator);
            employees[iterator].setPassword("");
            employees[iterator].setRoleses(null);
            System.out.println(employees[iterator].getEmailid());
        }
    }
}