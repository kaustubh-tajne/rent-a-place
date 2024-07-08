package com.hcl.ownerMicroservice.mapper;


import java.util.List;

import com.hcl.ownerMicroservice.dto.MessageDto;
import com.hcl.ownerMicroservice.model.Message;

public class MessageMapper {


    public static List<MessageDto> toDto(List<Message> messageList) {
        return messageList.stream()
                .map(message -> toDto(message))
                .toList();
    }

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setOwnerId(message.getOwner().getId());
        messageDto.setUserId(message.getUserId());

        return messageDto;
    }

    public static List<Message> toEntity(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(messageDto -> toEntity(messageDto))
                .toList();
    }

    public static Message toEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        message.setUserId(messageDto.getUserId());

        return message;
    }
}
