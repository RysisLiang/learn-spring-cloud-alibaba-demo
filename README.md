# spring-cloud-alibaba-demo

## 介绍

#### 该项目为 Spring-Cloud 与 Spring-Cloud-Alibaba 技术整合的示例。

其中主要有两个分支：
- dev-openfeign/master
- dev-dubbo

主要是验证两种技术，在分布式应用中的使用、以及与其它组件的功能整合情况。

#### 组件
- Spring Cloud Gateway 网关。作为一个API架构，用来保护、增强和控制对于API服务的访问。API网关是一个处于应用程序或服务（提供 REST API 接口服务）之前的系统，用来管理授权、访问控制和流量限制等，这样 REST API接口服务就被 API网关保护起来，对所有的调用者透明。
- Spring Cloud Alibaba Nacos 动态服务发现、配置管理和服务管理平台。快速构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。
- Spring Cloud Alibaba Sentinel 流量控制。是面向分布式服务架构的流量控制组件，主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护、热点防护等多个维度来帮助开发者保障微服务的稳定性。
- Seata 分布式一致性中间件。
- SkyWalking todo

选型说明：
1. 使用 Spring-Cloud-Alibaba 的原因。Spring-Cloud 的部分技术依赖 Netflix 的开源项目，且部分项目已经停止更新进入仅维护阶段。
所以使用 Spring-Cloud-Alibaba 的部分组件来进行替换。

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

服务注册与发现、配置集中管理

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

流量控制中间件

[官方介绍-包含了主流框架的适配](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)

[Spring cloud Alibaba的整合说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)

控制台的启动

下载 jar 包：`sentinel-dashboard.jar`

运行控制台
```bash
java -Dserver.port=8050 -Dcsp.sentinel.dashboard.server=localhost:8050 -jar sentinel-dashboard-1.8.0.jar 
```

默认账户：sentinel/sentinel

#### Seata

分布式一致性中间件

服务端

```
# 如果使用nacos 集中管理配置
1. 从官方库中找到/script文件夹里面有相关你的配置说明其中config.txt文件复制到seata目录，nacos中的nacos-config.sh or nacos-config.py 复制到seata-server的conf目录；
2. config.txt 修改其中需要调整的参数。尤其是`service.vgroupMapping.my_test_tx_group=default` 这个参数
3. 执行初始化脚本：sh ${SEATAPATH}/script/config-center/nacos/nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t namespace -u username -w password

# 使用默认的本地配置文件
1. 修改配置文件，file.conf包括模式、数据库的地址、registry.conf注册中心的类型等；

# 启动TC服务，可以制动ip port
启动seata服务：sh ./bin/seata-server.sh
Options:
	--host, -h
	  The host to bind.
	  Default: 0.0.0.0
	--port, -p
	  The port to listen.
	  Default: 8091
	--storeMode, -m
	  log store mode : file、db
	  Default: file
	--help 
```

客户端

1. 引入依赖，要与服务端的版本统一；
2. 把服务端的file.conf、registry.conf放到resource下（使用集中配置，或者统一配置yml则只挪配置内容即可）；
3. 事务分组与指定的TC名称要一致；
4. AT模式直接使用注解即可；


#### SkyWalking

todo

---

## 模块介绍

- common 基础工具模块
- gateway-server 网关服务模块
- main-web-server 核心web服务模块
- user-server 用户服务模块
- order-server 订单服务模块
