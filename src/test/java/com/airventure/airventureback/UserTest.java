
package com.airventure.airventureback;

        import com.airventure.airventureback.reservation.domain.entity.Reservation;
        import com.airventure.airventureback.user.domain.entity.Role;
        import com.airventure.airventureback.user.domain.entity.User;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.util.HashSet;
        import java.util.Set;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {
    private User user;


    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserId() {
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testUserFirstName() {
        String firstName = "Test FirstName";
        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void testUserLastName() {
        String lastName = "Test LastName";
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void testUserEmail() {
        String email = "Test Email";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testPassword() {
        String password = "Test password";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

 /*  @Test
    public void testEnabled() {
        boolean isEnable = true;
        user.setIsEnabled(isEnable);
        assertEquals(isEnable, user.getIsEnabled());
    }*/

    @Test
    public void testRoles() {
        Role roles = new Role("USER_ADMIN");

        user.setRoles(roles);

        assertEquals(roles, user.getRoles());
    }

    @Test
    public void testReservations() {
        Set<Reservation> reservations = new HashSet<>();
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        reservations.add(reservation1);
        reservations.add(reservation2);

        user.setReservations(reservations);
        assertNotNull(user.getReservations());
        assertEquals(2, user.getReservations().size());
    }
}

