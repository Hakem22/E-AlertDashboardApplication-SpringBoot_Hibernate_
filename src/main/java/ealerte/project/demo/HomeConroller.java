package ealerte.project.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeConroller implements ErrorController {


    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword() {
        return "forgetPassword";
    }

    @GetMapping("/wait")
    public String checkEmail() {
        return "wait";
    }

    @GetMapping("/lockScreen")
    public String lockScreen() {
        return "lockScreen";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/inbox")
    public String inbox() {
        return "inbox";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/calender")
    public String calender() {
        return "calender";
    }

    @GetMapping("/sensorFomm")
    public String sensorForm() {
        return "sensorForm";
    }

    @GetMapping("/emergencyServiceForm")
    public String emergencyServiceForm() {
        return "emergencyServiceForm";
    }

    @GetMapping("/managerForm")
    public String managerForm() {
        return "managerForm";
    }

    @GetMapping("/adminForm")
    public String adminForm() {
        return "adminForm";
    }

    @GetMapping("/adminDirectory")
    public String adminDirectory() {
        return "adminDirectory";
    }

    @GetMapping("/managerDirectory")
    public String managerDirectory() {
        return "managerDirectory";
    }

    @GetMapping("/sensorsTable")
    public String sensorsTable() {
        return "sensorsTable";
    }

    @GetMapping("/emergencyService")
    public String emergencyService() {
        return "emergencyService";
    }

    @GetMapping("/statistics")
    public String statistics() {
        return "statistics";
    }

    @GetMapping("/mobileAlerteMap")
    public String mobileAlerteMap() {
        return "mobileAlerteMap";
    }

    @GetMapping("/sensorsMap")
    public String sensorsMap() {
        return "sensorsMap";
    }

    @GetMapping("/townVectorMap")
    public String townVectorMap() {
        return "townVectorMap";
    }

    @GetMapping("/alerteTable")
    public String alerteTable() {
        return "alerteTable";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/error")
    public String error() {
        return "page-404";
    }
    @GetMapping("/login")
    public String login2() {
        return "login";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    }
