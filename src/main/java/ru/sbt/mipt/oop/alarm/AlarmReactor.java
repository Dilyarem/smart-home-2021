package ru.sbt.mipt.oop.alarm;

public interface AlarmReactor {
    void onAlarmActiveState();
    void onAlarmInactiveState();
    void onAlarmAlertState();
}
