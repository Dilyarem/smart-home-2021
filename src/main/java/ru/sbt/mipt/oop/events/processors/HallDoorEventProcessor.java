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

    private boolean isDoorEvent(SensorEvent event) {
        return (event.getType().equals(DOOR_CLOSED));
    }

    private void turnOffAllLight() {
        smartHome.execute((innComponent -> {
            if (innComponent instanceof Light) {
                Light light = (Light) innComponent;
                light.setOn(false);
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        }));
    }

    private void turnOffIfDoorIsHall(String doorId) {
        smartHome.execute((component -> {
            if (component instanceof Door) {
                Door door = (Door) component;
                if (door.getId().equals(doorId)) {
                    turnOffAllLight();
                    CommandSender sender = new CommandSender(smartHome);
                    sender.sendAllLight();
                }
            }
        }));
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isDoorEvent(event)) return;

        smartHome.execute((homeComponent -> {
            if (homeComponent instanceof Room) {
                Room room = (Room) homeComponent;
                if (!room.getName().equals("hall")) {
                    return;
                }

                turnOffIfDoorIsHall(event.getObjectId());
            }
        }));
    }
}
