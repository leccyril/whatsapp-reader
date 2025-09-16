######################## STAGE 1 ###########################
FROM alpine/java:21-jdk as builder

WORKDIR /build
COPY . .

RUN mkdir -p /root/.m2

RUN ./mvnw install
RUN cp ./target/*.jar ./whatsapp-reader.jar

######################## STAGE 2 ###########################
FROM alpine/java:21-jre

RUN apk update && apk upgrade
WORKDIR /opt/app
COPY --from=builder /build/whatsapp-reader.jar whatsapp-reader.jar

USER 1000
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /opt/app/whatsapp-reader.jar"]