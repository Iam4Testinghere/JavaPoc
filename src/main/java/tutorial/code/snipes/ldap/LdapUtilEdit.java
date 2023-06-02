package tutorial.code.snipes.ldap;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Properties;

public class LdapUtilEdit {
    /**
     * Diese Klasse bietet Methoden zur Bearbeitung von LDAP-Verbindungen und Operationen.
     * Sie ermöglicht das Ändern von Benutzergruppen, das Überprüfen, ob ein Benutzer in einer Gruppe ist,
     * das Überprüfen, ob ein Benutzer existiert, und das Überprüfen, ob eine Gruppe existiert.
     */

    public static final String COM_SUN_JNDI_LDAP_LDAP_CTX_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String LDAP_LOCALHOST_10389 = "ldap://localhost:10389";
    public static final String UID_ADMIN_OU_SYSTEM = "uid=admin, ou=system";
    public static final String PASSWORD = "secret";
    public static final String GROUPS_OU_SYSTEM = "ou=groups,ou=system";
    public static final String OU_GROUPS_OU_SYSTEM = "ou=groups,ou=system";
    private DirContext connection;

    /**
     * Gibt eine neue LDAP-Verbindung zurück.
     *
     * @return die neu erstellte Verbindung oder null, wenn ein Fehler aufgetreten ist.
     */
    public DirContext getNewConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, COM_SUN_JNDI_LDAP_LDAP_CTX_FACTORY);
        env.put(Context.PROVIDER_URL, LDAP_LOCALHOST_10389);
        env.put(Context.SECURITY_PRINCIPAL, UID_ADMIN_OU_SYSTEM);
        env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        try {
            connection = new InitialDirContext(env);
            return connection;
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ändert die Gruppe eines Benutzers.
     *
     * @param group    der Name der Zielgruppe.
     * @param username der Benutzername.
     * @return true, wenn die Änderung erfolgreich war, false sonst.
     */
    boolean changeGroupOfUsers(String group, String username) {
        String searchFilter = "(&(objectClass=person)(uid=" + username + "))";
        String[] attr = {"cn"};
        String searchBase = GROUPS_OU_SYSTEM;
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(attr);
        try {
            getNewConnection().search(searchBase, searchFilter, controls);
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("cn", group));
            getNewConnection().modifyAttributes(searchBase, mods);
            return true;
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Überprüft, ob ein Benutzer in einer Gruppe ist.
     *
     * @param group    der Name der Gruppe.
     * @param username der Benutzername.
     * @return true, wenn der Benutzer in der Gruppe ist, false sonst.
     */
    boolean isUserInGroup(String group, String username) {
        if (group == null || group.isEmpty()) {
            throw new IllegalArgumentException("Der Gruppenname darf nicht null oder leer sein");
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Der Benutzername darf nicht null oder leer sein");
        }

        String searchFilter = "(&(objectClass=groupOfUniqueNames)(cn=" + group + ")(uniqueMember= cn=" + username + ",ou=users,ou=system" + "))";
        String[] attr = {"cn"};
        String searchBase = OU_GROUPS_OU_SYSTEM;
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(attr);

        DirContext connection = null;
        NamingEnumeration<SearchResult> results = null;

        try {
            connection = getNewConnection();
            if (connection == null) {
                throw new NamingException("Es konnte keine Verbindung zum LDAP-Server hergestellt werden");
            }

            results = connection.search(searchBase, searchFilter, controls);
            return results.hasMore();
        } catch (NamingException ne) {
            // Hier kann Logging hinzugefügt werden, falls eine Logging-Bibliothek verwendet wird.
            System.err.println("Beim Überprüfen, ob der Benutzer in der Gruppe ist, ist ein Fehler aufgetreten: " + ne.getMessage());
            return false;
        } finally {
            try {
                if (results != null) {
                    results.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (NamingException ne) {
                System.err.println("Beim Schließen der LDAP-Ressourcen ist ein Fehler aufgetreten: " + ne.getMessage());
            }
        }
    }

    /**
     * Überprüft, ob ein Benutzer existiert.
     *
     * @param username der Benutzername.
     * @return true, wenn der Benutzer existiert, false sonst.
     */
    boolean userExists(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Der Benutzername darf nicht null oder leer sein");
        }

        String searchFilter = "(&(objectClass=inetOrgPerson)(cn=" + username + "))";
        String[] attr = {"cn"};
        String searchBase = "ou=users,dc=example,dc=com";
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(attr);

        DirContext connection = null;
        NamingEnumeration<SearchResult> results = null;

        try {
            connection = getNewConnection();
            if (connection == null) {
                throw new NamingException("Es konnte keine Verbindung zum LDAP-Server hergestellt werden");
            }

            results = connection.search(searchBase, searchFilter, controls);
            return results.hasMore();
        } catch (NamingException ne) {
            System.err.println("Beim Überprüfen, ob der Benutzer existiert, ist ein Fehler aufgetreten: " + ne.getMessage());
            return false;
        } finally {
            try {
                if (results != null) {
                    results.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (NamingException ne) {
                System.err.println("Beim Schließen der LDAP-Ressourcen ist ein Fehler aufgetreten: " + ne.getMessage());
            }
        }
    }

    /**
     * Überprüft, ob eine Gruppe existiert.
     *
     * @param group der Name der Gruppe.
     * @return true, wenn die Gruppe existiert, false sonst.
     */
    boolean groupExists(String group) {
        if (group == null || group.isEmpty()) {
            throw new IllegalArgumentException("Der Gruppenname darf nicht null oder leer sein");
        }

        String searchFilter = "(&(objectClass=groupOfUniqueNames)(cn=" + group + "))";
        String[] attr = {"cn"};
        String searchBase = OU_GROUPS_OU_SYSTEM;
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(attr);

        DirContext connection = null;
        NamingEnumeration<SearchResult> results = null;

        try {
            connection = getNewConnection();
            if (connection == null) {
                throw new NamingException("Es konnte keine Verbindung zum LDAP-Server hergestellt werden");
            }

            results = connection.search(searchBase, searchFilter, controls);
            return results.hasMore();
        } catch (NamingException ne) {
            // Hier kann Logging hinzugefügt werden, falls eine Logging-Bibliothek verwendet wird.
            System.err.println("Beim Überprüfen, ob die Gruppe existiert, ist ein Fehler aufgetreten: " + ne.getMessage());
            return false;
        } finally {
            try {
                if (results != null) {
                    results.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (NamingException ne) {
                System.err.println("Beim Schließen der LDAP-Ressourcen ist ein Fehler aufgetreten: " + ne.getMessage());
            }
        }
    }
}
