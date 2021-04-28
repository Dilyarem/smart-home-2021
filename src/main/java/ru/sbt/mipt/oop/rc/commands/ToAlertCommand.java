package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class ToAlertCommand implements Command {
    private final Alarm alarm;

    public ToAlertCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.turnToAlert();
    }
}
