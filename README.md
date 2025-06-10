# Tool Calling Demo

这是一个基于 Spring Boot 3.x 和 Spring AI 的 AI 工具项目，集成了阿里云通义千问大模型。

## 环境要求

- JDK 21 或更高版本
- Maven 3.6 或更高版本
- 阿里云通义千问 API Key（或者其他平台的 API Key 和 Base Url）
- BitFree Token

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/yoyocraft/tool-call-demo.git
cd tool-call-demo
```

### 2. 配置环境变量

在运行项目之前，需要设置以下环境变量：

```bash
# 阿里云通义千问 API Key
export DASHSCOPE_API_KEY=your_api_key_here

# BitFree Token
export BITFREE_TOKEN=your_bitfree_token_here
```

### 3. 构建项目

```bash
mvn clean package
```

### 4. 运行项目

```bash
java -jar target/bitfree-tool-1.0.0.jar
```

或者使用 Maven 直接运行：

```bash
mvn spring-boot:run
```

## 项目配置

项目的主要配置文件位于 `src/main/resources/application.yaml`，主要配置项包括：

- 服务端口：8080
- 上下文路径：/api
- 通义千问模型：qwen-plus

### 自定义配置

你可以通过修改 `application.yaml` 文件来自定义以下配置：

```yaml
spring:
  ai:
    dashscope:
      # 自定义 API 基础 URL（可选）
      base-url: your_custom_base_url
      # 自定义模型（可选）
      chat:
        options:
          model: your_preferred_model
```

阿里云百炼可用的模型选项见 https://bailian.console.aliyun.com/?tab=model#/model-market

> ⚠️ **重要提示**：本项目依赖模型的 FunctionCalling 能力，请确保所选用的模型支持 FunctionCalling 功能。

## API 文档

项目集成了 Knife4j 接口文档，启动项目后可以通过以下地址访问：

- Swagger UI：http://localhost:8080/api/swagger-ui.html
- Knife4j 增强文档：http://localhost:8080/api/doc.html
- OpenAPI 规范：http://localhost:8080/api/v3/api-docs

## 项目依赖

主要依赖包括：

- Spring Boot 3.4.3
- Spring AI Alibaba 1.0.0-M6.1
- Knife4j 4.4.0
- Lombok
- Gson

## 开发说明

1. 项目使用 Maven 进行依赖管理和构建
2. 代码风格遵循 Checkstyle 规范，配置文件位于 `style/checkstyle.xml`
3. 项目使用 Lombok 简化代码，请确保 IDE 安装了 Lombok 插件

## 注意事项

1. 请确保在使用前正确配置了阿里云通义千问的 API Key 和 BitFree Token
2. 项目默认使用 qwen-plus 模型，可以在配置文件中修改为其他支持的模型
3. 所有 API 接口都以 `/api` 为前缀
4. 如果需要使用自定义的 API 基础 URL，可以在配置文件中设置 `spring.ai.dashscope.base-url`
5. 请确保所选用的模型支持 FunctionCalling 功能，否则可能导致功能异常

