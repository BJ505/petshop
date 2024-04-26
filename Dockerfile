FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/petshop-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_JY40LQE4N7I860UD /app/oracle_wallet
EXPOSE 9090

CMD [ "java", "-jar", "app.jar" ]