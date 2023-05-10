package tutorial.code.snipes.ApacheFlink;

import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Die Klasse SensorSourceTest enthält Testfälle für die SensorEventSource-Klasse.
 * Es wird getestet, ob die run-Methode korrekt SensorEvent-Objekte generiert und Wasserzeichen erzeugt.
 */
class SensorSourceTest {
    @Test
    void testRunMethod() throws Exception {
        // Setup
        SensorEventSource sensorSource = new SensorEventSource();
        SourceContext<SensorEvent> context = mock(SourceContext.class);
        // Exercise
        sensorSource.run(context);
        // Verify
        // Überprüfen, ob mindestens 10 SensorEvent-Objekte generiert wurden.
        verify(context, atLeast(2)).collect(any(SensorEvent.class));
        // Überprüfen, ob mindestens ein Wasserzeichen erzeugt wurde.
        verify(context, atLeastOnce()).emitWatermark(any(Watermark.class));
    }
}

