package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmToAlertAction implements Action{
    @Override
    public void act(Object component) {
        if (component instanceof Alarm) {
            Alarm alarm = (Alarm) component;
            alarm.turnToAlert();
        }
    }
}
