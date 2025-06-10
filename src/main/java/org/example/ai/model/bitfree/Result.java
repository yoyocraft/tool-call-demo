package org.example.ai.model.bitfree;

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
public class Result<T> implements Serializable {

    @Serial private static final long serialVersionUID = 7163242669595049214L;

    private T data;

    private Integer code;

    private String message;
}
