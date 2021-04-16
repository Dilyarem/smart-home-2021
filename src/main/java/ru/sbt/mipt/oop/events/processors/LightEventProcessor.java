package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
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

        smartHome.execute((component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    updateLightState(light, getLightState(event));
                }
            }
        }));
    }

    private void updateLightState(Light light, boolean newState) {
        light.setOn(newState);
        System.out.println("Light " + light.getId() + " was turned " + (newState ? "on." : "off."));
    }

    private boolean getLightState(Event event){
        return event.getType().equals(EventType.LIGHT_ON);
    }

    private boolean isLightEvent(Event event) {
        return (event.getType().equals(EventType.LIGHT_ON) || event.getType().equals(EventType.LIGHT_OFF));
    }
}
