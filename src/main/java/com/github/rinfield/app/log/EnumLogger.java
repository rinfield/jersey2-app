package com.github.rinfield.app.log;

public interface EnumLogger<T extends EnumLogs> {
    String getName();

    void write(final T logEnum);

    void write(final T logEnum, final Object arg);

    void write(final T logEnum, final Object... args);

    void write(final T logEnum, final Throwable e);
}
