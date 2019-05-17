FROM openjdk:8-jre
MAINTAINER "my first app"
LABEL Description="image for first app" Version="1.0.0"

ARG APP_NAME
ENV ENV_APP_NAME $APP_NAME
ENV JAVA_OPTIONS="-Xms256m -Xmx768m -Xss255k -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/app/logs -verbose:gc -Xloggc:/usr/app/logs/gc.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=5m"

RUN mkdir /usr/app/
WORKDIR /usr/app
ADD /mnote-$APP_NAME/target/mnote-$APP_NAME.jar /usr/app
#ADD lib /usr/app/lib

ADD preStart.sh /usr/app

RUN chmod 775 /usr/app/preStart.sh

RUN echo "appName=$APP_NAME  envAppName=$ENV_APP_NAME"

RUN echo "start... $APP_NAME ${APP_NAME}"


RUN echo "#!/bin/bash \n sh preStart.sh \n java ${JAVA_OPTIONS} -jar ./mnote-${APP_NAME}.jar" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

RUN cat ./entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]



#ENTRYPOINT sh preStart.sh && java -jar ./mnote-$APP_NAME.jar

#ENTRYPOINT ["/bin/sh", "-c" , "sh preStart.sh && exec java -jar ./mnote-$APP_NAME.jar " ]
