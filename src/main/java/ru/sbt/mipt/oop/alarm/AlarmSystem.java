package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class AlarmSystem implements Actionable {
    private AlarmSystemState state;

    public AlarmSystem() {
        this.state = new DeactivatedState(this);
    }

    public AlarmSystem(String code) {
        this.state = new ActivatedState(this, code);
    }

    public void changeState(AlarmSystemState state) {
        this.state = state;
    }

    public AlarmSystemState getState() {
        return state;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
