package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

public class Alarm implements Actionable {
    private AlarmState state;
    private final String code;

    public Alarm(String code) {
        this.state = new DeactivatedState(this);
        this.code = code;
    }

    void changeState(AlarmState state) {
        this.state = state;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void turnToAlert() {
        state.turnToAlert();
    }

    public boolean isActivated() {
        return (state instanceof ActivatedState);
    }

    public boolean isDeactivated() {
        return (state instanceof DeactivatedState);
    }

    public boolean isAlert() {
        return (state instanceof AlertState);
    }

    public void react(AlarmReactor alarmReactor) {
        state.react(alarmReactor);
    }

    public boolean isCorrectCode(String code) {
        return this.code.equals(code);
    }
}
