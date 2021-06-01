# SkyWalking Agent 配置
# 配置 Agent 名字。一般来说，我们直接使用 Spring Boot 项目的 `spring.application.name` 。
export SW_AGENT_NAME=main-web-server
# 配置 Collector 地址。
export SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800
# 配置链路的最大 Span 数量。一般情况下，不需要配置，默认为 300 。主要考虑，有些新上 SkyWalking Agent 的项目，代码可能比较糟糕。
#export SW_AGENT_SPAN_LIMIT=2000
# SkyWalking Agent jar 地址。
export JAVA_AGENT=-javaagent:/Users/kunda-liang/IdeaProjects/spring-cloud-alibaba-demo/docker/skywalking-agent.jar

# Jar 启动
java -jar $JAVA_AGENT ./target/main-web-server-0.0.1-SNAPSHOT.jar