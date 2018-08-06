#!/usr/bin/python

import requests
import requests.auth
import base64
import sys

clientId = "adminclient"
password = "M0uD.mY@ "

if len(sys.argv) >= 2:
    clientId = sys.argv[1]
    password = sys.argv[2]


scope_encoded = base64.b64encode(clientId + ":" + password)
print "Token: " + scope_encoded

client_auth = requests.auth.HTTPBasicAuth(clientId, password)
post_data = {"grant_type": "password", "username": "john", "password": "123", "client_id": clientId}
headers = {"Authorization": "Basic " + scope_encoded, "Content-type": "application/x-www-form-urlencoded; charset=utf-8"}

response = requests.post("https://localhost:8081/oauth/token", auth=client_auth, data=post_data, headers=headers, verify=False)
print "[" + str(response.raw.read()) + "]"

print response.json()
