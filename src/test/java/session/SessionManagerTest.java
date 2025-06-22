package session;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for SessionManager to validate basic CRUD operations.
 */
public class SessionManagerTest {

    /**
     * Tests creation, retrieval, and deletion of session entries.
     */
    @Test
    public void testSessionCRUD() {
        SessionManager sm = new SessionManager();

        // Create or update a session
        sm.createOrUpdateSession("user1", "token123");

        // Validate session retrieval
        assertEquals("token123", sm.getSession("user1"));

        // Delete the session
        sm.deleteSession("user1");

        // Ensure session is removed
        assertNull(sm.getSession("user1"));
    }
}
