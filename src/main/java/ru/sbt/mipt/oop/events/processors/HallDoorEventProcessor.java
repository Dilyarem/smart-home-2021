package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.AllLightAction;
import ru.sbt.mipt.oop.action.CloseHallDoorAction;
import ru.sbt.mipt.oop.smarthome.Door;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.EventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor{
    private final SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private boolean isDoorEvent(Event event) {
        return (event.getType().equals(DOOR_CLOSED));
    }



    @Override
    public void processEvent(Event event) {
        if (!isDoorEvent(event)) return;

        SensorEvent sensorEvent = (SensorEvent) event;

        Action closeHallDoor = new CloseHallDoorAction(smartHome, sensorEvent.getObjectId());
        smartHome.execute(closeHallDoor);
    }
}
