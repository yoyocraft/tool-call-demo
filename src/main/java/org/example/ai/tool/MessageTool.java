package org.example.ai.tool;

import com.google.common.net.HttpHeaders;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.example.ai.constant.ServerConstant;
import org.example.ai.model.BitfreeMessagePrompt;
import org.example.ai.model.bitfree.MessageResponse;
import org.example.ai.model.bitfree.PageResult;
import org.example.ai.model.bitfree.Result;
import org.example.ai.util.GsonUtil;
import org.example.ai.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.util.ObjectUtils;

import static org.example.ai.constant.ServerConstant.SUCCESS_CODE;

/**
 * @author <a href="https://github.com/yoyocraft">yoyocraft</a>
 * @date 2025/06/10
 */
public class MessageTool {

    private static final Logger logger = LoggerFactory.getLogger(MessageTool.class);

    @Tool(description = "Query Bitfree chat messages")
    public String queryChatMessage(
        @ToolParam(description = "Page number, defaults to 1 if not provided") Integer pageNum,
        @ToolParam(description = "Page size, defaults to 20 if not provided (max 1000)") Integer pageSize
    ) {
        try {
            Map<String, String> headers = buildReqHeaders();
            Map<String, String> params = buildReqParams(pageNum, pageSize);

            Result<PageResult<MessageResponse>> result = doQueryMessage(params, headers);

            PageResult<MessageResponse> pageResult = result.getData();
            if (pageResult.getTotal() == 0) {
                return "No chat records found in Bitfree";
            }

            List<MessageResponse> messages = pageResult.getList();
            List<BitfreeMessagePrompt> polishMessages = messages.stream()
                .map(BitfreeMessagePrompt::new)
                .toList();
            return GsonUtil.toJson(polishMessages);
        } catch (Exception e) {
            logger.error("Error querying chat messages", e);
            return String.format("Failed to call Bitfree query service. Exception:[%s]", e.getMessage());
        }
    }

    private Result<PageResult<MessageResponse>> doQueryMessage(
        Map<String, String> params,
        Map<String, String> headers
    ) throws Exception {
        CompletableFuture<String> respFuture = HttpUtil.sendGetRequest(
            ServerConstant.BITFREE_CHAT_URL,
            params,
            headers
        );

        String json = respFuture.get();
        Result<PageResult<MessageResponse>> result = GsonUtil.fromJson(json, new TypeToken<>() {
        });
        if (!SUCCESS_CODE.equals(result.getCode())) {
            String errorMsg = String.format(
                "Bitfree query service returned an error. Status code: [%s], Error message: [%s]",
                result.getCode(),
                result.getMessage()
            );
            throw new UnsupportedOperationException(errorMsg);
        }
        return result;
    }

    private Map<String, String> buildReqParams(Integer pageNum, Integer pageSize) {
        return Map.of(
            ServerConstant.CHAT_PARAM_PAGE_NUM, String.valueOf(pageNum),
            ServerConstant.CHAT_PARAM_PAGE_SIZE, String.valueOf(pageSize)
        );
    }

    private Map<String, String> buildReqHeaders() {
        String token = getToken();
        return Map.of(
            HttpHeaders.COOKIE, String.format(ServerConstant.BITFREE_CHAT_TOKEN_VALUE_PATTERN, token)
        );
    }

    private String getToken() {
        String token = System.getProperty(ServerConstant.BITFREE_TOKEN_KEY);
        if (ObjectUtils.isEmpty(token)) {
            token = System.getenv(ServerConstant.BITFREE_TOKEN_KEY);
        }
        if (ObjectUtils.isEmpty(token)) {
            throw new UnsupportedOperationException(
                "Bitfree query service error: Token not configured"
            );
        }
        return token;
    }
}
