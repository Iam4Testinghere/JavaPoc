package tutorial.code.snipes.ApacheFlink;

import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * Diese Klasse enthält Tests für die SensorEventSource-Klasse.
 */
public class SensorEventSourceTest {
    private SensorEventSource sensorEventSource;
    private SourceContext<SensorEvent> sourceContext;

    /**
     * Initialisiert die SensorEventSource und das SourceContext-Objekt.
     */
    @BeforeEach
    public void setUp() {
        sensorEventSource = new SensorEventSource();
        sourceContext = Mockito.mock(SourceContext.class);
    }

    /**
     * Beendet die SensorEventSource.
     */
    @AfterEach
    public void tearDown() {
        sensorEventSource.cancel();
    }

    /**
     * Testet die run()-Methode der SensorEventSource-Klasse.
     *
     * Die Methode startet die SensorEventSource in einem separaten Thread, um den Test nicht zu blockieren.
     * Es wird eine Wartezeit von 2000 Millisekunden eingesetzt, damit genug Events generiert werden.
     * Anschließend wird die SensorEventSource beendet und der Thread beendet.
     * Es wird geprüft, dass die collect()-Methode mindestens einmal und mindestens 10-mal aufgerufen wird.
     */
    @Test
    public void testRunMethod() throws Exception {
        Thread sourceThread = new Thread(() -> {
            try {
                sensorEventSource.run(sourceContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        sourceThread.start();
        Thread.sleep(2000); // Wartezeit erhöht, um sicherzustellen, dass mindestens 10 Ereignisse generiert werden.
        sensorEventSource.cancel();
        sourceThread.join();
        verify(sourceContext, atLeastOnce()).collect(any(SensorEvent.class));
        verify(sourceContext, atLeast(10)).collect(any(SensorEvent.class));
    }
}
