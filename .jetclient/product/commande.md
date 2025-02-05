```toml
name = 'commande'
method = 'POST'
url = 'http://localhost:8080/commandes'
sortWeight = 5000000
id = 'd47275ee-7003-4dbc-8f5b-456f73d65e71'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTZBIiwiaWF0IjoxNzM4Njk3MzA2LCJleHAiOjE3Mzg3ODM3MDZ9.lZRYX1DBpU3SPSm1oPGk1WCeD5GmkeMqx1vOPtfWjrk'

[body]
type = 'JSON'
raw = '''
{
  "clientId": 1,
  "description": "Test",
  "status": "NEW",
  "ligneCommandes": [
    {
      prixUnitaire: 1200,
      produitId: 7,
      quantity: 2
    }
  ]
}

'''
```
