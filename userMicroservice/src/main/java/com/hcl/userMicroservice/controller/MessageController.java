package com.hcl.userMicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hcl.userMicroservice.dto.MessageDto;
import com.hcl.userMicroservice.service.MessageService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rentAPLace/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //to get the list of messages done 
    @GetMapping
    private ResponseEntity<List<MessageDto>> getAll() {
        final List<MessageDto> result = messageService.getAll();
        return ResponseEntity.ok(result);
    }

  //to get a specific message using message id
    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDto> getOneById(@PathVariable int messageId) {
        MessageDto result = messageService.getOneById(messageId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    // First 1
    //function used to create message from user side
    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageDto messageDto) {
        final MessageDto result = messageService.create(messageDto);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }


    //to edit a specific message contents 
    @PutMapping
    public ResponseEntity<MessageDto> update(@RequestBody MessageDto messageDto) {
        final MessageDto result = messageService.update(messageDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

  //to delete a message permanently
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> delete(@PathVariable int messageId) {
        boolean isDeleted = messageService.delete(messageId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Second 4
    @PostMapping("/addMessageIntoUser/{ownerId}/{userId}/{content}")
    public ResponseEntity<?> addMessages(@PathVariable int ownerId, @PathVariable int userId, @PathVariable String content) {
        messageService.addMessage(ownerId, userId, content);
        return ResponseEntity.ok().build();
    }
}
