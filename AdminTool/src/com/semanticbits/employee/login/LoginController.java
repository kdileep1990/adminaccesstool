package com.semanticbits.employee.login;

import com.semanticbits.employee.Employee;
import com.semanticbits.employee.registration.UserDaoImpl;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login.html")
public class LoginController {
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
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "user") Employee user,
            BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (result.hasErrors()) {
            return "login";
        } else {
            String view = "login";
            boolean flag = userDao.isAuthenticated(user);
            System.out.println("flag" + flag);
            if (flag) {
                String user1[] = user.getEmailid().split("@");
                System.out.println(user1[0]);
                request.getSession().setAttribute("user", user1[0]);
                response.sendRedirect("adminHome.html");
            } else {
                request.setAttribute("msg", "Wrong credentials");
            }

            return view;
        }
    }
}
