# non-blocking-io vs blocking-io
<img src="images/overall-structure.drawio.png" alt="overall structure"  width="600"/><br>
---
#### Rresouce Limit Config Example
```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <configuration>
    <jvmArguments>-Xms256m -Xmx512m -XX:ActiveProcessorCount=2</jvmArguments>
  </configuration>
</plugin>
```
#### Mongodb
```
mongodb docker compose file `mongodb/mongodb-docker-compose.yaml`
```

#### Run Locust Docker with lode test script
```
cd <PATH>/locust
```
Run specific script. (eg; springboot Mono load test with `Mongodb`)
```
docker run -p 8089:8089 -v $(pwd):/mnt/locust locustio/locust -f /mnt/locust/reactive_process.py
```
---
# load test result for each process
All of result are tested by 2000 users, ramp up period 10 sec, durration 10 min.

## Taditional
<img src="images/traditional_process-2000.jpg" width="1100" />

## CompletableFuture
<img src="images/future_process-2000.jpg" width="1100" />

## Reactive/webflux
<img src="images/reactive_process-2000.jpg" width="1100" />
