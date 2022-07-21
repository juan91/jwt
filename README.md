# jwt

# peticion a controlador de prueba:

curl --location --request GET 'localhost:8081/control' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJVc3VhcmlvIjoicGVwZSIsImVtYWlsIjoiZXBlcEBnbWFpbC5jb20iLCJpYXQiOjE2NTg0MTMwMzYsImV4cCI6MTY1ODQxNDIzNn0.Jq0v8L3kgWF2TZx1QCPUiL7EXTWFfyYGzr7jI1Vdlg4'

# login retorna jwt si login es exitoso
curl --location --request POST 'localhost:8081/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"Usuario":"pepe",
"Password":"123456",
"vinavegador":"abc",
"viip":"192.123.32.3",
"voidconexion":"",
"vonombrecompleto":"",
"voemail":"",
"voerror":""
}'