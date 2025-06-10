package org.example.ai.tool;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@Configuration
public class ToolRegistrar {

    @Bean
    public ToolCallback[] allTools() {
        return ToolCallbacks.from(
            new MessageTool()
        );
    }
}
