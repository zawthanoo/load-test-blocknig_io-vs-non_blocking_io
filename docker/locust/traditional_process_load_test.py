#docker run -p 8089:8089 -v $(pwd):/mnt/locust locustio/locust -f /mnt/locust/traditional_process_load_test.py



from locust import HttpUser, task, constant
import json
import uuid
import random, string

class cacheService(HttpUser):

    wait_time = constant(1)
    ids = []

    @task
    def sendMessage(self):
        characters = string.ascii_letters + string.digits
        testMessage = ''.join(random.choice(characters) for _ in range(15))
        payload = {
            "messageId":  str(uuid.uuid4()),
            "message": testMessage
        }
        headers = {'content-type': 'application/json'}
        response = self.client.post("/loadtest/traditional/process", data=json.dumps(payload),headers=headers)
        if response.status_code == 200:
            out = response.json()
            # message = out[response]
            # self.message.append(message)            
                


