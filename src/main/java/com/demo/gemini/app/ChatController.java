package com.demo.gemini.app;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    @Autowired
    private ChatModel chatModel;

    public ChatController(ChatClient.Builder builder){
         chatClient = builder.build();
    }

    @GetMapping("/chatModel")
    public ChatResponse generatedResponseUseChatModel(@RequestParam String query){
      return chatModel.call(new Prompt(query));
    }


    @GetMapping("/chatClient")
    public ChatResponse generateResponseUseChatClient(@RequestParam String query){
        return chatClient.prompt().user(query)
                .call()
                .chatResponse();
    }
}
