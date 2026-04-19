package team2.wandria.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team2.wandria.model.Flight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
    }

    @Test
    void findAllShouldReturnAllAvailableFlights() {
        List<Flight> flights = flightService.findAll();

        assertNotNull(flights);
        assertFalse(flights.isEmpty());
    }

    @Test
    void findByFlightNumberWithValidIdShouldReturnFlight() {
        Flight flight = flightService.findByFlightNumber("EK202");

        assertNotNull(flight);
        assertEquals("EK202", flight.getFlightNumber());
    }

    @Test
    void findByFlightNumberWithInvalidIdShouldReturnNull() {
        Flight flight = flightService.findByFlightNumber("FAKE999");

        assertNull(flight);
    }

    @Test
    void searchByNameWithValidKeywordShouldReturnMatchingFlights() {
        List<Flight> flights = flightService.searchByName("Dubai");

        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        assertEquals("Dubai", flights.get(0).getDestination());
    }

    @Test
    void searchByNameWithInvalidKeywordShouldReturnEmptyList() {
        List<Flight> flights = flightService.searchByName("Atlantis");

        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }

    @Test
    void searchByCategoryWithValidCategoryShouldReturnMatchingFlights() {
        List<Flight> flights = flightService.searchByCategory("International");

        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        assertEquals("International", flights.get(0).getCategory());
    }

    @Test
    void searchByCategoryWithInvalidCategoryShouldReturnEmptyList() {
        List<Flight> flights = flightService.searchByCategory("Space");

        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }
}