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
  "firstName": "Admin",
  "lastName": "Admin",
  "genre": "MALE",
  "email": "admin@admin.com",
  "phoneNumber": "12345678",
  "matricule": "123456A",
  "status": "ACTIVE",
  "rolesId": [1,2,3]
}'''
```
