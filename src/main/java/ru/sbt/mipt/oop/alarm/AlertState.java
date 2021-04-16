package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlertMesenger;

public class AlertState implements AlarmState {
    private Alarm alarm;

    public AlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.isCorrectCode(code)) {
            alarm.changeState(new DeactivatedState(alarm));
        }
    }

    @Override
    public void turnToAlert() {
        (new AlertMesenger()).send();
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmAlertState();
    }
}
