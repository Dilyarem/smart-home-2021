package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.smarthome.Room;

public class HallLightAction implements Action{
    private final boolean isToOn;

    public HallLightAction(boolean isToOn) {
        this.isToOn = isToOn;
    }

    @Override
    public void act(Object component) {
        if (component instanceof Room) {
            Room room = (Room) component;
            if (!room.getName().equals("hall")) {
                return;
            }

            Action turnAllHallLight = new AllLightAction(true);
            room.execute(turnAllHallLight);
        }
    }
}
