# Maven tarafından projeyi derlemek için resmi Maven imajını kullanın
FROM maven:3.8.4-openjdk-11 AS build

# Çalışma dizetini belirle
WORKDIR /usr/src/app

# Maven bağımlılıklarını kopyala ve proje dosyalarını kopyala
COPY pom.xml .
COPY src ./src

# Proje derleme
RUN mvn clean install -DskipTests

# Selenium Cucumber projesini kopyala
WORKDIR /usr/src/selenium-cucumber
COPY C:/Users/elif/IdeaProjects/NesineAutomation .

# Node.js için bir ikinci aşama başlatın
FROM node:14.17.1-alpine AS node-build

# Node.js uygulamasını kopyala
WORKDIR /usr/src/node-app
COPY D:/NesineDeneme .

# Node.js uygulamasını derle
RUN npm install
RUN npm run build

# Uygulama ve Node.js kodunu birleştirme
FROM openjdk:11-jre-slim

WORKDIR /usr/src/app

# Maven tarafından oluşturulan JAR dosyasını kopyala
COPY --from=build /usr/src/app/target/NesineAutomation-1.0-SNAPSHOT.jar .

# Selenium Cucumber ve Node.js uygulamalarını kopyala
COPY --from=build /usr/src/selenium-cucumber .
COPY --from=node-build /usr/src/node-app .

# Diğer dosya ve dizinleri kopyala
COPY drivers ./drivers
COPY .idea ./.idea

# Uygulama çalıştırma komutu
CMD ["java", "-jar", "NesineAutomation-1.0-SNAPSHOT.jar"]
