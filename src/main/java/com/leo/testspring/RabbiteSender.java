package com.leo.testspring;

import com.leo.testspring.model.Book;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbiteSender {

    @Autowired
    private Queue dateQueue;

    @Autowired
    private Queue objQueue;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "now date ".concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date()));
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend(dateQueue.getName(), context);
    }

    public void sendObj(Book book) {
        System.out.println("Sender : " + book);
        rabbitTemplate.convertAndSend(objQueue.getName(), book);
    }
}
