# Springboot-demo

## 1.安装环境

### 1.1 JDK

照着链接装 https://jingyan.baidu.com/article/7f41ececcc3367183d095cee.html

### 1.2 MAVEN

照着链接装 https://www.cnblogs.com/yw-ah/p/8508286.html

## 2.运行

1.git clone 下载代码后，在spring boot 目录下执行命名：mvn clean install。成功后在springboot-demo-web module中执行main方法，程序即可运行。

## 3.概述

项目分为4个module：web、service、model、dao。

### 3.1 web

web为整个项目的启动入口，在UserController中模拟了用户登录的过程。在SpringbootDemoWebApplicationTests中使用了单元测试验证了登录过程。其中涉及到的技术：[断言](https://cloud.tencent.com/developer/article/1036666)、[MockMvc](https://www.cnblogs.com/williamjie/p/9145165.html) 。

### 3.2 service

service编写了登录相关的处理逻辑。

### 3.3 model

model中存放了项目中需要的实体类。

### 3.4 dao

dao中的代码与数据库交互，使用了[mybatis](http://www.mybatis.cn/)。spring boot 与 mybatis 集成https://www.cnblogs.com/wangshen31/p/8744157.html。

### 3.5 数据库

本项目为了简便使用了 [h2](https://www.ibm.com/links?url=http%3A%2F%2Fwww.h2database.com%2Fh2.pdf)。基础介绍自行百度。

## 4.打包

在 spring-boot-web 目录下执行命令：mvn clean package 即可打包成功，执行命令：java -jar xxx.jar 即可运行jar包。xxx为你的jar包名。