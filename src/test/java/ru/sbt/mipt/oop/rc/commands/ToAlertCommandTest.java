package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.alarm.Alarm;

import static org.junit.Assert.*;

public class ToAlertCommandTest {

    @Test
    public void execute() {
        Alarm alarm = new Alarm("password");
        Command toAllertCommand = new ToAlertCommand(alarm);
        toAllertCommand.execute();
        assertTrue(alarm.isAlert());
    }
}