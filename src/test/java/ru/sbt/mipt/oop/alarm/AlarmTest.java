package ru.sbt.mipt.oop.alarm;

import org.junit.Test;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.processors.AlarmEventProcessor;

import static org.junit.Assert.assertTrue;

public class AlarmTest {
    private final String code = "dilyarem";
    private final Alarm alarm = new Alarm(code);
    private final AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(alarm);


    private void activateAlarm(String code) {
        AlarmEvent event = new AlarmEvent(EventType.ALARM_ACTIVATE, code);
        alarmEventProcessor.processEvent(event);
    }


    private void deactivateAlarm(String code) {
        AlarmEvent event = new AlarmEvent(EventType.ALARM_DEACTIVATE, code);
        alarmEventProcessor.processEvent(event);
    }

    @Test
    public void alarmActivateWithCorrectCode() {
        activateAlarm("dilyarem");
        assertTrue(alarm.isActivated());
    }

    @Test
    public void alarmActivateWithWrongCode() {
        activateAlarm("dilyaaaaarem");
        assertTrue(alarm.isDeactivated());
    }

    @Test
    public void alarmDeactivateWithCorrectCode() {
        activateAlarm("dilyarem");
        deactivateAlarm("dilyarem");
        assertTrue(alarm.isDeactivated());
    }

    @Test
    public void alarmDeactivateWithWrongCode() {
        activateAlarm("dilyarem");
        deactivateAlarm("dilyaaarem");
        assertTrue(alarm.isAlert());
    }

}