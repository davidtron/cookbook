package uk.co.spiraltechnology.dto;

import java.util.ArrayList;
import java.util.List;

import uk.co.spiraltechnology.domain.Message;

public class MessageConverter {

    public static MessageDTO toDTO(Message message) {
        return new MessageDTO(message.getContent());
    }

    public static MessageListDTO toDTO(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
        for (Message message : messages) {
            messageDTOs.add(new MessageDTO(message.getContent()));
        }
        return new MessageListDTO(messageDTOs);
    }

    public static Message toEntity(MessageDTO messageDTO) {
        return new Message(messageDTO.content);
    }

}