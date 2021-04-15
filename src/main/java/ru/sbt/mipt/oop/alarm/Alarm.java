package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Alarm implements Actionable, AlarmState {
    private AlarmState state;
    private String code;

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

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
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

    @Override
    public void react(AlarmReactor alarmReactor) {
        state.react(alarmReactor);
    }

    public boolean isCorrectCode(String code) {
        return this.code.equals(code);
    }
}
