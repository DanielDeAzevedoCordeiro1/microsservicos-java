# Microsservicos Java

## Se trata de uma abordagem de arquitetura orientada a eventos (EDA) utilizando microsservicos , a medida que um novo evento e criado um produtor de eventos (producer) envia este evento para uma fila (RabbitMQ) que posteriormente sera consumida por um microsservico especifico.

## Como Usar

### Criacao Message Broker

Crie um conta no CloudAMPQ ou um container Docker para montar seu message broker e em seguida va ate o application.properties (email-service e producer) e cole as credenciais de conexao:

![CloudAMPQ](assets/Screenshot_2026-02-04_18-49-52.png)

Cole neste campo:
```
spring.rabbitmq.addresses=#
```

### Banco de dados

Rode o comando (Subira um container com postgres):
```bash
docker compose upd -d
```


### Suba a API de eventos:

```bash
cd events-api && mvn spring-boot:run
```

### Suba o producer (Certifique que as credencias do rabbitMQ estejam corretas!):

```bash
cd producer && mvn spring-boot:run
```
