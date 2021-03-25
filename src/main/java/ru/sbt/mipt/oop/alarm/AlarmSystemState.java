package ru.sbt.mipt.oop.alarm;

public abstract class AlarmSystemState {
    protected final AlarmSystem alarmSystem;

    public AlarmSystemState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public abstract void activate(String code);
    public abstract void deactivate(String code);
    public abstract void turnToAlert(String code);
    public abstract AlarmStateType getState();
}
