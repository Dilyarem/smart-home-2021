package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.adapters.EventProcessorAdapter;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.events.processors.EventProcessor;
import ru.sbt.mipt.oop.events.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.events.processors.LightEventProcessor;
import ru.sbt.mipt.oop.homereader.SmartHomeFromJSReader;
import ru.sbt.mipt.oop.rc.RemoteControl;
import ru.sbt.mipt.oop.rc.RemoteControlImpl;
import ru.sbt.mipt.oop.rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.rc.commands.*;
import ru.sbt.mipt.oop.smarthome.SmartHome;

import java.util.List;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SmartHome smartHome() {
        return (new SmartHomeFromJSReader("smart-home-1.js")).read();
    }

    @Bean
    EventProcessor lightEventProcessor() {
        return new LightEventProcessor(smartHome());
    }

    @Bean
    EventProcessor getDoorEventProcessor() {
        return new DoorEventProcessor(smartHome());
    }

    @Bean
    EventProcessor getHallDoorEventProcessor() {
        return new HallDoorEventProcessor(smartHome());
    }

    @Bean
    Map<String, EventType> dict(){
        return Map.of(
                "LightIsOn", EventType.LIGHT_ON,
                "LightIsOff", EventType.LIGHT_OFF,
                "DoorIsOpen", EventType.DOOR_OPEN,
                "DoorIsClosed", EventType.DOOR_CLOSED
        );
    }


    @Bean
    SensorEventsManager sensorEventsManager(List<EventProcessor> eventHandlers) {
        SensorEventsManager manager = new SensorEventsManager();
        eventHandlers.forEach(handler -> manager.registerEventHandler(new EventProcessorAdapter(handler, dict())));
        return manager;
    }

    @Bean
    Alarm alarm(){
        return smartHome().getAlarm();
    }

    @Bean
    Command activateAlarmCommand() {
        return new ActivateAlarmCommand(alarm(), "password");
    }

    @Bean
    Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(smartHome(), "4");
    }

    @Bean
    Command toAlertCommand() {
        return new ToAlertCommand(alarm());
    }

    @Bean
    Command turnOffAllLightCommand() {
        return new TurnOffAllLightCommand(smartHome());
    }

    @Bean
    Command turnOnAllLightCommand() {
        return new TurnOnAllLightCommand(smartHome());
    }

    @Bean
    Command turnOnHallLightCommand() {
        return new TurnOnHallLightCommand(smartHome());
    }


    Map<String, Command> remoteControlDict(){
        return Map.of(
                "A", activateAlarmCommand(),
                "B", closeHallDoorCommand(),
                "C", toAlertCommand(),
                "D", turnOffAllLightCommand(),
                "1", turnOnHallLightCommand(),
                "2", turnOnAllLightCommand()
        );
    }

    @Bean
    RemoteControl remoteControl() {
        return new RemoteControlImpl(remoteControlDict());
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry registry = new RemoteControlRegistry();
        registry.registerRemoteControl(remoteControl(), "id");
        return registry;
    }

}
