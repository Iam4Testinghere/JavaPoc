package org.testing.functions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveWebpageAsHtml {
    // Konstanten am Anfang der Klasse deklarieren
    public static final String SW_TEST_HTML = "test.html";
    public static final String HTTP_CONSOLE_LOGIN_PAGE = "http://abc:7001/console/login/LoginForm.jsp";

    private String webpageUrl = HTTP_CONSOLE_LOGIN_PAGE;
    private String htmlFileName = SW_TEST_HTML;
    private Path htmlFilePath = Paths.get(htmlFileName);

    public void startProcess() {
        try {
            // HTML-Datei von der Webseite lesen
            readPage(webpageUrl, htmlFileName);
            // Überprüfen Sie die Berechtigungen der HTML-Datei
            checkFilePermissions(htmlFileName, htmlFilePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkFilePermissions(String htmlFileName, Path htmlFilePath) throws SecurityException {
        // Überprüfen Sie, ob die HTML-Datei beschreibbar ist
        if (!Files.isWritable(htmlFilePath)) {
            System.out.println("Die HTML-Datei " + htmlFileName + " ist nicht überschreibar.");
        }
        // Überprüfen Sie, ob die HTML-Datei bereits vorhanden ist
        if (Files.exists(htmlFilePath)) {
            throw new IllegalArgumentException("Die HTML-Datei " + htmlFileName + " existiert bereits.");
        }
        System.out.println("Die HTML-Datei " + htmlFileName + " wurde erfolgreich erstellt.");
    }


    public void readPage(String webpageUrl, String htmlFileName) throws IOException {
        HttpURLConnection connection = getHttpURLConnection(webpageUrl);
        readPage(connection);
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Fehler beim Laden der Webseite.");
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // InputStream von der Webseite lesen
            inputStream = connection.getInputStream();
            // HTML-Datei erstellen
            outputStream = new FileOutputStream(htmlFileName);
            byte[] buffer = new byte[1024];
            int length;
            // InputStream in die HTML-Datei schreiben
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            System.out.println("Die Webseite wurde erfolgreich geladen.");
        } finally {
            // InputStream und OutputStream schließen
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Fehler beim Schließen des InputStreams
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // Fehler beim Schließen des OutputStreams
                }
            }
        }
    }

    public void readPage(HttpURLConnection connection) throws IOException {
        // GET-Anforderung an die Webseite senden
        connection.setRequestMethod("GET");
        // User-Agent festlegen, um als Webbrowser zu erscheinen
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari /537.11");
        // Verbindung zur Webseite herstellen
        connection.connect();
    }

    public HttpURLConnection getHttpURLConnection(String webpageUrl) throws IOException {
        // URL-Objekt aus der Webseiten-URL erstellen
        URL url = new URL(webpageUrl);
        // Verbindung zur Webseite öffnen
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }
}