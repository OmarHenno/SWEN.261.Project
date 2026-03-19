package team2.wandria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class HomeController {

        @GetMapping("/") // home page
        public String getHomePage() {
            return "index";
        }
    }

