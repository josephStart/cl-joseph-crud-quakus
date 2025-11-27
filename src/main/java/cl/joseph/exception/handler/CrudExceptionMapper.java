package cl.joseph.exception.handler;

import cl.joseph.exceptions.CrudException;
import cl.joseph.model.dto.NotificationDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.ZonedDateTime;
import java.util.UUID;

@Provider
public class CrudExceptionMapper implements ExceptionMapper<CrudException> {

    @Override
    public Response toResponse(CrudException exception) {
        NotificationDto notification = NotificationDto.builder()
                .id(UUID.randomUUID())
                .timestamp(ZonedDateTime.now())
                .message(exception.getMessage())
                .build();
        return Response.serverError().entity(notification).build();
    }
}
