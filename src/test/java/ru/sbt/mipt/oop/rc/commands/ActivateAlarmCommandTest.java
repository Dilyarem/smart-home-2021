package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.alarm.Alarm;

import static org.junit.Assert.assertTrue;

public class ActivateAlarmCommandTest {

    @Test
    public void execute() {
        Alarm alarm = new Alarm("password");
        Command activateCommand = new ActivateAlarmCommand(alarm, "password");
        activateCommand.execute();
        assertTrue(alarm.isActivated());
    }
}