package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
