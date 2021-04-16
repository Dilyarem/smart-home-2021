package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.generators.EventGenerator;
import ru.sbt.mipt.oop.events.processors.EventProcessor;

public class EventLoop {
    private final EventProcessor eventProcessor;
    private final EventGenerator eventGenerator;

    public EventLoop(EventProcessor eventProcessor, EventGenerator eventGenerator) {
        this.eventProcessor = eventProcessor;
        this.eventGenerator = eventGenerator;
    }

    public void startLoop() {
        SensorEvent event = eventGenerator.generateEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventProcessor.processEvent(event);
            event = eventGenerator.generateEvent();
        }
    }
}
