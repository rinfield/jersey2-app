package com.github.rinfield.provider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.rinfield.app.log.AppLogger;
import com.github.rinfield.app.log.AppLogs;

@Provider
public class UncatchedExceptionMapper implements ExceptionMapper<Exception> {

    private static final AppLogger log = AppLogger
        .of(UncatchedExceptionMapper.class);

    @Override
    public Response toResponse(final Exception handlingEx) {
        log.write(AppLogs.UNCATCHED_EXCEPTION, handlingEx);
        handlingEx.printStackTrace();
        String responseInfo = handlingEx.getMessage();
        try (final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteStream)) {
            handlingEx.printStackTrace(printStream);
            responseInfo = byteStream.toString(StandardCharsets.UTF_8.name());
        } catch (final IOException e) {
            // ignore
        }
        return Response.serverError().entity(responseInfo).build();
    }
}
