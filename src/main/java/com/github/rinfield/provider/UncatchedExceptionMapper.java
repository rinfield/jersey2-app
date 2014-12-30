package com.github.rinfield.provider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class UncatchedExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory
        .getLogger(UncatchedExceptionMapper.class);

    @Override
    public Response toResponse(final Exception handlingEx) {
        log.error("uncatched exception", handlingEx);
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
