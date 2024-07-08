package com.hcl.userMicroservice.mapper;


import java.util.List;

import com.hcl.userMicroservice.dto.MessageDto;
import com.hcl.userMicroservice.model.Message;

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
        messageDto.setOwnerId(message.getOwnerId());
        messageDto.setUserId(message.getUserProfile().getId());

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
        message.setOwnerId(messageDto.getOwnerId());

        return message;
    }

}

