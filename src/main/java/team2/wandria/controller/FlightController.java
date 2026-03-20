package team2.wandria.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team2.wandria.model.Flight;
import team2.wandria.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/api/flights/search/name")
    @ResponseBody
    public List<Flight> searchByName(@RequestParam String keyword) {
        return flightService.searchByName(keyword);
    }


    @GetMapping("/api/flights/search/category")
    @ResponseBody
    public List<Flight> searchByCategory(@RequestParam String category) {
        return flightService.searchByCategory(category);
    }
    @PostMapping("/api/flights/collection")
    @ResponseBody
    public String addToCollection(@RequestParam String flightNumber) {
        flightService.addToCollection(flightNumber);
        return "Flight added to collection";
    }

    @GetMapping("/collection")
    public String getCollection(Model model) {
        model.addAttribute("flights", flightService.getCollection());
        return "collection";
    }
}