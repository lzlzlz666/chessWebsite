# 围棋网站开发

#### 介绍

本项目是J2EE课程的开发项目；后端采用Spring Boot + MyBatis；前端采用HTML + CSS + JavaScript；本项目适用于初学后端以及前端的同学学习！（注意：项目里jsp文件并没有什么作用，老师要求必须放几个--> *已经淘汰的技术）

#### 软件架构

软件架构说明：

- **后端**：Spring Boot、MyBatis
- **前端**：HTML、CSS、JavaScript

#### 使用教程

1. 克隆仓库：

   ```bash
   https://github.com/lzlzlz666/chessWebsite.git
   ```

2. 导入数据库：

   - 在数据库中执行 **chess_website.sql** 脚本来初始化数据库。

3. 如何配置?

       采用Springboot框架(引入mybatis)

    ​    `Application.java`里下面两项改成自己数据库的信息

    ```java
    spring.datasource.username= 你的数据库名字
    
    spring.datasource.password= 你的数据库密码
    ```

4. 启动项目：

   - 使用IDE导入项目，配置好数据库连接后，运行主类`Application.java`。

   - 在浏览器里输入localhost:8082

     端口号通过下面这行代码，可以自行配置

     ```java
     server.port= 你想要的端口号
     ```

#### 使用说明

1. 打开浏览器，访问项目主页：

   ```bash
   http://localhost:8080
   ```

2. 登录/注册用户以访问更多功能。

3. 了解围棋的基本规则并开始对战。

   

#### 参与贡献

​      浙江农林大学计算机专业大二学生（计算机223 lz👑）



#### 运行截图


![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610142902754.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143044528.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143055706.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143113396.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143144447.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143207795.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143225373.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143238536.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143303993.png)
![项目截图](https://github.com/lzlzlz666/chessWebsite/blob/master/show/image-20240610143318416.png)

.......等等
