#docker run -p 8089:8089 -v $(pwd):/mnt/locust locustio/locust -f /mnt/locust/future_process_.py



from locust import HttpUser, task, constant
import json
import uuid
import random, string

class cacheService(HttpUser):

    wait_time = constant(1)
    ids = []

    @task
    def process(self):
        characters = string.ascii_letters + string.digits
        testMessage = ''.join(random.choice(characters) for _ in range(15))
        payload = {
            "messageId":  str(uuid.uuid4()),
            "message": testMessage
        }
        headers = {'content-type': 'application/json'}
        response = self.client.post("/loadtest/future/process", data=json.dumps(payload),headers=headers)
        if response.status_code == 200:
            out = response.json()
            # message = out[response]
            # self.message.append(message)
    @task
    def mongotest(self):
        characters = string.ascii_letters + string.digits
        testMessage = ''.join(random.choice(characters) for _ in range(15))
        payload = {
            "messageId":  str(uuid.uuid4()),
            "message": testMessage
        }
        headers = {'content-type': 'application/json'}
        response = self.client.post("/loadtest/future/mongotest", data=json.dumps(payload),headers=headers)
        if response.status_code == 200:
            out = response.json()
            # message = out[response]
            # self.message.append(message)    
    @task
    def ioprocess(self):
        characters = string.ascii_letters + string.digits
        testMessage = ''.join(random.choice(characters) for _ in range(15))
        payload = {
            "messageId":  str(uuid.uuid4()),
            "message": testMessage
        }
        headers = {'content-type': 'application/json'}
        response = self.client.post("/loadtest/future/ioprocess", data=json.dumps(payload),headers=headers)
        if response.status_code == 200:
            out = response.json()
            # message = out[response]
            # self.message.append(message)            

    @task
    def external(self):
        characters = string.ascii_letters + string.digits
        testMessage = ''.join(random.choice(characters) for _ in range(15))
        payload = {
            "messageId":  str(uuid.uuid4()),
            "message": testMessage
        }
        headers = {'content-type': 'application/json'}
        response = self.client.post("/loadtest/future/external", data=json.dumps(payload),headers=headers)
        if response.status_code == 200:
            out = response.json()
            # message = out[response]
            # self.message.append(message)                    
                                                    
                


