package tutorial.code.snipes.ldap;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.zapodot.junit.ldap.EmbeddedLdapRule;
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import static org.junit.Assert.*;
class LdapUserAndGroupsMitMockTest {
    /**
     * Diese Klasse enthält JUnit-Tests für die LdapUserAndGroups-Klasse.
     * Sie testet verschiedene Funktionen der Klasse, einschließlich der Suche nach einem bestimmten Benutzer,
     * der Herstellung einer Verbindung zum LDAP-Server und einer weiteren Testfunktion.
     */
    public static final String DC_EXAMPLE_DC_COM = "dc=example,dc=com";
    @Rule
    public EmbeddedLdapRule embeddedLdapRule = EmbeddedLdapRuleBuilder.newInstance().usingDomainDsn(DC_EXAMPLE_DC_COM)
            .usingBindDSN("cn=Directory manager,ou=users,dc=example,dc=com").bindingToPort(10389).importingLdifs("userUndGruppen.ldif").build();

    /**
     * Überprüft, ob die Methode "findSpecificUser" true zurückgibt, wenn der Benutzer existiert.
     * @throws NamingException - Wenn ein Naming-Fehler auftritt.
     */
    @Test
    void findSpecificUser_ShouldReturnTrue_WhenUserExists() throws NamingException {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        String existingUserName = "John Doe";
        boolean userExists = ldapUserAndGroups.findSpecificUser(existingUserName);
        assertTrue(userExists);
    }
    /**
     * Überprüft, ob die Methode "findSpecificUser" false zurückgibt, wenn der Benutzer nicht existiert.
     * @throws NamingException - Wenn ein Naming-Fehler auftritt.
     */
    @Test
    void findSpecificUser_ShouldReturnFalse_WhenUserDoesNotExist() throws NamingException {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        String nonExistingUserName = "NonExistingUser";
        boolean userExists = ldapUserAndGroups.findSpecificUser(nonExistingUserName);
        assertFalse(userExists);
    }
    /**
     * Überprüft, ob eine Verbindung zum LDAP-Server hergestellt werden kann.
     */
    @Test
    void newConnection_ShouldConnectToLdapServer() {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        DirContext connection = ldapUserAndGroups.getConnection();
        assertNotNull(connection);
    }
    /**
     * Eine weitere Testmethode, die noch nicht implementiert ist.
     * @throws Exception - Wenn ein Fehler auftritt.
     */
    @Test
    public void testwas() throws Exception {
        LdapUserAndGroups ldapUserAndGroups = new LdapUserAndGroups();
        //  ldapUserAndGroups.testwas();
    }
}

