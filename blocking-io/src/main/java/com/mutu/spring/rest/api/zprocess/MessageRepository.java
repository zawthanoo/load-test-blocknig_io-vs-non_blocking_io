package com.mutu.spring.rest.api.zprocess;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mutu.spring.rest.api.dto.Message;


public interface MessageRepository extends MongoRepository<Message, String> {
	
}