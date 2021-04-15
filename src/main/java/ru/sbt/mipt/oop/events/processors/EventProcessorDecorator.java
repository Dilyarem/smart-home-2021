package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.AlertMesenger;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmReactor;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

public class EventProcessorDecorator implements EventProcessor, AlarmReactor {
    private EventProcessor processor;
    private Alarm alarm;
    private Event event;

    public EventProcessorDecorator(EventProcessor processor, Alarm alarm) {
        this.processor = processor;
        this.alarm = alarm;
    }

    @Override
    public void onAlarmActiveState() {
        if (event instanceof SensorEvent) {
            alarm.turnToAlert();
            (new AlertMesenger()).send();
        } else {
            processor.processEvent(event);
        }
    }

    @Override
    public void onAlarmInactiveState() {
        processor.processEvent(event);
    }

    @Override
    public void onAlarmAlertState() {
        (new AlertMesenger()).send();
    }

    @Override
    public void processEvent(Event event) {
        this.event = event;
        alarm.react(this);
    }
}
