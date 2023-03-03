package org.testing.functions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SaveWebpageAsHtmlTests {
    /**
     * Method under test: {@link SaveWebpageAsHtml#checkFilePermissions(String, Path)}
     */
    @Test
    void testCheckFilePermissions() throws IOException, SecurityException {
        SaveWebpageAsHtml saveWebpageAsHtml = new SaveWebpageAsHtml();
        saveWebpageAsHtml.checkFilePermissions("foo.txt", Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
        HttpURLConnection httpURLConnection = saveWebpageAsHtml.getHttpURLConnection("https://example.org/example");
        assertFalse(httpURLConnection.getAllowUserInteraction());
        assertTrue(httpURLConnection.getUseCaches());
        assertTrue(httpURLConnection.getRequestProperties().isEmpty());
        assertEquals(0, httpURLConnection.getConnectTimeout());
        assertTrue(httpURLConnection.getDoInput());
        assertTrue(httpURLConnection.getInstanceFollowRedirects());
        assertFalse(httpURLConnection.getDoOutput());
        assertEquals(0, httpURLConnection.getReadTimeout());
        assertEquals("GET", httpURLConnection.getRequestMethod());
        assertTrue(httpURLConnection.getDefaultUseCaches());
        assertEquals(0L, httpURLConnection.getIfModifiedSince());
        URL uRL = httpURLConnection.getURL();
        assertEquals(443, uRL.getDefaultPort());
        assertEquals("example.org", uRL.getAuthority());
        assertEquals("https", uRL.getProtocol());
        assertEquals("https://example.org/example", uRL.toExternalForm());
        assertEquals("example.org", uRL.getHost());
        assertEquals("/example", uRL.getPath());
        assertEquals("example.org:80", httpURLConnection.getPermission().getName());
        assertEquals("/example", uRL.getFile());
        assertEquals(-1, uRL.getPort());
    }
}

