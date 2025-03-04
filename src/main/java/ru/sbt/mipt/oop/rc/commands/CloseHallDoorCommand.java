package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.action.CloseHallDoorAction;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public class CloseHallDoorCommand implements Command{
    private final SmartHome smartHome;
    private final String doorId;

    public CloseHallDoorCommand(SmartHome smartHome, String hallDoorId) {
        this.smartHome = smartHome;
        this.doorId = hallDoorId;
    }

    @Override
    public void execute() {
        smartHome.execute(new CloseHallDoorAction(smartHome, doorId));
    }
}
