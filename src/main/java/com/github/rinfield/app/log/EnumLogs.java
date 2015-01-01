package com.github.rinfield.app.log;

import org.apache.log4j.Level;

public interface EnumLogs {

    Level getLevel();

    String getMessage();

    String getMessage(Object arg);

    String getMessage(Object... args);
}
