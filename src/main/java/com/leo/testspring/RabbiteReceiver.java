package com.leo.testspring;

import com.leo.testspring.model.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbiteReceiver {
    @RabbitListener(queues = "date")
    public void processDate(String date) {
        System.out.println("Receiver ==================: " + date);
    }

    @RabbitListener(queues = "object")
    public void processObj(Book book) {
        System.out.println("Receiver ==================: " + book);
        System.out.println("Receiver ==================: " + book.getName());
        System.out.println("Receiver ==================: " + book.getDescription());
    }
}
