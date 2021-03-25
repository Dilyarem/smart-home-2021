package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.AlarmEventType;

public class AlarmEvent implements Event{
    private final AlarmEventType type;

    public String getCode() {
        return code;
    }

    private final String code;

    public AlarmEvent(AlarmEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public AlarmEventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
