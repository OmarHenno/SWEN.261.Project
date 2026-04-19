package team2.wandria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team2.wandria.model.Flight;
import team2.wandria.service.FlightService;
import java.util.List;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestController
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {

        this.flightService = flightService;
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

    @GetMapping("/api/flights")
    public List<Flight> getFlights() {
        return flightService.findAll();
    }

    @GetMapping("/api/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable String id) {
        Flight flight = flightService.findByFlightNumber(id);

        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(flight);
    }

    @PostMapping("/flights/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight saved = flightService.saveFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/flights/{id}/update")
    public ResponseEntity<Flight> updateFlight(@PathVariable String id, @RequestBody Flight flight) {
        Flight updated = flightService.updateFlight(id, flight);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/flights/{id}/delete")
    public ResponseEntity<Map<String, Object>> deleteFlight(@PathVariable String id) {
        boolean deleted = flightService.deleteFlight(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("success", true, "message", "Flight deleted successfully"));
    }
}