# security-admin

项目模块
- client 客户端
- system 服务端
- common 通用模块

system 目录结构说明
```shell
- system
  - config # 通用配置目录
  - module # 功能模块目录
    - security #认证鉴权 Spring Security
    - system   #系统功能
      - controller 
      - domain # vo 对象和普通 bean
      - entity # 数据库对象
      - mapper 
      - service

- common
  - exception # 统一异常处理
  - annotation # 自定义注解
  - aspect # 自定义注解切面
  - utils # 工具类
  
- client 
  - log # 客户端日志
  - config # 通用配置目录
  - entity
  - tasks # 定时任务
  - utils # 工具类
```



