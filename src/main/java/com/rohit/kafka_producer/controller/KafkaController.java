package com.rohit.kafka_producer.controller;

import com.rohit.kafka_producer.service.KafkaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class KafkaController {

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
     try{
         kafkaPublisher.sendMsgToTopic(message);
         return ResponseEntity.ok("message published successfully");
     }catch (Exception ex){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
     }
    }
}
