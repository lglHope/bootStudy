#基础镜像，如果本地没有，会从远程仓库拉取。
FROM java:8

# 将jar包添加到容器中并更名为app.jar
ADD *.jar app.jar

#拷贝本地文件到镜像中#复制上下文目录下的target/demo-1.0.0.jar 到容器里
#COPY ${JAR_FILE} penguin-log-web.jar

#指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
