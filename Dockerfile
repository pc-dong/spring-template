FROM openjdk:17-oracle

RUN echo "Asia/Shanghai" > /etc/timezone

RUN mkdir -p /opt/app/

COPY ./api/build/libs/api.jar /opt/app/app.jar
COPY run.sh /opt/bin/

EXPOSE 8080
EXPOSE 8081


WORKDIR /opt/app

ENTRYPOINT ["sh", "/opt/bin/run.sh"]
