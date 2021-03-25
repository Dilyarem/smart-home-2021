package ru.sbt.mipt.oop.events.processors;


import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmStateType;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.AlarmEventType;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;


public class AnyEventProcessor implements EventProcessor {
    private final SmartHome smartHome;
    private final DoorEventProcessor doorEventProcessor;
    private final LightEventProcessor lightEventProcessor;
    private final HallDoorEventHandler hallDoorEventHandler;
    private final AlarmEventProcessor alarmEventProcessor;

    public AnyEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.doorEventProcessor = new DoorEventProcessor(smartHome);
        this.lightEventProcessor = new LightEventProcessor(smartHome);
        this.hallDoorEventHandler = new HallDoorEventHandler(smartHome);
        this.alarmEventProcessor = new AlarmEventProcessor(smartHome);
    }



    @Override
    public void processEvent(Event event){
        if (event instanceof SensorEvent) {
            if (!smartHome.getAlarmState().equals(AlarmStateType.ACTIVATED)) {
                SensorEvent sensorEvent = (SensorEvent) event;
                lightEventProcessor.processEvent(sensorEvent);
                doorEventProcessor.processEvent(sensorEvent);
                hallDoorEventHandler.processEvent(sensorEvent);
            } else {
                alarmEventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, ""));
            }
        }

        if (event instanceof AlarmEvent) {
            AlarmEvent alarmEvent = (AlarmEvent) event;
            alarmEventProcessor.processEvent(alarmEvent);
        }
    }
}