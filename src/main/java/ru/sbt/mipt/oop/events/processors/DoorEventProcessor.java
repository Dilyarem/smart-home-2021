package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.DoorAction;
import ru.sbt.mipt.oop.smarthome.Door;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;

import static ru.sbt.mipt.oop.events.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.EventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(Event event) {
        if (!isDoorEvent(event)) return;

        SensorEvent sensorEvent = (SensorEvent) event;

        Action changeDoorState  = new DoorAction(sensorEvent.getObjectId(), getDoorState(event));
        smartHome.execute(changeDoorState);
    }



    private boolean getDoorState(Event event){
        return event.getType().equals(EventType.DOOR_OPEN);
    }

    private boolean isDoorEvent(Event event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

}
