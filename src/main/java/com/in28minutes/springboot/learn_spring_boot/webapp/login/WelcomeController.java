package com.in28minutes.springboot.learn_spring_boot.webapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotWelcomePage(ModelMap modelMap) {
        //This is where we put the value username in the session
        modelMap.put("username", getLoggedInUsername());
        return "welcome";
    }

    private String getLoggedInUsername(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "/ajax-timer", method = RequestMethod.GET)
    @ResponseBody
    public String ajaxTimer() throws InterruptedException {
        Thread.sleep(5000); // Simulate a 5-second delay
        return "The timer has finished!";
    }

}
