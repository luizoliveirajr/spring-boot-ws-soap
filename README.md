# Exemplo de Uso de Web Service SOAP com Spring Boot

Este é um exemplo simples de um projeto Spring Boot que demonstra expor um serviço web SOAP.

## Pré-requisitos

Certifique-se de ter instalado o seguinte:

- Java Development Kit (JDK) 11
- Maven

## Como Executar o Projeto

1. Clone este repositório para o seu ambiente local:

    ```
    git clone https://github.com/luizoliveirajr/spring-boot-ws-soap.git
    ```

O servidor embutido do Spring Boot será iniciado e o projeto estará pronto para uso.

## Como Acessar o Serviço Web SOAP

- O serviço web SOAP estará disponível em [http://localhost:8080/ws](http://localhost:8080/ws).

- Você pode visualizar o WSDL do serviço em [http://localhost:8080/ws/customer.wsdl](http://localhost:8080/ws/customer.wsdl).

## Exemplo de Consumo do Serviço Web SOAP

Você pode consumir o serviço web SOAP usando ferramentas como SoapUI, curl ou até mesmo criando clientes SOAP em Java.

Aqui está um exemplo de como consumir o serviço usando cURL:

```bash
curl -X POST \
  -H "Content-Type: text/xml" \
  -d @request.xml \
  http://localhost:8080/ws
