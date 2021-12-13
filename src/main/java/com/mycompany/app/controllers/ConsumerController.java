package com.mycompany.app.controllers;

import com.mycompany.app.services.ConsumerService;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {

    @GetMapping("/api/consume")
    public String loadPage(Model model) {

        // Initialize message attributes
        String key = "Empty";
        String value = "Empty";
        String partition = "Empty";
        String offset = "Empty";

        //Send message attributes to UI model
        model.addAttribute("key", key);
        model.addAttribute("value", value);
        model.addAttribute("partition", partition);
        model.addAttribute("offset", offset);

        // Return html page
        return "consume";
    }

    @GetMapping("/api/consume/consume-message")
    public String consumeMessage(Model model) {

        // Initialize message attributes
        String key = "Empty";
        String value = "Empty";
        String partition = "Empty";
        String offset = "Empty";

        //Check if message stack has messages inside
        if(ConsumerService.MESSAGES.size() >= 1){
            //Consume message from stack
            String message = ConsumerService.MESSAGES.get(0);
            String[] arrOfStr = message.split(" -- ");
            key = arrOfStr[0];
            value = arrOfStr[1];
            partition = arrOfStr[2];
            offset = arrOfStr[3];

            // Remove message from stack
            ConsumerService.MESSAGES.remove(0);
        }
        //Send message attributes to UI model
        model.addAttribute("key", key);
        model.addAttribute("value", value);
        model.addAttribute("partition", partition);
        model.addAttribute("offset", offset);

        // Return html page
        return "consume";
    }
}
