package tutorial.code.snipes.ApacheFlink;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

/**
 * Änderung von Patrick Wiget am 10. Mai 2023: Hinzufügen einer neuen Funktion plus Text.
 * Diese Klasse extrahiert Zeitstempel von Sensorereignissen und weist Wasserzeichen zu,
 * um Verzögerungen zu behandeln.
 * TODO: Ich muss noch Junit 5 Demo Test schreiben.
 * TODO: Fehlerbehandlung fehlen.
 * TODO: Evtl. noch Logger.getLogge Implementieren.
 */
public class SensorEventTimestampExtractor implements AssignerWithPeriodicWatermarks<SensorEvent> {
    private long currentMaxTimestamp = 0;
    private final long maxOutOfOrderness = 10000;
    /**
     * Extrahiert den Ereigniszeitstempel aus dem Sensorereignis und aktualisiert den aktuellen maximalen Zeitstempel.
     *
     * @param element Das Sensorereignis, aus dem der Zeitstempel extrahiert wird.
     * @param previousElementTimestamp Der vorherige Zeitstempel des vorherigen Elements.
     * @return Der extrahierte Zeitstempel des Sensorereignisses.
     */
    @Override
    public long extractTimestamp(SensorEvent element, long previousElementTimestamp) {
        long eventTime = element.getEventTime();
        currentMaxTimestamp = Math.max(eventTime, currentMaxTimestamp);
        return eventTime;
    }
    /**
     * Gibt ein Wasserzeichen zurück, das den maximalen Zeitstempel abzüglich der maximalen Verzögerungszeit
     * (maxOutOfOrderness) enthält.     *
     * @return Das Wasserzeichen.
     */
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
    }
}
