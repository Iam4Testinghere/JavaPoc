package tutorial.code.snipes.ApacheFlink;

/**
 * Diese Klasse repräsentiert ein Sensorereignis, das eine Sensor-ID, einen Messwert und einen Zeitstempel enthält.
 * TODO: Ich muss noch Junit 5 Demo Test schreiben.
 * TODO: Fehlerbehandlung fehlen.
 * TODO: Evtl. noch Logger.getLogge Implementieren.
 */
public class SensorEvent {
    private String sensorId;
    private double value;
    private long eventTime;

    /**
     * Änderung von Patrick Wiget am 10.Mai 2023: Hinzufügen einer neuen Funktion plus Text Beschreibung.
     * Erstellt ein neues Sensorereignis mit den angegebenen Eigenschaften.
     *
     * @param sensorId Die ID des Sensors, der das Ereignis ausgelöst hat.
     * @param value Der gemessene Wert des Sensors zum Zeitpunkt des Ereignisses.
     * @param eventTime Der Zeitstempel des Ereignisses.
     */
    public SensorEvent(String sensorId, double value, long eventTime) {
        this.sensorId = sensorId;
        this.value = value;
        this.eventTime = eventTime;
    }

    /**
     * Gibt die ID des Sensors zurück, der das Ereignis ausgelöst hat.
     *
     * @return Die ID des Sensors.
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Gibt den gemessenen Wert des Sensors zum Zeitpunkt des Ereignisses zurück.
     *
     * @return Der gemessene Wert.
     */
    public double getValue() {
        return value;
    }

    /**
     * Gibt den Zeitstempel des Ereignisses zurück.
     *
     * @return Der Zeitstempel.
     */
    public long getEventTime() {
        return eventTime;
    }
}
