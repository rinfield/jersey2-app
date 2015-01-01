package com.github.rinfield.app.log;

import static org.apache.log4j.Level.DEBUG;
import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.INFO;
import static org.apache.log4j.Level.TRACE;
import static org.apache.log4j.Level.WARN;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Level;

public enum AppLogs implements EnumLogs {

    APPLICATION_BEGIN(INFO),

    ON_APPLICATION_EVENT(INFO),

    ON_REQUEST_EVENT(INFO),

    ON_CONTAINER_STARTUP(INFO),

    ON_CONTAINER_RELOAD(INFO),

    ON_CONTAINER_SHUTDOWN(INFO),

    SEVICE_INJECTED(TRACE),

    POST_CONSTRUCT(INFO),

    PRE_DESTROY(INFO),

    HEART_BEAT(INFO),

    HEART_BEAT_END(INFO),

    REQUESTED_USER_ID(INFO),

    UNCATCHED_EXCEPTION(ERROR),

    ERROR_LOG(ERROR),

    WARN_LOG(WARN),

    INFO_LOG(INFO),

    DEBUG_LOG(DEBUG),

    TRACE_LOG(TRACE), ;

    private Level level;

    private AppLogs(final Level level) {
        this.level = level;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public String getMessage() {
        return this.name();
    }

    @Override
    public String getMessage(final Object arg) {
        return this.name() + ": " + arg;
    }

    @Override
    public String getMessage(final Object... args) {
        return this.name()
            + ": "
            + Arrays.stream(args).map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}
