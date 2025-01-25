# non-blocking-io vs blocking-io

1. 🔵 `Taditional` - blocking io
2. 🟠 `Mono` - none blocking io
3. 🟢 `CompletableFuture` - none blocking io

## Sample process: load test result (User: 500, Rump Up: 10, 5 min)
<img src="images/process_01.png" alt="Taditional blocking IP" width="800" /><br>
<img src="images/process_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/process_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>


## IO process: load test result (User: 500, Rump Up: 10, 5 min)
<img src="images/ioprocess_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/ioprocess_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/ioprocess_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>

## mongodb process: load test result (User: 500, Rump Up: 10, 5 min)
<img src="images/mongo_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/mongo_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/mongo_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>

## External REST API Call: load test result (User: 500, Rump Up: 10, 5 min)
<img src="images/external_01.png" alt="Taditional blocking IO" width="800" /><br>
<img src="images/external_02.png" alt="Mono non blocking IO" width="800" /><br>
<img src="images/external_03.png" alt="CompletableFuture non blocking IO" width="800" /><br>
