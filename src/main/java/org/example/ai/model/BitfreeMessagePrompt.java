package org.example.ai.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.example.ai.model.bitfree.MessageResponse;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@Getter
@Setter
public class BitfreeMessagePrompt implements Serializable {

    private String username;
    private String content;
    private String quoteUsername;
    private String quoteContent;

    public BitfreeMessagePrompt() {
    }

    public BitfreeMessagePrompt(MessageResponse message) {
        this.username = message.getUserName();
        this.content = message.getContent();
        this.quoteUsername = message.getMentionedUserName();
        this.quoteContent = message.getMentionedContent();
    }
}
