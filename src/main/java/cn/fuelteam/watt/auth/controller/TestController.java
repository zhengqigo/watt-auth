package cn.fuelteam.watt.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/admin")
    //@PreAuthorize("hasAuthority('R_ADMIN')")
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        return "hello";
    }
}
