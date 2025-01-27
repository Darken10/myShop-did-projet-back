```toml
name = 'product (1)'
method = 'POST'
url = 'http://localhost:8080/commandes'
sortWeight = 6000000
id = 'c64f3b13-dfd2-44c6-a5e2-4dedbefe1d66'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTZBIiwiaWF0IjoxNzM3OTE0MDY2LCJleHAiOjE3MzgwMDA0NjZ9.otIp6_KaQeIWFu9HWULWJq7D-fxqz4RWJ9VZfgFx2sU'

[body]
type = 'JSON'
raw = '''
{
  "clientId": 1,
  "description": "Test description",
  "status": "NEW",
  "ligneCommandes": [
    {
      "prixUnitaire": 110,
      "quantity" : 2,
      "produitId" : 10
    }
  ]
}'''
```
