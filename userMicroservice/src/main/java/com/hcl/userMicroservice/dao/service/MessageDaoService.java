package com.hcl.userMicroservice.dao.service;

import org.springframework.stereotype.Service;

import com.hcl.userMicroservice.model.Message;
import com.hcl.userMicroservice.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageDaoService {

    private final MessageRepository messageRepository;

    public MessageDaoService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public boolean isExistById(int id) {
        final Optional<Message> optionalUserProfile = messageRepository.findById(id);
        return optionalUserProfile.isPresent();
    }
    public Optional<Message> getOneById(int id) {
        return messageRepository.findById(id);
    }

    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public Message update(Message message) {
        return messageRepository.save(message);
    }

    public boolean delete(int id) {
        messageRepository.deleteById(id);
        return true;
    }

}

