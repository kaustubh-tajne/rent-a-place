package com.hcl.ownerMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.ownerMicroservice.dao.service.MessageDaoService;
import com.hcl.ownerMicroservice.dao.service.OwnerDaoService;
import com.hcl.ownerMicroservice.dto.MessageDto;
import com.hcl.ownerMicroservice.exceptions.OwnerNotFoundException;
import com.hcl.ownerMicroservice.mapper.MessageMapper;
import com.hcl.ownerMicroservice.model.Message;
import com.hcl.ownerMicroservice.model.Owner;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageDaoService messageDaoService;

    private final OwnerDaoService ownerDaoService;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public MessageService(MessageDaoService messageDaoService, OwnerDaoService ownerDaoService, LoadBalancerClient loadBalancerClient) {
        this.messageDaoService = messageDaoService;
        this.ownerDaoService = ownerDaoService;
        this.loadBalancerClient = loadBalancerClient;
    }

    //to get the list of messages done 
    public List<MessageDto> getAll() {
        final List<Message> messageList = messageDaoService.getAll();
        return MessageMapper.toDto(messageList);
    }

    //to get a specific message using message id
    public MessageDto getOneById(int id) {
        final Optional<Message> optionalMessage = messageDaoService.getOneById(id);
        if(optionalMessage.isEmpty()) {
            throw new RuntimeException("Message not found");
        }
        return MessageMapper.toDto(optionalMessage.get());
    }

    // Second 2
  //function used to create message from owner side
    public MessageDto create(MessageDto messageDto) {
        if (!ownerDaoService.isExistById(messageDto.getOwnerId())) {
            throw new OwnerNotFoundException("Owner Not found");
        }

        Message message = MessageMapper.toEntity(messageDto);
        Optional<Owner> optionalOwner = ownerDaoService.getOneById(messageDto.getOwnerId());
        Owner owner = optionalOwner.get();

        message.setOwner(owner);
        owner.getMessages().add(message);

        Message savedMessage = messageDaoService.create(message);
        ownerDaoService.update(owner);

        insertIntoUserMessages(messageDto.getUserId(), messageDto.getOwnerId(), messageDto.getContent());
        return MessageMapper.toDto(savedMessage);
    }

  //to edit a specific message contents 
    public MessageDto update(MessageDto messageDto) {
        if (!ownerDaoService.isExistById(messageDto.getOwnerId())) {
            throw new RuntimeException("Owner Not found");
        }

        Message message = MessageMapper.toEntity(messageDto);
        Optional<Owner> optionalOwner = ownerDaoService.getOneById(messageDto.getOwnerId());
        Owner owner = optionalOwner.get();

        message.setOwner(owner);

        Message savedMessage = messageDaoService.update(message);

        return MessageMapper.toDto(savedMessage);
    }

  //to delete a message permanently
    public boolean delete(int id) {
        return messageDaoService.delete(id);
    }

    // First 5
    public void addMessage(int ownerId, int userId, String content) {
        Optional<Owner> optionalOwner = ownerDaoService.getOneById(ownerId);
        Owner owner = optionalOwner.get();

        Message message = new Message();
        message.setOwner(owner);
        message.setUserId(userId);
        message.setContent(content);

        owner.getMessages().add(message);

        messageDaoService.create(message);
        ownerDaoService.update(owner);

    }

    // Second 3
    public void insertIntoUserMessages(int userId, int ownerId, String content) {
        ServiceInstance userService = loadBalancerClient.choose("USER-SERVICE");
        String baseUrl = userService.getUri().toString();

        System.out.println(baseUrl);

        String urlPath = baseUrl + "/api/rentAPLace/v1/messages/" + "addMessageIntoUser/" + ownerId + "/" + userId + "/" + content;
        System.out.println(urlPath);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                urlPath,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<Void>() {
                }
        );
        System.out.println(responseEntity);
    }
}

