# gRPC vs REST

### STEPS:

* clone this repo
* Package `mvn clean package -DskipTests`
* Build docker images `docker-compose build`
* Run `docker-compose up`

You can issue requests like this.

#### REST

http://localhost:8080/rest/unary/1000

#### gRPC
http://localhost:8080/grpc/unary/1000

http://localhost:8080/grpc/stream/1000
