package team2.wandria.service;

import team2.wandria.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightService {

    private final List<Flight> flights = new ArrayList<>();

    public FlightService() { //temporary memory data until a database is implemented in future sprints
        flights.add(new Flight("EK202", "Dubai",
        LocalDateTime.of(2026, 3, 15, 14, 30),
        120, 950.0));

        flights.add(new Flight("QR811", "New York",
        LocalDateTime.of(2026, 3, 16, 9, 15),                0, 720.0));

        flights.add(new Flight("BA108", "London",
        LocalDateTime.of(2026, 3, 20, 18, 45),
        40, 1800.0));

        flights.add(new Flight("LH631", "America",
        LocalDateTime.of(2026, 3, 18, 7, 30),
        15, 1400.0));

        flights.add(new Flight("AF655", "Paris",
        LocalDateTime.of(2026, 3, 22, 21, 0),
        8, 1500.0));
    }

    public List<Flight> findAll() {

        return Collections.unmodifiableList(flights);
    }
}