package team2.wandria.service;

import org.springframework.stereotype.Service;
import team2.wandria.model.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class  FlightService {

private final List<Flight> flights = new ArrayList<>();

public FlightService() {
    flights.add(new Flight("EK202", "Dubai", "Domestic",
            LocalDateTime.of(2026, 3, 15, 14, 30),
            120, 950.0));

    flights.add(new Flight("QR811", "Doha", "International",
            LocalDateTime.of(2026, 3, 16, 9, 15),
            0, 720.0));

    flights.add(new Flight("BA108", "London", "International",
            LocalDateTime.of(2026, 3, 20, 18, 45),
            40, 1800.0));

    flights.add(new Flight("LH631", "Frankfurt", "International",
            LocalDateTime.of(2026, 3, 18, 7, 30),
            15, 1400.0));

    flights.add(new Flight("AF655", "Paris", "International",
            LocalDateTime.of(2026, 3, 22, 21, 0),
            8, 1500.0));

    flights.add(new Flight("EY303", "Abu Dhabi", "Domestic",
            LocalDateTime.of(2026, 3, 19, 10, 45),
            25, 500.0));

    flights.add(new Flight("SV220", "Jeddah", "International",
            LocalDateTime.of(2026, 3, 24, 13, 20),
            50, 1100.0));

    flights.add(new Flight("RJ551", "Amman", "International",
            LocalDateTime.of(2026, 3, 25, 8, 10),
            12, 980.0));

    flights.add(new Flight("MS901", "Cairo", "International",
            LocalDateTime.of(2026, 3, 26, 16, 0),
            30, 890.0));

    flights.add(new Flight("KU410", "Kuwait City", "International",
            LocalDateTime.of(2026, 3, 27, 11, 35),
            18, 760.0));
}

public List<Flight> findAll() {
    return Collections.unmodifiableList(flights);
}

public Flight findByFlightNumber(String flightNumber) {
    for (Flight flight : flights) {
        if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
            return flight;
        }
    }
    return null;
}

public List<Flight> findAllSortedByPrice() {
    List<Flight> sortedFlights = new ArrayList<>(flights);

    sortedFlights.sort(new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return Double.compare(f1.getPrice(), f2.getPrice());
        }
    });

    return sortedFlights;
}

public List<Flight> searchByName(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
        return flights;
    }

    String lower = keyword.trim().toLowerCase();
    List<Flight> result = new ArrayList<>();

    for (Flight flight : flights) {
        if (flight.getDestination() != null &&
                flight.getDestination().toLowerCase().contains(lower)) {
            result.add(flight);
        }
    }

    return result;
}

public List<Flight> searchByCategory(String category) {
    if (category == null || category.trim().isEmpty()) {
        return flights;
    }

    List<Flight> result = new ArrayList<>();

    for (Flight flight : flights) {
        if (flight.getCategory() != null &&
                flight.getCategory().equalsIgnoreCase(category.trim())) {
            result.add(flight);
        }
    }

    return result;
}

public Flight saveFlight(Flight flight) {
    flights.add(flight);
    return flight;
}

public Flight updateFlight(String flightNumber, Flight updated) {
    for (int i = 0; i < flights.size(); i++) {
        Flight existing = flights.get(i);

        if (existing.getFlightNumber().equalsIgnoreCase(flightNumber)) {
            existing.setDestination(updated.getDestination());
            existing.setCategory(updated.getCategory());
            existing.setDepartureTime(updated.getDepartureTime());
            existing.setSeats(updated.getSeatsAvailable());
            existing.setPrice(updated.getPrice());
            return existing;
        }
    }
    return null;
}

public boolean deleteFlight(String flightNumber) {
    return flights.removeIf(f -> f.getFlightNumber().equalsIgnoreCase(flightNumber));
}
}