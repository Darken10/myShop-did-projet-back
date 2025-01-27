```toml
name = 'client'
method = 'POST'
url = 'http://localhost:8080/clients'
sortWeight = 4000000
id = '088749c3-3327-48da-8cad-7714b0a5f796'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTZBIiwiaWF0IjoxNzM3OTE0MDY2LCJleHAiOjE3MzgwMDA0NjZ9.otIp6_KaQeIWFu9HWULWJq7D-fxqz4RWJ9VZfgFx2sU'

[body]
type = 'JSON'
raw = '''
{
  "name": "Client",
  "phone": "10101012",
  "solde": 0,
  "address" : "test"
}'''
```
