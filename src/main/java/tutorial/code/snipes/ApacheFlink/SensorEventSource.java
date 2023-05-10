package tutorial.code.snipes.ApacheFlink;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 Änderung von Patrick Wiget am  01.Mai 2023: Hinzufügen einer neuen Funktion plus Text Beschreibung.
 Diese Klasse generiert zufällige Sensorereignisse und gibt sie über einen Datenstrom aus.

 Dieser Codeausschnitt definiert eine Variable "eventTime" vom Datentyp "long". Der Wert von "eventTime" wird berechnet,
 indem die aktuelle Systemzeit in Millisekunden von einem zufällig generierten Wert subtrahiert wird. Der zufällige Wert
 wird durch die Methode "nextInt(n)" der Klasse "Random" generiert und gibt eine zufällige ganze Zahl zwischen 0 (inklusive)
 und "n" (exklusive) zurück.
 In diesem Fall wird "nextInt(1000)" verwendet, was bedeutet, dass die generierte zufällige Zahl zwischen 0 und 999 (inklusive) liegt.
 Durch Subtrahieren dieser zufälligen Zahl von der aktuellen Systemzeit wird eine zufällige Zeit in der Vergangenheit erzeugt,
 die maximal 999 Millisekunden (also knapp eine Sekunde) von der aktuellen Systemzeit entfernt ist.
 Dieser Codeausschnitt kann beispielsweise in einem System eingesetzt werden, das Events oder Ereignisse generiert,
 um den Zeitstempel jedes Ereignisses zufällig zu variieren und so ein realistischeres Verhalten zu simulieren.
 long eventTime = System.currentTimeMillis() - RANDOM.nextInt(1000);
 * TODO: Ich muss noch Junit 5 Demo Test schreiben.
 * TODO: Evtl. noch Logger.getLogge Implementieren  .
 */
public class SensorEventSource implements SourceFunction<SensorEvent> {
    private static final Random RANDOM = new Random();
    private static final String[] SENSOR_IDS = {"sensor-1", "sensor-2", "sensor-3"};

    private volatile boolean running = true;

    /**
     * Generiert zufällige Sensorereignisse und gibt sie über den bereitgestellten Kontext aus.
     *
     * @param ctx der Kontext, über den die Sensorereignisse ausgegeben werden.
     * @throws Exception wenn ein Fehler beim Generieren oder Sammeln von Sensorereignissen auftritt.
     */
    @Override
    public void run(SourceContext<SensorEvent> ctx) throws Exception {
        while (running) {
            // Wählen Sie eine zufällige Sensor-ID aus und generieren Sie einen zufälligen Wert und ein zufälliges Ereigniszeitstempel.
            String sensorId = SENSOR_IDS[RANDOM.nextInt(SENSOR_IDS.length)];
            double value = ThreadLocalRandom.current().nextInt(1, 100);
            long eventTime = System.currentTimeMillis() - RANDOM.nextInt(1000);

            // Sammeln Sie das generierte Sensorereignis und geben Sie es über den Kontext aus.
            ctx.collect(new SensorEvent(sensorId, value, eventTime));
            Thread.sleep(100);
        }
    }
    /**
     * Stoppt das Generieren von Sensorereignissen.
     */
    @Override
    public void cancel() {
        running = false;
    }
}