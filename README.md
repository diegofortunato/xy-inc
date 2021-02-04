# XY-Inc APP

A XY Inc é uma empresa especializada na produção de excelentes receptores GPS. A nossa missão foi desenvolver um dispositivo inovador que ira auxiliar pessoas na localização de ponto de interesse (POIs).

 Stack
 
  - Kotlin
  - Spring Boot
  - Postgres
  - Docker
  
 Build
 
  Certifique que você tenha o Docker instalado em sua maquina e rode os seguintes comandos:
  
  - Primeiro passo clone o projeto: git@github.com:diegofortunato/xy-inc.git
  - Entre na pasta raiz no local clonado.
  - Build o projeto com: mvn clean package
  - Após o build execute o seguinte comando: docker build ./ -t springbootapp
  - Após esse comando. execute: docker-compose up
  
  Pronto, o projeto estara disponivel em: localhost:8080
  
 Execução
 
   Você pode executar em qualquer client HTTP. Uma Collection de exemplo esta disponivel aqui: 
   
   Você pode tambem utilizar os seguintes Curls para realizar as chamadas disponiveis:
   
   curl -X POST \
  http://localhost:8080/points \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a90eea72-fc3a-48bf-9524-062ddef2cb63' \
  -H 'cache-control: no-cache' \
  -d '{
	"request": {
		"name": "frutaria",
		"coordinate_x": 10,
		"coordinate_y": 15
	}
}'

curl -X GET \
  http://localhost:8080/points \
  -H 'Postman-Token: 362d7bf3-a5cd-4211-883b-299fd562cadc' \
  -H 'cache-control: no-cache'
  
  
  curl -X GET \
  'http://localhost:8080/points/distance?coordinateX=10&coordinateY=15&distance=10' \
  -H 'Postman-Token: c6819857-4372-4c9d-ab66-1773877c65e3' \
  -H 'cache-control: no-cache'
  
  
 Documetação
 
  Você pode encontrar a documentação do projeto aqui: 
  
