# devops-infnet

# Projeto com Spring Boot expondo API de cadastro de Clientes.

Para executar, clonar repositório do git e executar docker-compose up.

Testes unitários utilizando: JUNIT e MOCKITO. 

Health check do projeto usando Actuator: localhost:8080/actuator/health 

Métricas exportadas para o formato do Prometheus utilizando o Micrometer e Actuator. 

Dados exportados para o Zipkin

LOGs do projeto exportadas para ferramenta de logs Papertrail.
