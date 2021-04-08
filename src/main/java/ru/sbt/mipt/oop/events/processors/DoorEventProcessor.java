package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.SmartHome;
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

        smartHome.execute((component -> {
            if (component instanceof Door) {
                Door door = (Door) component;
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    updateDoorState(door, getDoorState(event));
                }
            }
        }));
    }

    private void updateDoorState(Door door, boolean newState) {
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "opened." : "closed."));
    }

    private boolean getDoorState(Event event){
        return event.getType().equals(EventType.DOOR_OPEN);
    }

    private boolean isDoorEvent(Event event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

}
