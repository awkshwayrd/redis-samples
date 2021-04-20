# redi2read

```
git submodule add git@github.com:redis-developer/redismod-docker-compose.git redi2read/docker
git submodule add git@github.com:redis-developer/redi2read-data.git redi2read/src/main/resources/data
cd redi2read
cd docker
docker-compose up
docker exec -it docker_redis_1 bash
/data# redis-cli 
127.0.0.1:6379> PING
PONG
127.0.0.1:6379> PING Marco!
"Marco!"
127.0.0.1:6379> MODULE LIST
.
127.0.0.1:6379> SET myname "Brian"
OK
127.0.0.1:6379> GET myname
"Brian"
127.0.0.1:6379> TYPE myname
string

127.0.0.1:6379> KEYS *
1) "myname"
2) "redi2read:strings:database:redis:creator"
127.0.0.1:6379> TYPE "redi2read:strings:database:redis:creator"
string
127.0.0.1:6379> GET "redi2read:strings:database:redis:creator"
"Salvatore Sanfilippo"

./mvnw clean spring-boot:run
.
curl --location --request POST 'http://localhost:8080/api/redis/strings' \
 --header 'Content-Type: application/json' \
 --data-raw '{ "database:redis:creator": "Salvatore Sanfilippo" }'
{"database:redis:creator":"Salvatore Sanfilippo"}

curl --location --request GET 'http://localhost:8080/api/redis/strings/database:redis:creator'
{"database:redis:creator":"Salvatore Sanfilippo"}

curl --location --request GET 'http://localhost:8080/api/redis/strings/database:neo4j:creator'
{"timestamp":"2021-04-15T10:00:06.748+00:00","status":404,"error":"Not Found"...}

redis-cli MONITOR
OK
1618480385.129302 [0 172.23.0.1:62448] "SET" "redi2read:strings:database:redis:creator" "Salvatore Sanfilippo"
1618480450.261937 [0 172.23.0.1:63296] "COMMAND"
1618480453.476876 [0 172.23.0.1:63296] "KEYS" "*"
1618480463.596025 [0 172.23.0.1:63296] "TYPE" "redi2read:strings:database:redis:creator"
1618480469.165282 [0 172.23.0.1:63296] "GET" "redi2read:strings:database:redis:creator"
1618480592.273111 [0 172.23.0.1:65136] "GET" "redi2read:strings:database:redis:creator"
1618480755.935903 [0 172.23.0.1:65136] "GET" "redi2read:strings:database:redis:editor"
1618480806.777170 [0 172.23.0.1:65136] "GET" "redi2read:strings:database:neo4j:creator"
```

![user-role-model](images/user-role-model.png)

```
127.0.0.1:6379> KEYS com.redislabs.edu.redi2read.models.Role*
1) "com.redislabs.edu.redi2read.models.Role:d7951b59-1419-4161-baf8-0ee863cdca80"
2) "com.redislabs.edu.redi2read.models.Role"
3) "com.redislabs.edu.redi2read.models.Role:a1fa6e36-b4f4-42df-9a7c-ac3a4d0727f6"
127.0.0.1:6379> TYPE "com.redislabs.edu.redi2read.models.Role:d7951b59-1419-4161-baf8-0ee863cdca80"
hash
127.0.0.1:6379> HGETALL "com.redislabs.edu.redi2read.models.Role:d7951b59-1419-4161-baf8-0ee863cdca80"
1) "_class"
2) "com.redislabs.edu.redi2read.models.Role"
3) "id"
4) "d7951b59-1419-4161-baf8-0ee863cdca80"
5) "name"
6) "customer"
127.0.0.1:6379> TYPE "com.redislabs.edu.redi2read.models.Role"
set
127.0.0.1:6379> SMEMBERS "com.redislabs.edu.redi2read.models.Role"
1) "a1fa6e36-b4f4-42df-9a7c-ac3a4d0727f6"
2) "d7951b59-1419-4161-baf8-0ee863cdca80"
```

Load user data from *users.json*

```
redis-cli
127.0.0.1:6379> KEYS "com.redislabs.edu.redi2read.models.User"
1) "com.redislabs.edu.redi2read.models.User"
127.0.0.1:6379> TYPE "com.redislabs.edu.redi2read.models.User"
set
127.0.0.1:6379> SCARD "com.redislabs.edu.redi2read.models.User"
(integer) 1001
127.0.0.1:6379> SRANDMEMBER "com.redislabs.edu.redi2read.models.User"
"5418786846225868871"
HGETALL "com.redislabs.edu.redi2read.models.User:-1848761758049653394"
 1) "email"
 2) "janice.garza@example.com"
 3) "name"
 4) "Janice Garza"
 5) "_class"
 6) "com.redislabs.edu.redi2read.models.User"
 7) "roles.[0]"
 8) "com.redislabs.edu.redi2read.models.Role:252bbf2c-3c56-4769-bed4-cd7b7de8255d"
 9) "id"
10) "-1848761758049653394"
11) "password"
12) "$2a$10$zG4t1hZYFg4mUUDe00so0OwQGzv7bMTdtB7lwsDjNHpZQdbA.Zx6G"

redis-cli MONITOR
OK
1618901037.101283 [0 172.23.0.1:63006] "COMMAND"
1618901521.852593 [0 172.23.0.1:63006] "KEYS" "com.redislabs.edu.redi2read.models.User"
1618901529.001789 [0 172.23.0.1:63006] "TYPE" "com.redislabs.edu.redi2read.models.User"
1618901540.794540 [0 172.23.0.1:63006] "SCARD" "com.redislabs.edu.redi2read.models.User"
1618901553.749002 [0 172.23.0.1:63006] "SRANDMEMBER" "com.redislabs.edu.redi2read.models.User"
1618901595.322009 [0 172.23.0.1:63006] "HGETALL" "com.redislabs.edu.redi2read.models.User:-1848761758049653394"

curl --location --request GET 'http://localhost:8080/api/users/' | jq
curl --location --request GET 'http://localhost:8080/api/users/?email=donald.gibson@example.com' | jq

```

