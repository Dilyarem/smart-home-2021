package ru.sbt.mipt.oop;
import ru.sbt.mipt.oop.events.generators.RandomEventGenerator;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.homereader.SmartHomeFromJSReader;

import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = (new SmartHomeFromJSReader("smart-home-1.js")).read();
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome),
                new AlarmEventProcessor(smartHome.getAlarm())
        );
        // начинаем цикл обработки событий
        EventLoop eventLoop = new EventLoop(new EventProcessorDecorator(new AnyEventProcessor(eventProcessors),
                smartHome.getAlarm()), new RandomEventGenerator());
        eventLoop.startLoop();
    }
}
