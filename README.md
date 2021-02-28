# spring-cloud-alibaba-demo

## 介绍

该项目为 Spring-Cloud 与 Spring-Cloud-Alibaba 与 Dubbo 技术整合的示例。

组件
- Spring Cloud Gateway 网关。作为一个API架构，用来保护、增强和控制对于API服务的访问。API网关是一个处于应用程序或服务（提供 REST API 接口服务）之前的系统，用来管理授权、访问控制和流量限制等，这样 REST API接口服务就被 API网关保护起来，对所有的调用者透明。
- Spring Cloud Alibaba Nacos 动态服务发现、配置管理和服务管理平台。快速构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。
- Apache Dubbo 远程调用服务。面向接口代理的高性能RPC调用，智能容错和负载均衡，服务自动注册和发现，高度可扩展能力，运行期流量调度，可视化的服务治理与运维。
- Spring Cloud Alibaba Sentinel 流量控制。是面向分布式服务架构的流量控制组件，主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护、热点防护等多个维度来帮助开发者保障微服务的稳定性。
- Spring Security 安全认证服务的框架。为基于J2EE企业应用软件提供了全面安全服务。
- todo

选型说明：
1. 使用 Spring-Cloud-Alibaba 的原因。
    1. Spring-Cloud 的部分技术依赖 Netflix 的开源项目，且部分项目已经停止更新进入仅维护阶段。
所以使用 Spring-Cloud-Alibaba 的部分组件来进行替换。
2. 使用 Dubbo 的原因。
    1. 原本 Spring-Cloud 的服务间通讯是通过使用对 Eureka 与 Ribbon 的封装整合后的 OpenFeign 来进行调用的。但是其本质是基于HTTP协议的。而 Dubbo 其底层属于基于Netty开发的RPC服务。从理论层面上来说效率可能会更高？（待研究和测试）
    2. 其次 Ribbon 也是 Netflix 的开源项目，不更新进入维护阶段。虽然说又有 LoadBalance 插件替代，但是依赖还在。
3. 使用 Sentinel 的原因。
    1. Hystrix 不更新进入维护阶段。所以需要准备替代品，而 Sentinel 能实现 Hystrix 的所有功能；

---

## 版本关系

[官方介绍](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

Spring-cloud与Spring-boot版本关系

Spring Cloud Version|Spring Cloud Alibaba Version|Spring Boot Version|当前
---|---|---|---
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
# 1. 下载Nacos-1.4.1.zip
# 2. 解压
unzip x Nacos-1.4.1.zip 
# 3. 进入
cd nacos/bin 
# 4. 启动服务。单点
sh startup.sh -m standalone
# 5. 关闭
sh shutdown.sh

```

#### Dubbo

[官方文档](https://dubbo.apache.org/zh/docs/v2.7/user/)

推荐用法：
1. 在 Provider 端尽量多配置 Consumer 端属性；
    1. 作服务的提供方，比服务消费方更清楚服务的性能参数，如调用的超时时间、合理的重试次数等；
    2. 在 Provider 端配置后，Consumer 端不配置则会使用 Provider 端的配置，即 Provider 端的配置可以作为 Consumer 的缺省值。否则，Consumer 会使用 Consumer 端的全局设置，这对于 Provider 是不可控的，并且往往是不合理的；
2. 在 Provider 端配置合理的 Provider 端属性；
    1. threads：服务线程池大小；
    2. executes：一个服务提供者并行执行请求上限，即当 Provider 对一个服务的并发调用达到上限后，新调用会阻塞，此时 Consumer 可能会超时。在方法上配置 dubbo:method 则针对该方法进行并发限制，在接口上配置 dubbo:service，则针对该服务进行并发限制；
3. 配置优先级 方法级配置 -> 接口级配置 -> 配置文件级配置；消费端配置 -> 服务端配置；

所以一些方法的特殊需求请一定在方法级上进行配置（注解也可以）。如果查询接口，幂等所以支持重试；但是新增接口就不支持幂等，不建议支持重试功能（且最大好在新增前进行数据存在的判断，主键啊redis锁等）；

[官方推荐用法说明](https://dubbo.apache.org/zh/docs/v2.7/user/recommend/)


dubbo控制台使用：

```bash
# 下载控制台项目代码
git clone https://github.com/apache/dubbo-admin.git

# 修改注册中心信息
vim dubbo-admin-server/src/main/resources/application.properties

# 打包
mvn clean package -Dmaven.test.skip=true

# 运行
java -Dserver.port=8060 -jar dubbo-admin-distribution/target/dubbo-admin-0.3.0-SNAPSHOT.jar
```

默认账户：root/root

todo

#### Sentinel

[官方介绍-包含了主流框架的适配](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)

[Spring cloud Alibaba的整合说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)

控制台的启动

下载 jar 包：`sentinel-dashboard.jar`

运行控制台
```bash
java -Dserver.port=8050 -Dcsp.sentinel.dashboard.server=localhost:8050 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```

默认账户：sentinel/sentinel

todo

---

## 模块介绍

- common-entity
- common-api
- user-server
- main-web-server