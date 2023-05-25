package tutorial.code.snipes.ldap;

import java.util.*;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

/**
 * Diese Klasse stellt Methoden zum Arbeiten mit LDAP-Benutzern und -Gruppen bereit.
 */
public class LdapUserAndGroups {
    DirContext connection;

    /**
     * Erstellt eine neue Instanz von LdapUserAndGroups und stellt eine Verbindung zum LDAP-Server her.
     */
    public LdapUserAndGroups() {
        newConnection();
    }

    /**
     * Erstellt eine neue Instanz von LdapUserAndGroups mit einer vorhandenen Verbindung zum LDAP-Server.
     *
     * @param connection Die bestehende Verbindung zum LDAP-Server.
     */
    public LdapUserAndGroups(DirContext connection) {
        this.connection = connection;
    }

    /**
     * Gibt die aktuelle Verbindung zum LDAP-Server zurück.
     *
     * @return Die Verbindung zum LDAP-Server.
     */

    public DirContext getConnection() {
        return connection;
    }
    /**
     * Stellt eine Verbindung zum LDAP-Server her.
     * Setzt die Verbindungsparameter und fängt mögliche Ausnahmen ab.
     */

    public void newConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");
        try {
            connection = new InitialDirContext(env);
            System.out.println("Hallo Welt!" + connection);
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ruft eine Liste von Attributen für alle Benutzer ab.
     *
     * @return Eine Liste von Attributen für alle Benutzer.
     * @throws NamingException Wenn bei der Suche nach Benutzern ein Fehler auftritt.
     */

    public List<Attributes> getAllUsers() throws NamingException {
        String searchFilter = "(objectClass=organizationalPerson)";
        String[] reqAtt = {"cn", "sn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("dc=example,dc=com", searchFilter, controls);

        SearchResult result = null;
        List<Attributes> attrList = new ArrayList<>();
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            System.out.println(result);
            Attributes attrs = result.getAttributes();
            attrList.add(attrs);
        }
        return attrList;
    }

    /**
     * Überprüft, ob ein bestimmter Benutzer vorhanden ist.
     *
     * @param userName Der Benutzername des zu suchenden Benutzers.
     * @return true, wenn der Benutzer gefunden wurde, andernfalls false.
     * @throws NamingException Wenn bei der Suche nach Benutzern ein Fehler auftritt.
     */

    public boolean findSpecificUser(String userName) throws NamingException {
        String searchFilter = "(objectClass=organizationalPerson)";
        String[] reqAtt = {"cn", "sn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);
        NamingEnumeration users = connection.search("ou=Users,dc=example,dc=com", searchFilter, controls);
        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            if (attr.get("sn") != null) {
                for (int i = 0; i < attr.get("sn").size(); i++) {
                    if (userName.equals(attr.get("sn").get(i).toString())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Ruft eine Liste von Attributen für alle Gruppen ab.
     *
     * @return Eine Liste von Attributen für alle Gruppen.
     * @throws NamingException Wenn bei der Suche nach Gruppen ein Fehler auftritt.
     */
    public List<Attributes> getAllGroups() throws NamingException {
        String searchFilter = "(objectClass=groupOfNames)";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration groups = connection.search("ou=Groups,dc=example,dc=com", searchFilter, controls);

        SearchResult result = null;
        List<Attributes> attrList = new ArrayList<>();

        while (groups.hasMore()) {
            result = (SearchResult) groups.next();
            System.out.println(result);
            Attributes attrs = result.getAttributes();
            attrList.add(attrs);
        }
        return attrList;
    }

    /**
     * Ruft Benutzerdetails auf Attributebene ab und gibt sie aus.
     *
     * @throws NamingException Wenn bei der Suche nach Benutzern ein Fehler auftritt.
     */
    public void getUserDetailOnAttributLevel() throws NamingException {

        String filter = "(objectClass=inetOrgPerson)";
        String[] atr = {"telephoneNumber"};
        SearchControls searchControls = new SearchControls();
        searchControls.setReturningAttributes(atr);
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration user = connection.search("ou=Users,dc=example,dc=com", filter, searchControls);

        while (user.hasMore()) {
            SearchResult searchResult = (SearchResult) user.nextElement();
            Attributes attObject = searchResult.getAttributes();

            NamingEnumeration attributes = attObject.getAll();
            while (attributes.hasMore()) {
                Attribute attributeObject = (Attribute) attributes.nextElement();
                System.out.println("Level 1 " + attributeObject.getID() + " " + attributeObject.get());

                NamingEnumeration a = attributeObject.getAll();
                /*
                 * Die letzte "while"-Schleife ist wichtig, um sicherzustellen,
                 * dass alle Werte eines Attributes durchlaufen und ausgegeben werden.
                 * Ohne diese Schleife würde nur der erste Wert des Attributes telephoneNumber ausgegeben werden.
                 * Beispiel:
                 * dn: cn=John Doe,ou=Users,dc=example,dc=com
                 * objectClass: inetOrgPerson
                 * cn: John Doe
                 * sn: Doe
                 * telephoneNumber: 1234567890
                 * telephoneNumber: 9876543210
                 */
                while (a.hasMore()) {
                    String solo = (String) a.nextElement();
                    System.out.println("Level 2 " + solo);
                }
            }
        }
    }

    /**
     * Ruft das Passwort für den angegebenen Benutzernamen ab.
     *
     * @param userName der Benutzername
     * @return das Passwort des Benutzers oder null, wenn das Passwort nicht gefunden wurde
     * @throws NamingException falls ein Fehler bei der Namensauflösung auftritt
     */
    public String getUserPassword(String userName) throws NamingException {
        String searchFilter = "(cn=" + userName + ")";
        String[] reqAtt = {"userPassword"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);
        NamingEnumeration users = connection.search("ou=Users,dc=example,dc=com", searchFilter, controls);
        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            if (attr.get("userPassword") != null) {
                return attr.get("userPassword").get().toString();
            }
        }
        return null; // Wenn das Passwort nicht gefunden wurde
    }
    /**
     * Ändert das Passwort für den angegebenen Benutzernamen.
     *
     * @param userName     der Benutzername
     * @param newPassword das neue Passwort
     * @throws NamingException falls ein Fehler bei der Namensauflösung auftritt
     * @throws IllegalArgumentException falls der Benutzername oder das neue Passwort ungültig sind
     * Verende wenn möglich die java guard clause um den Kode lesbarer zu machen.
     */
    public void changeUserPassword(String userName, String newPassword) throws NamingException {
        if (userName == null || newPassword == null || userName.isEmpty() || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Benutzername oder neues Passwort dürfen nicht null oder leer sein");
        }
        // Verwende regulären Ausdruck, um die Länge des Passworts zu überprüfen
        if (newPassword.length() < 8 || newPassword.length() > 16) {
            throw new IllegalArgumentException("Passwort muss zwischen 8 und 16 Zeichen lang sein");
        }
        // Überprüfe mit regulärem Ausdruck, ob das Passwort Zahlen und Sonderzeichen enthält
        if (!newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$")) {
            throw new IllegalArgumentException("Passwort muss mindestens eine Zahl, einen Großbuchstaben, einen Kleinbuchstaben, ein Sonderzeichen enthalten");
        }
        // Überprüfe, ob der Benutzer dasselbe Passwort verwendet
        if (getUserPassword(userName).equals(newPassword)) {
            throw new IllegalArgumentException("Das neue Passwort darf nicht mit dem alten übereinstimmen");
        }
        // Überprüfe, ob der Benutzer existiert
        if (!findSpecificUser(userName)) {
            throw new IllegalArgumentException("Benutzer nicht gefunden");
        }
        try {
            ModificationItem[] item = new ModificationItem[1];
            item[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", newPassword));
            connection.modifyAttributes("cn=" + userName + ",ou=Users,dc=example,dc=com", item);
        } catch (NamingException e) {
            // Todo: log4j implnementieren
            e.printStackTrace();
        }
    }

}

