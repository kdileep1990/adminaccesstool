package com.semanticbits.employee.roles;

import com.semanticbits.employee.Employee;
import com.semanticbits.employee.Roles;
import com.semanticbits.employee.registration.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignRoleController {

    @Resource
    UserDaoImpl userDao;

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

//    @RequestMapping(value = "assignRole.html", method = RequestMethod.GET)
//    public String showForm(ModelMap model) {
//        Employee user = new Employee();
//        System.out.println("controller");
//        model.addAttribute("user", user);
//        return "assignRoles";
//    }
    @RequestMapping(value = "roles.html", method = RequestMethod.POST)
    public void processForm(@RequestParam("emailid") String email, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("assign role");
        List assignedRolesList = userDao.getAssignedRoles(email);
//        System.out.println("Assigned List:" + assignedRolesList);
//        List<Roles> rolesList = userDao.getRoles(email);
//        request.getSession().setAttribute("roles", rolesList);

//        request.getSession().setAttribute("assignedRoles", assignedRolesMap);
//			          	request.getRequestDispatcher("roles.jsp").forward(request, re)
        JSONArray jSONArray = JSONArray.fromObject(assignedRolesList);
        PrintWriter writer = response.getWriter();
        System.out.println(jSONArray.toString());
        writer.write(jSONArray.toString());
    }

    @RequestMapping(value = "assign.html", method = RequestMethod.POST)
    public void assignRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean flag = userDao.assignRoles(request.getParameter("roles").split(","), request.getParameter("emailid"));
        PrintWriter writer = response.getWriter();
        if (flag) {
            //response.setContentType("text/html");
//    	    		response.getWriter().print("Roles updated successfully");
//	    	    		request.getRequestDispatcher("roles.jsp").include(request, response);

            writer.write("updated successfully");
        } else {
            writer.write("updated failure");
        }
    }
}
