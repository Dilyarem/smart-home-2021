package ru.sbt.mipt.oop.alarm;

interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void turnToAlert();
    void react(AlarmReactor alarmReactor);
}
