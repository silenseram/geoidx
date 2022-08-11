FROM gradle

RUN mkdir /app

COPY ./ /app

WORKDIR /app

RUN \
    gradle build &&\
    echo | ls /app/build/libs/*

#COPY /app/build/libs/spring-boot.jar /app/appilikeishn_servar.jar

ENTRYPOINT ["java", "-jar", "/app/build/libs/spring-boot.jar"]