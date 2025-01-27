```toml
name = 'newpwd'
method = 'POST'
url = 'http://localhost:8080/auth/reset-password/new-password'
sortWeight = 4000000
id = 'eb66e50f-1453-42b4-acfc-db829a4f67d8'

[body]
type = 'JSON'
raw = '''
{
"password": "password",
"jeton": "74c7fc01-6b37-4764-8f7e-8babbb5ac3a9"
}'''
```
