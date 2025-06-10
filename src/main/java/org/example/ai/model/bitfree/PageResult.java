package org.example.ai.model.bitfree;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@Getter
@Setter
public class PageResult<T> implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private List<T> list;

    private Integer total;
}
