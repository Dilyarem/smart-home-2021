package ru.sbt.mipt.oop.rc;


import ru.sbt.mipt.oop.rc.commands.Command;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private final Map<String, Command> buttonCodeToCommand;

    public RemoteControlImpl(Map<String, Command> buttonCodeToCommand) {
        this.buttonCodeToCommand = buttonCodeToCommand;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        buttonCodeToCommand.get(buttonCode).execute();
    }
}
