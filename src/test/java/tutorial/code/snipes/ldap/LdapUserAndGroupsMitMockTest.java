package tutorial.code.snipes.ldap;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.zapodot.junit.ldap.EmbeddedLdapRule;
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
     * Diese Klasse enthält Unit-Tests für die Methode "getAllAttributeOfName" der Klasse "LdapUserAndGroups".
     * Sie überprüft, ob die Methode alle Attribute eines bestimmten Namens aus dem LDAP-Verzeichnis abruft.
     */
    @Test
    public void shouldReturnAllAttributesForGivenName() throws NamingException {
        // Arrange
        DirContext mockDirContext = mock(DirContext.class);
        NamingEnumeration<SearchResult> mockNamingEnumeration = mock(NamingEnumeration.class);
        SearchResult mockSearchResult = mock(SearchResult.class);
        Attributes mockAttributes = mock(Attributes.class);
        NamingEnumeration  mockAttributeEnumeration = mock(NamingEnumeration.class);
        Attribute mockAttribute = mock(Attribute.class);
        NamingEnumeration mockValueEnumeration = mock(NamingEnumeration.class);

        LdapUserAndGroups ldapUserAndGroups = mock(LdapUserAndGroups.class, Mockito.CALLS_REAL_METHODS);
        ldapUserAndGroups.connection = mockDirContext;

        // Mock behaviour
        Mockito.doReturn(mockDirContext).when(ldapUserAndGroups).getnewConnection();
        when(mockDirContext.search(anyString(), anyString(), any(SearchControls.class))).thenReturn(mockNamingEnumeration);
        when(mockNamingEnumeration.hasMore()).thenReturn(true, false);
        when(mockNamingEnumeration.nextElement()).thenReturn(mockSearchResult);
        when(mockSearchResult.getAttributes()).thenReturn(mockAttributes);
        when(mockAttributes.getAll()).thenReturn(mockAttributeEnumeration);
        when(mockAttributeEnumeration.hasMore()).thenReturn(true, false);
        when(mockAttributeEnumeration.next()).thenReturn(mockAttribute);
        when(mockAttribute.getID()).thenReturn("cn");
        when(mockAttribute.getAll()).thenReturn(mockValueEnumeration);
        when(mockValueEnumeration.hasMore()).thenReturn(true, false);
        when(mockValueEnumeration.next()).thenReturn("testuser");

        // Act
        List<String> allAttributes = ldapUserAndGroups.getAllAttributeOfName("testuser");

        // Assert
        assertEquals(1, allAttributes.size());
        assertEquals("cn: testuser", allAttributes.get(0));
    }
}

