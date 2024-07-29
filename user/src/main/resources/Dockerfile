FROM centos:7
MAINTAINER 1391121874@qq.com

# 安装打包必备软件
RUN yum install -y wget unzip telnet lsof net-tools bind-utils

# 准备 JDK/Tomcat 系统变量
ENV JAVA_HOME /usr/lib/jvm/java
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV ADMIN_HOME /home/admin

# 下载安装 OpenJDK
RUN yum -y install java-1.8.0-openjdk-devel
RUN mkdir -p /home/admin/app/
# 拷贝jar，修改成自己项目的jar包，路径相对根目录
COPY app/user-0.0.1-SNAPSHOT.jar  /home/admin/app/user-0.0.1-SNAPSHOT.jar
# 增加容器内中⽂支持
ENV LANG="en_US.UTF-8"
# 增强 Webshell 使⽤体验
ENV TERM=xterm

# 将启动命令写入启动脚本 start.sh
RUN mkdir -p ${ADMIN_HOME}

RUN echo 'java -jar  ${JAVA_OPTS} /home/admin/app/user-0.0.1-SNAPSHOT.jar'> /home/admin/start.sh && chmod +x /home/admin/start.sh
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
WORKDIR ${ADMIN_HOME}
CMD ["/bin/bash", "/home/admin/start.sh"]