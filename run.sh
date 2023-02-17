#!/bin/sh

if [ -z $JAVA_OPTS ];then
    JAVA_OPTS="-Xms512m -Xmx1024m"
fi

if [ -z $JAR_PATH ];then
    JAR_PATH="/opt/app"
fi

echo java $JAVA_OPTS -Dlog4j2.formatMsgNoLookups=true -Dspring.profiles.active=$ENV -Dlogging.file.name=/opt/app-provision-api/logs/app-provision-api.log -Djava.library.path=/usr/local/apr/lib/ -jar ${JAR_PATH}/*.jar

java $JAVA_OPTS -Dlog4j2.formatMsgNoLookups=true -Dspring.profiles.active=$ENV -Dlogging.file.name=/opt/app-provision-api/logs/app-provision-api.log -Djava.library.path=/usr/local/apr/lib/ -jar ${JAR_PATH}/*.jar