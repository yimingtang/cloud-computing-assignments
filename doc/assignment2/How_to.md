# 配置文件

MF1332057 唐毅明￼ MF1332086 张磊   MF1232037 李辉  

## 准备工作

请准备好如下环境，如未特别说明，均选用最新版本

1. 操作系统：Mac OS X Maverick/ Windows 7/ Ubuntu 12.04+（较新的Linux发行版） 
2. Maven3，建议3.0.5 [http://maven.apache.org/](http://maven.apache.org/)
3. Tomcat 7 [http://tomcat.apache.org](http://tomcat.apache.org)
4. JDK 7 [http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
5. MySQL 5.6 [http://www.mysql.com](http://www.mysql.com)
6. EclipseMyEclipse 10 或者 Intellij IDEA 13
7. Git 1.8.5 [http://git-scm.com/](http://git-scm.com/)


## 具体步骤

强烈建议下载本项目所在Git仓库的最新源码进行测试！项目地址为：[https://github.com/krafttuc/cloud-computing-assignments/](https://github.com/krafttuc/cloud-computing-assignments/)

关于Git和Github的使用请参阅：

1. https://help.github.com/
2. git-scm.com/book

如果无法使用Git，则请使用作业内的源码。当你获得源码后，请按如下步骤进行：
1. 进入pom.xml文件目录下，命令行下mvn eclipse:myeclispe2. MyEclipse导入该工程3. 数据库中导入doc_hub.sql4. 数据库中先添加测试用户 admin（用户名）/admin(密码），其他字段也一并填入
5. 
5. 在MyEclipse中配置Tomcat运行文件
6. Run

如有疑问，请参阅：http://docs.spring.io/docs/