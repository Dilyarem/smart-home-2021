package ru.sbt.mipt.oop.events.processors;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class EventProcessorTest {
    @Test
    public void closeOneDoorLeaveOthers() {
        //setup
        Door doorId1 = new Door(true, "1");
        Door doorId2 = new Door(true, "2");
        Door doorId3 = new Door(false, "3");
        Room room = new Room(Arrays.asList(),
                Arrays.asList(doorId1, doorId2, doorId3),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        anyEventProcessor.processEvent(new SensorEvent(EventType.DOOR_CLOSED, "1"));
        //verify
        assertFalse(doorId1.isOpen());
        assertTrue(doorId2.isOpen());
        assertFalse(doorId3.isOpen());
    }

    @Test
    public void openOneDoorLeaveOthers() {
        //setup
        Door doorId1 = new Door(false, "1");
        Door doorId2 = new Door(false, "2");
        Door doorId3 = new Door(true, "3");
        Room room = new Room(Arrays.asList(),
                Arrays.asList(doorId1, doorId2),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        anyEventProcessor.processEvent(new SensorEvent(EventType.DOOR_OPEN, "1"));
        //verify
        assertTrue( doorId1.isOpen());
        assertFalse(doorId2.isOpen());
        assertTrue( doorId3.isOpen());
    }

    @Test
    public void turnOffOneLightLeaveOthers() {
        //setup
        Light lightId1 = new Light("1", true);
        Light lightId2 = new Light("2", true);
        Light lightId3 = new Light("3", false);
        Room room = new Room(Arrays.asList(lightId1, lightId2, lightId3),
                Arrays.asList(),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        anyEventProcessor.processEvent(new SensorEvent(EventType.LIGHT_OFF, "1"));
        //verify
        assertEquals(false, lightId1.isOn());
        assertEquals(true, lightId2.isOn());
        assertEquals(false, lightId3.isOn());
    }

    @Test
    public void turnOnOneLightLeaveOthers() {
        //setup
        Light lightId1 = new Light("1", false);
        Light lightId2 = new Light("2", true);
        Light lightId3 = new Light("3", false);
        Room room = new Room(Arrays.asList(lightId1, lightId2, lightId3),
                Arrays.asList(),
                "kitchen");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        anyEventProcessor.processEvent(new SensorEvent(EventType.LIGHT_ON, "1"));
        //verify
        assertEquals(true, lightId1.isOn());
        assertEquals(true, lightId2.isOn());
        assertEquals(false, lightId3.isOn());
    }

    @Test
    public void closeHallTurnOffAll() {
        //setup
        Door doorId1 = new Door(true, "1");
        Light lightId1 = new Light("1", true);
        Light lightId2 = new Light("2", false);
        Light lightId3 = new Light("3", true);
        Room kitchen = new Room(Arrays.asList(lightId1, lightId2),
                Arrays.asList(),
                "kitchen");
        Room hall = new Room(Arrays.asList(lightId3),
                Arrays.asList(doorId1),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, hall));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        SensorEvent closeHall = new SensorEvent(EventType.DOOR_CLOSED, "1");
        anyEventProcessor.processEvent(closeHall);
        //verify
        assertEquals(false, lightId1.isOn());
        assertEquals(false, lightId2.isOn());
        assertEquals(false, lightId3.isOn());
        assertEquals(false, doorId1.isOpen());
    }

    @Test
    public void closeNotHallLeaveLight() {
        //setup
        Door doorId1 = new Door(true, "1");
        Light lightId1 = new Light("1", true);
        Light lightId2 = new Light("2", false);
        Light lightId3 = new Light("3", true);
        Room kitchen = new Room(Arrays.asList(lightId1, lightId2),
                Arrays.asList(),
                "kitchen");
        Room bedroom = new Room(Arrays.asList(lightId3),
                Arrays.asList(doorId1),
                "bedroom");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bedroom));
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        );
        AnyEventProcessor anyEventProcessor = new AnyEventProcessor(eventProcessors);
        //execute
        SensorEvent closeHall = new SensorEvent(EventType.DOOR_CLOSED, "1");
        anyEventProcessor.processEvent(closeHall);
        //verify
        assertEquals(true, lightId1.isOn());
        assertEquals(false, lightId2.isOn());
        assertEquals(true, lightId3.isOn());
        assertEquals(false, doorId1.isOpen());
    }
}