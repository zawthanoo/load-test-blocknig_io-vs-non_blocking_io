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
docker run -p 8089:8089 -v $(pwd):/mnt/locust locustio/locust -f /mnt/locust/reactive_mongo_load_test.py
```
---
# load test result for each process
All of result are tested by 500 users, ramp up period 10 sec, durration 5 min.

1. 🔵 `Taditional` - blocking io
2. 🟠 `Mono` - none blocking io
3. 🟢 `CompletableFuture` - none blocking io

## Sample process
<img src="images/process_01.png" alt="Taditional blocking IP" width="800" /><br>
<img src="images/process_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/process_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>

## IO process
<img src="images/ioprocess_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/ioprocess_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/ioprocess_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>

## Mongodb process
<img src="images/mongo_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/mongo_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/mongo_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>

## External REST API Call
<img src="images/external_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/external_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/external_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>
