package com.github.rinfield.app.log;

import java.util.function.Function;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger implements EnumLogger<AppLogs> {

    public static AppLogger of(final Class<?> clazz) {
        return new AppLogger(clazz);
    }

    private final Logger l;

    private AppLogger(final Class<?> clazz) {
        l = LoggerFactory.getLogger(clazz);
    }

    @Override
    public String getName() {
        return l.getName();
    }

    @Override
    public void write(final AppLogs logEnum) {
        doLog(logEnum, x -> x.getMessage());
    }

    @Override
    public void write(final AppLogs logEnum, final Object arg) {
        doLog(logEnum, x -> x.getMessage(arg));
    }

    @Override
    public void write(final AppLogs logEnum, final Object... args) {
        doLog(logEnum, x -> x.getMessage(args));
    }

    private void doLog(final AppLogs logEnum, final Function<AppLogs, String> f) {
        final Level level = logEnum.getLevel();
        if (level == Level.ERROR && l.isErrorEnabled()) {
            l.error(f.apply(logEnum));
        }
        if (level == Level.WARN && l.isWarnEnabled()) {
            l.warn(f.apply(logEnum));
        }
        if (level == Level.INFO && l.isInfoEnabled()) {
            l.info(f.apply(logEnum));
        }
        if (level == Level.DEBUG && l.isDebugEnabled()) {
            l.debug(f.apply(logEnum));
        }
        if (level == Level.TRACE && l.isTraceEnabled()) {
            l.trace(f.apply(logEnum));
        }
    }

    @Override
    public void write(final AppLogs logEnum, final Throwable e) {
        final Level level = logEnum.getLevel();
        if (level == Level.ERROR && l.isErrorEnabled()) {
            l.error(logEnum.getMessage(), e);
        }
        if (level == Level.WARN && l.isWarnEnabled()) {
            l.warn(logEnum.getMessage(), e);
        }
        if (level == Level.INFO && l.isInfoEnabled()) {
            l.info(logEnum.getMessage(), e);
        }
        if (level == Level.DEBUG && l.isDebugEnabled()) {
            l.debug(logEnum.getMessage(), e);
        }
        if (level == Level.TRACE && l.isTraceEnabled()) {
            l.trace(logEnum.getMessage(), e);
        }
    }
}
