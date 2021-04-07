package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class LightEventProcessor implements EventProcessor{
    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isLightEvent(event)) return;

        smartHome.execute((component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                if (light.getId().equals(event.getObjectId())) {
                    updateLightState(light, getLightState(event));
                }
            }
        }));
    }

    private void updateLightState(Light light, boolean newState) {
        light.setOn(newState);
        System.out.println("Light " + light.getId() + " was turned " + (newState ? "on." : "off."));
    }

    private boolean getLightState(SensorEvent event){
        return event.getType().equals(SensorEventType.LIGHT_ON);
    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType().equals(SensorEventType.LIGHT_ON) || event.getType().equals(SensorEventType.LIGHT_OFF));
    }
}
