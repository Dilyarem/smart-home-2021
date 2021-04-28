package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.Door;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class CloseHallDoorAction implements Action{
    private final SmartHome smartHome;
    private final String objectId;

    public CloseHallDoorAction(SmartHome smartHome, String objectId) {
        this.smartHome = smartHome;
        this.objectId = objectId;
    }

    private void turnOffAllLight() {
        Action offAllLight = new AllLightAction(false);
        smartHome.execute(offAllLight);
    }

    private void closeIfDoorIsHall(Room hall, String doorId) {
        hall.execute((component -> {
            if (component instanceof Door) {
                Door door = (Door) component;
                if (door.getId().equals(doorId)) {
                    closeDoor(door);
                    turnOffAllLight();
                    CommandSender sender = new CommandSender(smartHome);
                    sender.sendAllLight();
                }
            }
        }));
    }

    private void closeDoor(Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
    }

    @Override
    public void act(Object component) {
        if (component instanceof Room) {
            Room room = (Room) component;
            if (!room.getName().equals("hall")) {
                return;
            }

            closeIfDoorIsHall(room, objectId);
        }
    }
}
