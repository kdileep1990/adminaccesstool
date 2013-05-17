package com.semanticbits.employee.registration;

import com.semanticbits.employee.Employee;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("register.html")
public class RegistrationController {

    @Resource
    private UserDaoImpl userDao;
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
    

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        Employee user = new Employee();
        System.out.println("controller");
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "user") Employee user, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result.hasErrors()) {
            System.out.println("sfdnks");
            return "registration";
        } else {
            String view = "registration";
            boolean flag = userDao.saveUserInfo(user);
            if (flag) {
                
//                view = "assignRoles";
//                request.getRequestDispatcher("assignRoles").forward(request, response);
                response.sendRedirect("manage.html");
            }
            else
            {
//                request.setAttribute("msg", "Email Id already exists");
//                result.addError(new ObjectError("emailid", "emailid already exists"));
                result.rejectValue("emailid", "field.required", "An account already exists for this email.");
                return "registration";
            }
            System.out.println("User values is : " + user.getEmailid());
            return view;
        }
    }
}
