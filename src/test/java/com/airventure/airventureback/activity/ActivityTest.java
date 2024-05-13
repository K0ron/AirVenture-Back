package com.airventure.airventureback.activity;

import com.airventure.airventureback.activity.domain.entity.Activity;
import com.airventure.airventureback.category.domain.Category;
import com.airventure.airventureback.reservation.domain.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
public class ActivityTest {
    private Activity activity;

    @BeforeEach
    public void setUp() {
        activity = new Activity();
    }

    @Test
    public void testActivityId() {
        Long id = 1L;
        activity.setId(id);
        assertEquals(id, activity.getId());
    }

    @Test
    public void testActivityName() {
        String name = "New Activity";
        activity.setName(name);
        assertEquals(name, activity.getName());
    }

    @Test
    public void testActivityDescription() {
        String description = "This is the description of my activity";
        activity.setDescription(description);
        assertEquals(description, activity.getDescription());
    }

    @Test
    public void testActivityDuration() {
        Integer duration = 2;
        activity.setDuration(duration);
        assertEquals(duration, activity.getDuration());
    }

    @Test
    public void testActivityLocation() {
        String location = "Toulouse, France";
        activity.setLocation(location);
        assertEquals(location, activity.getLocation());
    }

    @Test
    public void testActivityPrice() {
        Integer price = 250;
        activity.setPrice(price);
        assertEquals(price, activity.getPrice());
    }

    @Test
    public void testActivityReservation() {
        Set<Reservation> reservations = new HashSet<>();
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        reservations.add(reservation1);
        reservations.add(reservation2);

        activity.setReservations(reservations);
        assertNotNull(activity.getReservations());
        assertEquals(2, activity.getReservations().size());
    }

    @Test
    public void testActivityCategories() {
        Set<Category> categories = new HashSet<>();
        Category category1 = new Category();
        categories.add(category1);

        activity.setCategories(categories);
        assertNotNull(activity.getCategories());
        assertEquals(1, activity.getCategories().size());
    }
}
