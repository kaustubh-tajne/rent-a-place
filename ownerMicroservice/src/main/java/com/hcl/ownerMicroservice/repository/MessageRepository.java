package com.hcl.ownerMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ownerMicroservice.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}

