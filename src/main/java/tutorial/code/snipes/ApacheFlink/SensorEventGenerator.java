package tutorial.code.snipes.ApacheFlink;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * Änderung von Patrick Wiget am  09.Mai 2023: Hinzufügen einer neuen Funktion plus Text Beschreibung.
 /**
 * Diese Klasse generiert einen Datenstrom von Sensorereignissen, gruppiert sie nach Sensor-ID
 * und berechnet Durchschnittswerte innerhalb eines Zeitfensters.
 * TODO: Ich muss noch Junit 5 Demo Test schreiben.
 * TODO: Fehlerbehandlung fehlen.
 * TODO: Evtl. noch Logger.getLogge Implementieren.
 */
public class SensorEventGenerator {

    public static void main(String[] args) throws Exception {
        // Erstellen Sie eine StreamExecutionEnvironment-Instanz
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Erstellen Sie eine Datenstromquelle für Sensorereignisse und fügen Sie TimeStamps und Watermarks hinzu.
        DataStream<SensorEvent> sensorStream = env.addSource(new SensorEventSource())
                .assignTimestampsAndWatermarks(new SensorEventTimestampExtractor());

        // Gruppieren Sie den Datenstrom nach Sensor-ID und wenden Sie ein Tumbling Window an.
        // Führen Sie eine Reduktion durch, um die Durchschnittswerte der Sensordaten innerhalb des Fensters zu berechnen.
        sensorStream.keyBy(SensorEvent::getSensorId)
                .window(TumblingEventTimeWindows.of(Time.seconds(30)))
                .reduce(new ReduceFunction<SensorEvent>() {
                    @Override
                    public SensorEvent reduce(SensorEvent in, SensorEvent out) throws Exception {
                        return new SensorEvent(
                                in.getSensorId(),
                                (in.getValue() + out.getValue()) / 2,
                                Math.max(in.getEventTime(), out.getEventTime()));
                    }
                })
                .print();

        // Starten Sie den Stream-Job
        env.execute("Sensor Event Stream");
    }
}