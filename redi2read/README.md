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

Books categories catalog added from books folder

```
curl --location --request GET 'http://localhost:8080/api/books/' | jq
curl --location --request GET 'http://localhost:8080/api/books/1680503545' | jq
curl --location --request GET 'http://localhost:8080/api/books/categories' | jq
curl --location --request GET 'http://localhost:8080/api/books/?size=25&page=2' | jq
```

RedisJson shopping cart added

```
127.0.0.1:6379> SRANDMEMBER "com.redislabs.edu.redi2read.models.Cart"
"com.redislabs.edu.redi2read.models.Cart:fd8fb4d8-eeb8-49b7-b033-3a66cc8eeebf"
127.0.0.1:6379> TYPE "com.redislabs.edu.redi2read.models.Cart:fd8fb4d8-eeb8-49b7-b033-3a66cc8eeebf"
ReJSON-RL

curl --location --request GET 'http://localhost:8080/api/carts/fd8fb4d8-eeb8-49b7-b033-3a66cc8eeebf' | jq
```

RediSearch index added

```
1618939377.342267 [0 172.23.0.1:59820] "FT.INFO" "books-idx"
1618939377.350749 [0 172.23.0.1:59820] "FT.CREATE" "books-idx" "PREFIX" "1" "com.redislabs.edu.redi2read.models.Book:" "SCHEMA" "title" "TEXT" "SORTABLE" "subtitle" "TEXT" "description" "TEXT" "authors.[0]" "TEXT" "authors.[1]" "TEXT" "authors.[2]" "TEXT" "authors.[3]" "TEXT" "authors.[4]" "TEXT" "authors.[5]" "TEXT" "authors.[6]" "TEXT"

127.0.0.1:6379> FT.INFO "books-idx"
127.0.0.1:6379> FT.SEARCH books-idx "networking" RETURN 1 title
127.0.0.1:6379> FT.SEARCH books-idx "@title:networking" RETURN 1 title 
127.0.0.1:6379> FT.SEARCH books-idx "clo*" RETURN 4 title subtitle authors.[0] authors.[1] 
127.0.0.1:6379> FT.SEARCH books-idx "%scal%" RETURN 2 title subtitle 
127.0.0.1:6379> FT.SEARCH books-idx "rust | %scal%" RETURN 3 title subtitle authors.[0]

curl --location --request GET 'http://localhost:8080/api/books/search/?q=%25scal%25'
curl --location --request GET 'http://localhost:8080/api/books/authors/?q=brian%20s'
curl --location --request GET 'http://localhost:8080/api/books/authors/?q=brian%20sa'
```

Redisgraph

```
127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Actor {name: 'Kathryn Hahn', nick: 'ka'})"
1) 1) "Labels added: 1"
   2) "Nodes created: 1"
   3) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Show {name: 'WandaVision', nick: 'wv'})"
1) 1) "Labels added: 1"
   2) "Nodes created: 1"
   3) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'ka'}), (s:Show {nick: 'wv'}) CREATE (a)-[:ACTS]->(s)"
1) 1) "Relationships created: 1"

127.0.0.1:6379> GRAPH.QUERY "imdb-grf" "match (n) return distinct labels(n)"
1) 1) "labels(n)"
2) 1) 1) "Actor"
   2) 1) "Show"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Actor {name: 'Paul Bettany', nick: 'pb'})"
1) 1) "Nodes created: 1"
   2) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Actor {name: 'Paul Rudd', nick: 'pr'})"
1) 1) "Nodes created: 1"
   2) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Show {name: 'The Shrink Next Door', nick: 'tsnd'})"
1) 1) "Nodes created: 1"
   2) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Movie {name: 'Iron Man', nick: 'im'})"
1) 1) "Labels added: 1"
   2) "Nodes created: 1"
   3) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Movie {name: 'Our Idiot Brother', nick: 'oib'})"
1) 1) "Nodes created: 1"
   2) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "CREATE (:Movie {name: 'Captain America: Civil War', nick: 'cacw'})"
1) 1) "Nodes created: 1"
   2) "Properties set: 2"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'pb'}), (s:Show {nick: 'wv'}) CREATE (a)-[:ACTS]->(s)"
1) 1) "Relationships created: 1"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'pb'}), (m:Movie {nick: 'im'}) CREATE (a)-[:ACTS]->(m)"
1) 1) "Relationships created: 1"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'ka'}), (m:Movie {nick: 'oib'}) CREATE (a)-[:ACTS]->(m)"
1) 1) "Relationships created: 1"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'pr'}), (m:Movie {nick: 'oib'}) CREATE (a)-[:ACTS]->(m)"
1) 1) "Relationships created: 1"

127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'pr'}), (m:Movie {nick: 'cacw'}) CREATE (a)-[:ACTS]->(m)"
1) 1) "Relationships created: 1"
127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'pr'}), (s:Show {nick: 'tsnd'}) CREATE (a)-[:ACTS]->(s)"
1) 1) "Relationships created: 1"
127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor {nick: 'ka'}), (s:Show {nick: 'tsnd'}) CREATE (a)-[:ACTS]->(s)"
1) 1) "Relationships created: 1"
   2) "Cached execution: 0"
What are the relationships in our graph?   
127.0.0.1:6379> GRAPH.QUERY "imdb-grf" "MATCH ()-[e]->() RETURN distinct type(e)"
1) 1) "type(e)"
2) 1) 1) "ACTS"

Count the labels in the graph:
127.0.0.1:6379> GRAPH.QUERY "imdb-grf" "MATCH (n) RETURN distinct labels(n), count(n)"
1) 1) "labels(n)"
   2) "count(n)"
2) 1) 1) "Actor"
      2) (integer) 3
   2) 1) "Movie"
      2) (integer) 3
   3) 1) "Show"
      2) (integer) 2
      
127.0.0.1:6379> GRAPH.QUERY imdb-grf "MATCH (a:Actor)-[:ACTS]->(:Show {name:'The Shrink Next Door'}) RETURN a.name"
1) 1) "a.name"
2) 1) 1) "Kathryn Hahn"
   2) 1) "Paul Rudd"
   
Find any two actors that worked together in a movie:
127.0.0.1:6379> GRAPH.QUERY "imdb-grf" "MATCH (a1:Actor)-[:ACTS]->(m:Movie)<-[:ACTS]-(a2:Actor) WHERE a1 <> a2 AND id(a1) > id(a2) RETURN m.name, a1.name, a2.name"
1) 1) "m.name"
   2) "a1.name"
   3) "a2.name"
2) 1) 1) "Our Idiot Brother"
      2) "Paul Rudd"
      3) "Kathryn Hahn"

```



