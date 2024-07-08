package com.hcl.userMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.userMicroservice.dao.service.MessageDaoService;
import com.hcl.userMicroservice.dao.service.UserProfileDaoService;
import com.hcl.userMicroservice.dto.MessageDto;
import com.hcl.userMicroservice.mapper.MessageMapper;
import com.hcl.userMicroservice.model.Message;
import com.hcl.userMicroservice.model.UserProfile;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageDaoService messageDaoService;

    private final UserProfileDaoService userProfileDaoService;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public MessageService(MessageDaoService messageDaoService, UserProfileDaoService userProfileDaoService, LoadBalancerClient loadBalancerClient) {
        this.messageDaoService = messageDaoService;
        this.userProfileDaoService = userProfileDaoService;
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

    // First 2
  //function used to create message from user side
    public MessageDto create(MessageDto messageDto) {
        if (!userProfileDaoService.isExistById(messageDto.getUserId())) {
            throw new RuntimeException("User Not found");
        }

        Message message = MessageMapper.toEntity(messageDto);
        Optional<UserProfile> optionalUserProfile = userProfileDaoService.getOneById(messageDto.getUserId());
        UserProfile userProfile = optionalUserProfile.get();

        message.setUserProfile(userProfile);
        userProfile.getMessages().add(message);

        Message savedMessage = messageDaoService.create(message);
        userProfileDaoService.update(userProfile);

        insertIntoOwnerMessage(messageDto.getUserId(), messageDto.getOwnerId(), messageDto.getContent());
        return MessageMapper.toDto(savedMessage);
    }

  //to edit a specific message contents 
    public MessageDto update(MessageDto messageDto) {
        if (!userProfileDaoService.isExistById(messageDto.getUserId())) {
            throw new RuntimeException("Owner Not found");
        }

        Message message = MessageMapper.toEntity(messageDto);
        Optional<UserProfile> optionalUserProfile = userProfileDaoService.getOneById(messageDto.getUserId());
        UserProfile userProfile = optionalUserProfile.get();

        message.setUserProfile(userProfile);

        Message savedMessage = messageDaoService.update(message);

        return MessageMapper.toDto(savedMessage);
    }

  //to delete a message permanently
    public boolean delete(int id) {
        return messageDaoService.delete(id);
    }

    // First 3
    public void insertIntoOwnerMessage(int userId, int ownerId, String content) {
        ServiceInstance userService = loadBalancerClient.choose("OWNER-SERVICE");
        String baseUrl = userService.getUri().toString();
        System.out.println(baseUrl);

        String urlPath = baseUrl + "/api/rentAPLace/v1/messages/" + "addMessageIntoOwner/" + ownerId + "/" + userId + "/" + content;
        System.out.println(urlPath);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                urlPath,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<Void>() {
                }
        );
        System.out.println(responseEntity);
        return;
    }

    // Second 5
    public void addMessage(int ownerId, int userId, String content) {
        Optional<UserProfile> optionalUserProfile = userProfileDaoService.getOneById(userId);
        UserProfile userProfile = optionalUserProfile.get();

        Message message = new Message();
        message.setUserProfile(userProfile);
        message.setOwnerId(ownerId);
        message.setContent(content);

        userProfile.getMessages().add(message);

        messageDaoService.create(message);
        userProfileDaoService.update(userProfile);
    }
}
