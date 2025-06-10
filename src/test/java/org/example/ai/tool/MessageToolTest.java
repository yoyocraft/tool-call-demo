package org.example.ai.tool;

import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.example.ai.model.BitfreeMessagePrompt;
import org.example.ai.util.GsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
class MessageToolTest {
    MessageTool messageTool = new MessageTool();

    @Test
    void testQueryChatMessage() {
        String result = messageTool.queryChatMessage(1, 20);
        Assertions.assertNotNull(result);
        List<BitfreeMessagePrompt> messagePrompts = GsonUtil.fromJson(result, new TypeToken<>() {
        });
        Assertions.assertNotNull(messagePrompts);
        Assertions.assertFalse(messagePrompts.isEmpty());
    }
}

// Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme