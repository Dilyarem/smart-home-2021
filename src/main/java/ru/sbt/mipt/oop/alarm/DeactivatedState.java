package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlertMesenger;

public class DeactivatedState implements AlarmState {
    final Alarm alarm;

    public DeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if (alarm.isCorrectCode(code)) {
            alarm.changeState(new ActivatedState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void turnToAlert() {
        alarm.changeState(new AlertState(alarm));
        (new AlertMesenger()).send();
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmInactiveState();
    }
}
