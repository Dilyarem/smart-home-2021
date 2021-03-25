package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlertMesenger;

public class ActivatedState extends AlarmSystemState{
    private final String code;

    public ActivatedState(AlarmSystem alarmSystem, String code) {
        super(alarmSystem);
        this.code = code;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            alarmSystem.changeState(new DeactivatedState(alarmSystem));
        } else {
            turnToAlert(this.code);
        }
    }

    @Override
    public void turnToAlert(String code) {
        alarmSystem.changeState(new AlertState(alarmSystem, this.code));
        (new AlertMesenger()).send();
    }

    @Override
    public AlarmStateType getState() {
        return AlarmStateType.ACTIVATED;
    }

}
