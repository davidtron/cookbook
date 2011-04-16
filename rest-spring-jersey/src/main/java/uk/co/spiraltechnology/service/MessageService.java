package uk.co.spiraltechnology.service;

import uk.co.spiraltechnology.dto.ExceptionDTO;
import uk.co.spiraltechnology.dto.MessageConverter;
import uk.co.spiraltechnology.dto.MessageDTO;
import uk.co.spiraltechnology.dto.MessageListDTO;
import uk.co.spiraltechnology.domain.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;

@Path("/messages/")
@Component
@Scope("request")
public class MessageService {

    private static List<Message> messages = new ArrayList<Message>() {{
        add(0, new Message("First message"));
        add(1, new Message("Second message message"));

    }} ;

    @GET
    @Produces("application/xml")
    public MessageListDTO getMessages() {
        return MessageConverter.toDTO(messages);
    }

    @GET
    @Produces("application/xml")
    @Path("/{index}")
    public Response getMessage(@PathParam("index") int index) {
        try {
            return Response.status(Status.OK).entity(
                    MessageConverter.toDTO(messages.get(index))).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }

    @POST
    @Consumes("application/xml")
    public Response addMessage(MessageDTO messageDTO) {
        Message entity = MessageConverter.toEntity(messageDTO);
        messages.add(entity);
        return Response.status(Status.OK).build();
    }

    @PUT
    @Consumes("application/xml")
    @Path("/{index}")
    public Response updateMessage(@PathParam("index") int index,
                                  MessageDTO messageDTO) {
        try {
            messages.set(index, MessageConverter.toEntity(messageDTO));
            return Response.status(Status.OK).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }

    @DELETE
    @Produces("application/xml")
    @Path("/{index}")
    public Response removeMessage(@PathParam("index") int index) {
        try {
            MessageDTO messageDTO = MessageConverter.toDTO(messages.get(index));
            messages.remove(index);
            return Response.status(Status.OK).entity(messageDTO).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }

}