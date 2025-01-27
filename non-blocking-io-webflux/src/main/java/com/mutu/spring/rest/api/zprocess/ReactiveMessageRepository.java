package com.mutu.spring.rest.api.zprocess;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.mutu.spring.rest.api.dto.Message;


public interface ReactiveMessageRepository extends ReactiveMongoRepository<Message, String> {
	
}