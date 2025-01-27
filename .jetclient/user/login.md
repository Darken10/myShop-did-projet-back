```toml
name = 'login'
method = 'POST'
url = 'http://localhost:8080/auth/authenticate'
sortWeight = 5000000
id = '95ad064d-69f5-4152-ae6a-63cff8433b08'

[body]
type = 'JSON'
raw = '''
{
  "matricule": "123456A",
  "password": "password"
}'''
```
