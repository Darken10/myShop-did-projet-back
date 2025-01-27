```toml
name = 'register'
method = 'POST'
url = 'http://localhost:8080/auth/register'
sortWeight = 1000000
id = 'ebbbd8f8-0d4f-488c-bd21-d81aa4fd3442'

[body]
type = 'JSON'
raw = '''
{
  "firstName": "Darken",
  "lastName": "Darken",
  "genre": "MALE",
  "email": "test@test.com",
  "phoneNumber": "12345678",
  "matricule": "123456A",
  "status": "EN_ATTENTE",
  "rolesId": [1,2]
}'''
```
