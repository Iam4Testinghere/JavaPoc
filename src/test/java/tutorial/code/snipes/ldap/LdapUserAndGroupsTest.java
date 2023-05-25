package tutorial.code.snipes.ldap;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

import java.util.List;


public class LdapUserAndGroupsTest {
    /**
     * Diese Klasse enthält Testfälle für die Methode "changeUserPassword" der Klasse "LdapUserAndGroups".
     * Sie prüft verschiedene Szenarien, in denen eine IllegalArgumentException erwartet wird.
     * Testet die Methode "changeUserPassword" der Klasse "LdapUserAndGroups" mit verschiedenen Argumenten,
     * bei denen eine IllegalArgumentException erwartet wird.
     *
     * @throws NamingException wenn ein Fehler bei der Namensauflösung auftritt
     */
    @Test
    void testChangeUserPasswordE() throws NamingException {
        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" und "password" nicht angegeben werden
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword(null, null));

        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" nicht angegeben wird,
        // aber "password" angegeben ist
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword("foo", null));

        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" und "password" identisch sind
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword("foo", "foo"));

        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" angegeben ist,
        // aber "password" nicht den Anforderungen entspricht
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword("", "IdoIt4you"));

        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" angegeben ist und
        // "password" den Anforderungen entspricht
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword("JohnDoe",
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$"));

        // Überprüft, ob eine IllegalArgumentException geworfen wird, wenn "username" angegeben ist,
        // aber "password" leer ist
        assertThrows(IllegalArgumentException.class, () -> (new LdapUserAndGroups()).changeUserPassword("JohnDoe", ""));
    }
    @Test
    void getUserDetailOnAttributLevel() throws NamingException {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        assertTrue(ldapUserAndGroups.getUserPhoneNumbers("JohnDoe").size() > 0);
    }
}
