# SecKill - 基于Spring Boot的Java商品秒杀系统
- ### 项目架构：该项目以前后端分离的形式，采用restful的设计风格。后端主要以Spring、SpringMVC、MyBatis框架为核心，数据库采用MySQL+Redis，以RabbitMQ队列实现异步下单。
- ### 功能描述：主要包括登录、商品、订单和秒杀模块。用户登录成功时,后台将用户信息(Cookie)存在Redis缓存中;进入系统后展示秒杀商品列表,可进入详情页秒杀商品;秒杀时请求入队,客户端立即得到响应,秒杀成功后自动创建订单。
- ### 实现细节：系统初始化时,把秒杀商品的库存写入Redis中;用户密码加salt和两次MD5加密;数据以VO类包装、自定义CodeMessage、全局异常处理;每次数据库的查询都会先从Redis中获取,若无再查Myqsl随后写入Redis中;并发情况下(多个用户同时秒杀一个商品),为保证库存不出现小于0的情况,在sql加入了限制条件。

# 运行截图
![1-1](https://github.com/cloris-cc/photos/blob/master/1-1.png)
![1-2](https://github.com/cloris-cc/photos/blob/master/1-2.png)
