#!/usr/bin/python

import requests
import requests.auth
import base64

scope_encoded = base64.b64encode("clientIdPassword:secret")
#scope_encoded = base64.b64encode("john:123")
print "Token: " + scope_encoded



#client_auth = requests.auth.HTTPBasicAuth('john', '123')
client_auth = requests.auth.HTTPBasicAuth('clientIdPassword', 'secret')


#post_data = {"grant_type": "password", "username": "", "password": "", "client_id": "clientIdPassword"}
post_data = {"grant_type": "password", "username": "john", "password": "123", "client_id": "clientIdPassword"}
#post_data = {"grant_type": "password", "username": "clientIdPassword", "password": "secret", "client_id": "clientIdPassword"}

headers = {"Authorization": "Basic " + scope_encoded, "Content-type": "application/x-www-form-urlencoded; charset=utf-8"}
#headers = {"Content-type": "application/x-www-form-urlencoded; charset=utf-8"}


response = requests.post("https://localhost:8081/oauth/token", auth=client_auth, data=post_data, headers=headers, verify=False)
#response = requests.post("https://localhost:8081/oauth/token", data=post_data, headers=headers, verify=False)
print "[" + str(response.raw.read()) + "]"

print response.json()
