# **Projeto Bootcamp API Users**
![ ](https://hermes.dio.me/tracks/c90e7979-b807-4941-895a-8d85564b142e.png)

Bem-vindo à continuação do projeto API Users! 🎉 Este repositório faz parte da segunda etapa da minha mentoria no [bootcamp Coding the Future Claro Java Spring Boot](https://web.dio.me/track/coding-the-future-claro-java-spring-boot). No primeiro repositório, exploramos boas práticas no desenvolvimento de APIs utilizando Spring Boot. Você pode conferir a primeira parte deste projeto [aqui](https://github.com/LevyVianna/bootcamp-apis-springboot).

Nesta segunda fase, demos um passo adiante e integramos o Amazon DynamoDB como banco de dados, utilizando uma instância local para facilitar o desenvolvimento e os testes, de forma que nao é necessário criar uma conta na AWS


## **Como utilizar esse projeto?**

1. Caso tenha uma conta no GitHub, pode dar um fork nesse projeto.
2. Caso não tenha, você pode clonar esse projeto com o comando: “git clone <https://github.com/LevyVianna/bootcamp-apis-dynamoDB.git”>
3. Caso não tenha o git instalado na sua máquina, você pode fazer o dowload do projeto clicando no botão verde “Code“ e depois em “Download ZIP”

Considere dar uma “estrela“ ao projeto se você achar ele útil 😊!
## **Passo a passo para a instalação**

1. Baixar o projeto para o seu ambiente de trabalho em uma das opções anteriores 
2. Baixar e instalar o dynamoDB local
    Seguir os passos da [documentação aws aqui](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html)  
    **OU**  
    Fazer download aqui: [ZIP](https://d1ni2b6xgvw0s0.cloudfront.net/v2.x/dynamodb_local_latest.zip) ou [tar.gz](https://d1ni2b6xgvw0s0.cloudfront.net/v2.x/dynamodb_local_latest.tar.gz)  
    Após download, executar os comandos:  
    ```
 	  unzip <<CAMINHO_DO_ZIP_MUDE_AQUI>>  
      cd dynamodb_local_latest  
      java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb  
    ```
   
3. Instalar a AWS CLI (Command Line Interface):  
   (Não precisa criar conta na AWS)  
   https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html  

4. Cria a tabela Users e o EmailIndex (GSI) no dynamoDB - via AWS CLI  
   
    Criando a tabela Users:  
    ```aws dynamodb create-table --table-name Users --attribute-definitions AttributeName=username,AttributeType=S --key-schema AttributeName=username,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000```  
  
    Criando o GSI EmailIndex:  
    ```aws dynamodb update-table --table-name Users --attribute-definitions AttributeName=email,AttributeType=S --global-secondary-index-updates "[{\"Create\":{\"IndexName\":\"EmailIndex\",\"KeySchema\":[{\"AttributeName\":\"email\",\"KeyType\":\"HASH\"}],\"Projection\":{\"ProjectionType\":\"ALL\"},\"ProvisionedThroughput\":{\"ReadCapacityUnits\":5,\"WriteCapacityUnits\":5}}}]" --endpoint-url http://localhost:8000```  
  
5. Instalar o dynamoDB-admin:  
   https://www.npmjs.com/package/dynamodb-admin  
  
Após essas etapas você deve ser capaz de ver a tabela Users no DynamoDB Admin em:  
http://localhost:8001  
  
## **Objetivo do Projeto**  
Este projeto, acompanhado pela apresentação realizada na live, tem como objetivo familiarizar desenvolvedores com o DynamoDB, um banco de dados NoSQL baseado em chave-valor. O foco principal é ensinar como utilizar esse banco de dados de forma eficiente, evitando o uso da operação SCAN, que pode gerar custos elevados. Em vez disso, o projeto demonstra como realizar consultas diretamente em um GSI (Índice Secundário Global) quando a chave primária da tabela não estiver disponível, otimizando o desempenho e reduzindo custos.  
  
## **Stack Utilizado no Projeto**
  
![ ](https://github.com/LevyVianna/bootcamp-apis-dynamoDB/blob/main/imgs/crud_USERS.png)  
  
Este projeto foi desenvolvido utilizando o seguinte stack:  
  
- **Java 17**
- **Spring Boot 3.3.2**
- **DynamoDB Local**
- **Spring Web**
- **Springdoc OpenAPI (Swagger 3.0)**
- **Maven**
  
### **Documentação OpenAPI (Swagger):**  
http://localhost:8080/swagger-ui/index.html#/  
  
### **DynamoDB Admin Local:**
http://localhost:8001  
  
## **Contato**
  
- [LinkedIn](https://www.linkedin.com/in/aws-cost-optimization-specialist/)  
- [Instagram](https://www.instagram.com/levy.vianna/)  