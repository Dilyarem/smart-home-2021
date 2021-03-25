package ru.sbt.mipt.oop.events.processors;

import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmStateType;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.AlarmEventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EventProcessorWithAlarmTest {

    @Test
    public void openDoorInActivated() {
        //setup
        Door doorId1 = new Door(false, "1");
        Door doorId2 = new Door(false, "2");
        Door doorId3 = new Door(true, "3");
        Room room = new Room(Arrays.asList(),
                Arrays.asList(doorId1, doorId2),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(smartHome);
        //execute
        anyEventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1"));
        //verify
        assertEquals(false, doorId1.isOpen());
        assertEquals(true, smartHome.getAlarmState().equals(AlarmStateType.ALERT));
    }

    @Test
    public void openDoorInDeactivated() {
        //setup
        Door doorId1 = new Door(false, "1");
        Door doorId2 = new Door(false, "2");
        Door doorId3 = new Door(true, "3");
        Room room = new Room(Arrays.asList(),
                Arrays.asList(doorId1, doorId2),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(smartHome);
        //execute
        anyEventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "password"));
        anyEventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1"));
        //verify
        assertEquals(true, doorId1.isOpen());
        assertEquals(false, smartHome.getAlarmState().equals(AlarmStateType.ALERT));
    }
}