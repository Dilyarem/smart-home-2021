package ru.sbt.mipt.oop.events.generators;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;

public class RandomEventGenerator implements EventGenerator {
    @Override
    public SensorEvent generateEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        EventType eventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }
}
