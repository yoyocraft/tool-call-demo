package org.example.ai.api;

import lombok.RequiredArgsConstructor;
import org.example.ai.model.request.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final ChatClient chatClient;

    private final ToolCallback[] allTools;

    @RequestMapping(value = "/chat", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatRequest request) {
        Flux<String> content = chatClient
            .prompt(request.getPrompt())
            .tools(allTools)
            .stream()
            .content();
        return content.concatWith(Flux.just("\n[complete]"));
    }

}
