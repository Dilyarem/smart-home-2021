package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActivatedState;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.AlarmEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.AlarmEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor {
    private final SmartHome smartHome;

    public AlarmEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(AlarmEvent event) {
        smartHome.execute((component -> {
            if (component instanceof AlarmSystem) {
                AlarmSystem alarmSystem = (AlarmSystem) component;
                if (event.getType().equals(ALARM_ACTIVATE)) {
                    alarmSystem.getState().activate(event.getCode());
                }

                if (event.getType().equals(ALARM_DEACTIVATE)) {
                    alarmSystem.getState().deactivate(event.getCode());
                }
            }
        }));
    }
}
