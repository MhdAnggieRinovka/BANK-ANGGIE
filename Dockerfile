# Gunakan JDK 17 (sesuaikan dengan versi Java yang Anda pakai di Udemy)
FROM eclipse-temurin:21-jdk-alpine

# Folder kerja di dalam Docker
WORKDIR /app

# Copy file jar dari folder target ke dalam Docker dan beri nama app.jar
# Ganti 'target/*.jar' sesuai lokasi file jar Anda
COPY target/*.jar app.jar

EXPOSE 8080

# Jalankan aplikasinya
ENTRYPOINT ["java", "-jar", "app.jar"]