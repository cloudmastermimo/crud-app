NAME=$(cat /dev/urandom | env LC_CTYPE=C tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

curl --data "name=$NAME" http://crud-app.cfapps.io/person
