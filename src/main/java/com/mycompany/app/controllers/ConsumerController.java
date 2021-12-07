package com.mycompany.app.controllers;

import com.mycompany.app.services.ConsumerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {

    @Value("${BOOTSTRAP_SERVERS}")
    private String bootstrap_servers;

    @Value("${TOPIC_NAME}")
    private String topic_name;

    @GetMapping("/api/consume")
    public String loadPage(Model model) {
        return "consume";
    }

    @GetMapping("/api/consume/consume-message")
    public String consumeMessage(Model model) {
        ConsumerService.consume(bootstrap_servers, topic_name);
        return "consume";
    }
}
