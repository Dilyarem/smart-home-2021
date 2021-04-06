package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor{
    private final SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private boolean isHallDoor(String id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id) && room.getName().equals("hall")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isHallDoorEvent(SensorEvent event) {
        return (event.getType().equals(DOOR_CLOSED) && isHallDoor(event.getObjectId()));
    }

    private void turnOffAllLight() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
            }
        }
        System.out.println("All light were turned off.");
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isHallDoorEvent(event)) return;

        CommandSender sender = new CommandSender(smartHome);
        sender.sendAllLight();
        turnOffAllLight();
    }
}
