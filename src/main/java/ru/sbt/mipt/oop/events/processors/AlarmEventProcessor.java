package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.EventType.*;

public class AlarmEventProcessor implements  EventProcessor{
    private final SmartHome smartHome;

    public AlarmEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(Event event) {
        if (!isAlarmEvent(event)) return;

        AlarmEvent alarmEvent = (AlarmEvent) event;

        smartHome.execute((component -> {
            if (component instanceof AlarmSystem) {
                AlarmSystem alarmSystem = (AlarmSystem) component;
                if (event.getType().equals(ALARM_ACTIVATE)) {
                    alarmSystem.getState().activate(alarmEvent.getCode());
                }

                if (event.getType().equals(ALARM_DEACTIVATE)) {
                    alarmSystem.getState().deactivate(alarmEvent.getCode());
                }
            }
        }));
    }

    private boolean isAlarmEvent(Event event) {
        return (event.getType().equals(ALARM_ACTIVATE) || event.getType().equals(ALARM_DEACTIVATE));
    }
}
