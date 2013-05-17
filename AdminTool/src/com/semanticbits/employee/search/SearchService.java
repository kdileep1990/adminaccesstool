package com.semanticbits.employee.search;

import com.semanticbits.employee.Employee;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("searchService")
public class SearchService {

    @Resource
    private SearchDao searchDao;
    @Resource
    private AutoSuggestions autoSuggestions;
    private Employee[] employees;

    public AutoSuggestions getAutoSuggestions() {
        return autoSuggestions;
    }

    public void retrieveAutoSuggestions() {
        searchDao.generateAutoSuggestions();
        autoSuggestions = searchDao.getAutoSuggestions();
    }

    public void retrieveEmployeeDetails(String firstName,String lastName,String role,String userType) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        searchDao.searchEmployeeDetails(firstName, lastName, role, userType);
        employees=searchDao.getEmployees();
        
    }

    public Employee[] getEmployees() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return employees;
    }
}
