package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.utils.Searher;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isDoorEvent(event)) return;

        Door targetDoor = (new Searher(smartHome)).findDoor(event.getObjectId());
        if (targetDoor != null) {
            updateDoorState(targetDoor, getDoorState(event));
        }
    }

    private void updateDoorState(Door door, boolean newState) {
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "opened." : "closed."));
    }

    private boolean getDoorState(SensorEvent event){
        return event.getType().equals(SensorEventType.DOOR_OPEN);
    }

    private boolean isDoorEvent(SensorEvent event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

}
