package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.OneLightAction;
import ru.sbt.mipt.oop.smarthome.Light;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

public class LightEventProcessor implements EventProcessor{
    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(Event event) {
        if (!isLightEvent(event)) return;

        SensorEvent sensorEvent = (SensorEvent) event;

        Action setLight = new OneLightAction(sensorEvent.getObjectId(), getLightState(event));
        smartHome.execute(setLight);
    }

    private boolean getLightState(Event event){
        return event.getType().equals(EventType.LIGHT_ON);
    }

    private boolean isLightEvent(Event event) {
        return (event.getType().equals(EventType.LIGHT_ON) || event.getType().equals(EventType.LIGHT_OFF));
    }
}
