package team2.wandria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.wandria.model.Flight;
import team2.wandria.service.AuthServiceInterface;
import team2.wandria.service.FlightService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FlightController {

    private final FlightService flightService;
    private final AuthServiceInterface authService;

    public FlightController(FlightService flightService, AuthServiceInterface authService) {
        this.flightService = flightService;
        this.authService = authService;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return flightService.findAll();
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable String id) {
        Flight flight = flightService.findByFlightNumber(id);

        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(flight);
    }

    @GetMapping("/flights/search/name")
    public List<Flight> searchByName(@RequestParam String keyword) {
        return flightService.searchByName(keyword);
    }

    @GetMapping("/flights/search/category")
    public List<Flight> searchByCategory(@RequestParam String category) {
        return flightService.searchByCategory(category);
    }

    @PostMapping("/flights/add")
    public ResponseEntity<?> addFlight(@RequestBody Flight flight) {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", "Admins only"));
        }

        Flight saved = flightService.saveFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/flights/{id}/update")
    public ResponseEntity<?> updateFlight(@PathVariable String id, @RequestBody Flight flight) {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", "Admins only"));
        }

        Flight updated = flightService.updateFlight(id, flight);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/flights/{id}/delete")
    public ResponseEntity<?> deleteFlight(@PathVariable String id) {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "message", "Admins only"));
        }

        boolean deleted = flightService.deleteFlight(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Flight deleted successfully"
                )
        );
    }
}