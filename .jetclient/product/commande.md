```toml
name = 'commande'
method = 'GET'
url = 'http://localhost:8080/commandes'
sortWeight = 5000000
id = 'd47275ee-7003-4dbc-8f5b-456f73d65e71'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTZBIiwiaWF0IjoxNzM3OTE0MDY2LCJleHAiOjE3MzgwMDA0NjZ9.otIp6_KaQeIWFu9HWULWJq7D-fxqz4RWJ9VZfgFx2sU'

[body]
type = 'JSON'
raw = '''
{
  "clientId": 1,
  "description": "Test",
  "status": "NEW"
}'''
```
