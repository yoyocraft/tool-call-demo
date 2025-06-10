package org.example.ai.model.request;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@Getter
@Setter
public class ChatRequest implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String prompt;
}
