package com.mycompany.app.controllers;

import com.mycompany.app.services.ConsumerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {

    @GetMapping("/api/consume")
    public String loadPage( Model model) {
        return "consume";
    }

    @GetMapping("/api/consume/consume-message")
    public String consumeMessage(Model model) {
        String message = ConsumerService.consume();
        model.addAttribute("message", message);
        return "consume";
    }
}
