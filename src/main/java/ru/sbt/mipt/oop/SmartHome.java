package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.AlarmStateType;
import ru.sbt.mipt.oop.alarm.AlarmSystem;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    private Collection<Room> rooms;
    private AlarmSystem alarmSystem = new AlarmSystem("password");

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public AlarmStateType getAlarmState() {
        return alarmSystem.getState().getState();
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        rooms.forEach(room -> room.execute(action));
        alarmSystem.execute(action);
    }
}
