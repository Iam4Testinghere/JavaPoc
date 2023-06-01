package tutorial.code.snipes.ldap;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.zapodot.junit.ldap.EmbeddedLdapRule;
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import static org.junit.Assert.*;
public class LdapUserAndGroupsOhneMockTest {
    /**
     * Diese Klasse enthält Tests für die LdapUserAndGroups-Klasse, die Benutzer und Gruppen in einem LDAP-Verzeichnis manipuliert.
     */

    public static final String DC_EXAMPLE_DC_COM = "dc=example,dc=com";
    @Rule
    public EmbeddedLdapRule embeddedLdapRule = EmbeddedLdapRuleBuilder.newInstance().usingDomainDsn(DC_EXAMPLE_DC_COM)
            .usingBindDSN("cn=Directory manager,ou=users,dc=example,dc=com").bindingToPort(10389).importingLdifs("userUndGruppen.ldif").build();
    /**
     * Testet, ob findSpecificUser() "true" zurückgibt, wenn der Benutzer vorhanden ist.
     *
     * @throws NamingException Wenn ein Fehler beim Zugriff auf das LDAP-Verzeichnis auftritt.
     */
    @Test
    void findSpecificUser_ShouldReturnTrue_WhenUserExists() throws NamingException {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        String existingUserName = "John Doe";
        boolean userExists = ldapUserAndGroups.findSpecificUser(existingUserName);
        assertTrue(userExists);
    }
    /**
     * Testet, ob findSpecificUser() "false" zurückgibt, wenn der Benutzer nicht vorhanden ist.
     *
     * @throws NamingException Wenn ein Fehler beim Zugriff auf das LDAP-Verzeichnis auftritt.
     */
    @Test
    void findSpecificUser_ShouldReturnFalse_WhenUserDoesNotExist() throws NamingException {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        String nonExistingUserName = "NonExistingUser";
        boolean userExists = ldapUserAndGroups.findSpecificUser(nonExistingUserName);
        assertFalse(userExists);
    }
    /**
     * Testet, ob newConnection() erfolgreich eine Verbindung zum LDAP-Server herstellt.
     */
    @Test
    void newConnection_ShouldConnectToLdapServer() {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        DirContext connection = ldapUserAndGroups.getConnection();
        assertNotNull(connection);
    }
}

