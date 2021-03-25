package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlertMesenger;

public class DeactivatedState extends AlarmSystemState{

    public DeactivatedState(AlarmSystem alarmSystem) {
        super(alarmSystem);
    }

    @Override
    public void activate(String code) {
        alarmSystem.changeState(new ActivatedState(alarmSystem, code));
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void turnToAlert(String code) {
        alarmSystem.changeState(new AlertState(alarmSystem, code));
        (new AlertMesenger()).send();
    }

    @Override
    public AlarmStateType getState() {
        return AlarmStateType.DEACTIVATED;
    }
}
