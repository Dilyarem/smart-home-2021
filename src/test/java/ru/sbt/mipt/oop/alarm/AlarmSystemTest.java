package ru.sbt.mipt.oop.alarm;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmSystemTest {
    private final String code = "dilyarem";

    @Test
    public void activateSystem() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.getState().activate(code);
        assertEquals(AlarmStateType.ACTIVATED, alarmSystem.getState().getState());
    }

    @Test
    public void deactivateSystemWithCorrectCode() {
        AlarmSystem alarmSystem = new AlarmSystem(code);
        alarmSystem.getState().deactivate(code);
        assertEquals(AlarmStateType.DEACTIVATED, alarmSystem.getState().getState());
    }

    @Test
    public void deactivateSystemWithWrongCodeToAlert() {
        AlarmSystem alarmSystem = new AlarmSystem(code);
        alarmSystem.getState().deactivate("wrong");
        assertEquals(AlarmStateType.ALERT, alarmSystem.getState().getState());
    }

    @Test
    public void turnToAlert() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.getState().turnToAlert(code);
        assertEquals(AlarmStateType.ALERT, alarmSystem.getState().getState());
    }
}