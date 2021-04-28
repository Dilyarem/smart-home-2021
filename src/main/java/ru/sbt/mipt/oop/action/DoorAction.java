package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.smarthome.Door;

public class DoorAction implements Action{
    private final String objectId;
    private final boolean isToOpen;

    public DoorAction(String objectId, boolean isToOpen) {
        this.objectId = objectId;
        this.isToOpen = isToOpen;
    }

    @Override
    public void act(Object component) {
        if (component instanceof Door) {
            Door door = (Door) component;
            if (door.getId().equals(objectId)) {
                updateDoorState(door, isToOpen);
            }
        }
    }

    private void updateDoorState(Door door, boolean newState) {
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "opened." : "closed."));
    }
}
