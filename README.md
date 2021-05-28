# spring-cloud-alibaba-demo

## 介绍

该项目为 Spring-Cloud 与 Spring-Cloud-Alibaba 技术整合的示例。

组件
- Spring Cloud Gateway 网关。作为一个API架构，用来保护、增强和控制对于API服务的访问。API网关是一个处于应用程序或服务（提供 REST API 接口服务）之前的系统，用来管理授权、访问控制和流量限制等，这样 REST API接口服务就被 API网关保护起来，对所有的调用者透明。
- Spring Cloud Alibaba Nacos 动态服务发现、配置管理和服务管理平台。快速构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。
- Spring Cloud Alibaba Sentinel 流量控制。是面向分布式服务架构的流量控制组件，主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护、热点防护等多个维度来帮助开发者保障微服务的稳定性。
- Seata 分布式一致性中间件。
- todo

选型说明：
1. 使用 Spring-Cloud-Alibaba 的原因。
    1. Spring-Cloud 的部分技术依赖 Netflix 的开源项目，且部分项目已经停止更新进入仅维护阶段。
所以使用 Spring-Cloud-Alibaba 的部分组件来进行替换。
3. 使用 Sentinel 的原因。
    1. Hystrix 不更新进入维护阶段。所以需要准备替代品，而 Sentinel 能实现 Hystrix 的所有功能；

---

## 版本关系

[官方介绍](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

Spring-cloud与Spring-boot版本关系

Spring Cloud Version|Spring Cloud Alibaba Version|Spring Boot Version|当前
---|---|---|---
Spring Cloud 2020.0.0|2021.1|2.4.2.RELEASE|no
Spring Cloud Hoxton.SR8|2.2.5.RELEASE|2.3.2.RELEASE|yes
Spring Cloud Greenwich.SR6|2.1.3.RELEASE|2.1.13.RELEASE|no
Spring Cloud Hoxton.SR3|2.2.1.RELEASE|2.2.5.RELEASE|no
Spring Cloud Hoxton.RELEASE|2.2.0.RELEASE|2.2.X.RELEASE|no

Spring-cloud-Alibaba与组件的版本关系

Spring Cloud Alibaba Version|Sentinel Version|Nacos Version|RocketMQ Version|Dubbo Version|Seata Version|当前
---|---|---|---|---|---|---
2.2.5.RELEASE|1.8.0|1.4.1|4.4.0|2.7.8|1.3.0|yes
2.2.3.RELEASE or 2.1.3.RELEASE or 2.0.3.RELEASE|1.8.0|1.3.3|4.4.0|2.7.8|1.3.0|no
2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE|1.7.1|1.2.1|4.4.0|2.7.6|1.2.0|no

---

## 环境搭建

#### Nacos

[官网文档](https://nacos.io/zh-cn/docs/what-is-nacos.html)

```
# 1. 下载nacos-server-1.4.1.zip
# 2. 解压
unzip x nacos-server-1.4.1.zip 
# 3. 进入
cd nacos-1.4.1/bin 
# 4. 启动服务。单点
sh startup.sh -m standalone
# 5. 关闭
sh shutdown.sh
# 控制台
http://127.0.0.1:8848/nacos
```

#### Sentinel

[官方介绍-包含了主流框架的适配](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)

[Spring cloud Alibaba的整合说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)

控制台的启动

下载 jar 包：`sentinel-dashboard.jar`

运行控制台
```bash
java -Dserver.port=8050 -Dcsp.sentinel.dashboard.server=localhost:8050 -jar sentinel-dashboard-1.8.0.jar 
```

默认账户：sentinel/sentinel

#### seata

首先修改配置文件，并初始化一些数据

```
1. 将上面的config.txt文件复制到seata目录，nacos中的nacos-config.sh  nacos-config.py复制到seata的conf目录；
2. 修改配置文件，包括模式、数据库的地址、注册中心的类型等；
3. todo
```

---

## 模块介绍

- common
- gateway-server
- main-web-server
- user-server
- order-server
