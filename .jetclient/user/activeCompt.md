```toml
name = 'activeCompt'
method = 'POST'
url = 'http://localhost:8080/auth/reset-password'
sortWeight = 2000000
id = 'aa885050-1cc3-4235-a196-d3ed3a1424dd'

[body]
type = 'JSON'
raw = '''
{
  "email": "test@test.com",
  "matricule": "123456A",
  "url": "http://loclahost:4200"
}'''
```
