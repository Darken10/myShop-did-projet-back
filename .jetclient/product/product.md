```toml
name = 'product'
method = 'GET'
url = 'http://localhost:8080/produits'
sortWeight = 3000000
id = '4a07ef39-7f4a-48b3-a076-1881d953d6d7'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTZBIiwiaWF0IjoxNzM3OTE0MDY2LCJleHAiOjE3MzgwMDA0NjZ9.otIp6_KaQeIWFu9HWULWJq7D-fxqz4RWJ9VZfgFx2sU'

[body]
type = 'JSON'
raw = '''
{
  "libelle": "Test",
  "description": "test",
  "prix": 1200,
  "stock": 11,
  "image": "",
  "seuil": 5,
  "unite": "KG",
  "categoryId": 1,
  "tagsId": [1]
}'''
```
