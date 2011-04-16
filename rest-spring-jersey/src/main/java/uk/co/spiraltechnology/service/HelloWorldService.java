package uk.co.spiraltechnology.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import uk.co.spiraltechnology.dto.MessageDTO;

@Path("/helloworld")
@Component
@Scope("request")
public class HelloWorldService {

    @GET
    @Produces("application/xml")
    public MessageDTO getMessage() {
        return new MessageDTO("Hello world!");
    }

    @POST
    @Consumes("application/xml")
    public void setMessage(MessageDTO messageDTO) {
        System.out.println("Received a POST with message '" + messageDTO.content +"'");
    }

}