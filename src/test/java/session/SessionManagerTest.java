package session;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionManagerTest {
    @Test
    public void testSessionCRUD() {
        SessionManager sm = new SessionManager();
        sm.createOrUpdateSession("user1", "token123");
        assertEquals("token123", sm.getSession("user1"));
        sm.deleteSession("user1");
        assertNull(sm.getSession("user1"));
    }
}