package team2.wandria.controller;

import team2.wandria.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping({"/flights"}) //return the view of available flights using mustache templat
    public String getFlights(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "flights";
    }
}