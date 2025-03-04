package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.Event;
import java.util.List;


public class AnyEventProcessor implements EventProcessor {
    private final List<EventProcessor> processors;

    public AnyEventProcessor(List<EventProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public void processEvent(Event event){
        for (EventProcessor processor: processors) {
            processor.processEvent(event);
        }
    }
}