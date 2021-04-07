package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class Searher {
    private final SmartHome smartHome;

    public Searher(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public Light findLight(String id) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    return light;
                }
            }
        }
        return null;
    }

    public Door findDoor(String id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door;
                }
            }
        }
        return null;
    }
}
