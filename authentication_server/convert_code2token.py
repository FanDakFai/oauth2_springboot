#!/usr/bin/python

import requests
import requests.auth
import base64
import sys

clientId = "adminclient"
password = "M0uD.mY@ "

grantedCode = "0"
if len(sys.argv) >= 2:
    grantedCode = sys.argv[1]

if len(sys.argv) >= 4:
    clientId = sys.argv[2]
    password = sys.argv[3]


scope_encoded = base64.b64encode(clientId + ":" + password)
print "Token: " + scope_encoded

client_auth = requests.auth.HTTPBasicAuth(clientId, password)
post_data = {"grant_type": "authorization_code", "code": grantedCode , "client_id": clientId}
headers = {"Authorization": "Basic " + scope_encoded, "Content-type": "application/x-www-form-urlencoded; charset=utf-8"}

response = requests.post("https://localhost:8081/oauth/token", auth=client_auth, data=post_data, headers=headers, verify=False)
print "[" + str(response.raw.read()) + "]"

print response.json()
