package org.example.ai.model.bitfree;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
@Getter
@Setter
public class MessageResponse implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String userName;

    private Integer userLevel;

    private String avatar;

    private String content;

    private Date createTime;

    private Long mentionedUserId;

    private String mentionedUserName;

    private Long mentionedMessageId;

    private String mentionedContent;

    // 显示此用户为top几，备选数字为1-10和-1
    // -1代表不为top10里的排名
    private Integer top;
}
