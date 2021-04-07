package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class CommandSender {
    private final SmartHome smartHome;

    public CommandSender(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void sendAllLight() {
        smartHome.execute((component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }));
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
