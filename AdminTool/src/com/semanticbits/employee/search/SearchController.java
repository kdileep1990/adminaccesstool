package com.semanticbits.employee.search;

import com.semanticbits.employee.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search.html")
public class SearchController {

    @Resource
    private SearchService searchService;

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void searchPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        searchService.retrieveAutoSuggestions();
        System.out.println(searchService.getAutoSuggestions().getFirstNames().length);
        JSONObject jSONObject =JSONObject.fromObject(searchService.getAutoSuggestions());
        PrintWriter writer = response.getWriter();
        System.out.println(jSONObject.toString());
        writer.write(jSONObject.toString());
    }
    @RequestMapping(method = RequestMethod.POST)
    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("hi this is dileep");
        searchService.retrieveEmployeeDetails(request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("role"),request.getParameter("userType"));
//        System.out.println(searchService.getAutoSuggestions().getFirstNames().length);
//        model.addObject("autoSuggestions", searchService.getAutoSuggestions());
//        System.out.println( searchService.getEmployees()[0].getEmailid());
        JSONArray jSONArray =JSONArray.fromObject(searchService.getEmployees());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        System.out.println(jSONArray.toString());
        writer.write(jSONArray.toString());
    }
}
