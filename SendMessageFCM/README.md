## SOBRE
Webservice para envio de mensagens ao FCM (Firebase Cloud Message). Faz o envio da mensagem e devolve o resultado do envio.
Ele recebe uma mensagem com parâmetros na URL:
* token: código identificador de um aparelho
* titulo: titulo da mensagem
* mensagem: mensagem :p

Exemplo de URL:
http://localhost:8080/sendMessage/message?token=c86VHZESwf0:APA91bGqaunz6Vc5t0sYjv1ZJAJOcaNS-0gouQMJ0tDZJVXwyMH77zYVe7jSiefs5B_2KRj2yElKj9EnhVrGhdV7LyFbUstCq2l9Hzgw6hek7WhX6EXRM9SRi4wsmax103e8KdyCVLVf&title=Teste Maroto&message=Hello World!!!!

### Tecnologias
* Java 1.8
* Maven: gerenciador de pacotes
* Spring Boot
* SLF4J

### FCM - Firebase Cloud Message
É preciso atualizar a "serverKey" na classe "Constants" com o chave do projeto no Firebase.
O FCM trabalho com mensagens em JSON.
Exemplo de mensagem de request:
{
	"to": "c86VHZESwf0:APA91bGqaunz6Vc5t0sYjv1ZJAJOcaNS-0gouQMJ0tDZJVXwyMH77zYVe7jSiefs5B_2KRj2yElKj9EnhVrGhdV7LyFbUstCq2l9Hzgw6hek7WhX6EXRM9SRi4wsmax103e8KdyCVLVf",
	"notification": {
		"title": "Teste Maroto",
		"body": "Hello World!!!!"
	}
}

Exemplo de mensagem de response:
{
	"multicast_id": 7387212234952271701,
	"success": 1,
	"failure": 0,
	"canonical_ids": 0,
	"results": [{
		"message_id": "0:1518013506662096%992d8ca4992d8ca4"
	}]
}

Não consegui simular um erro onde voltasse uma mensagem com erro do FCM.
Consegui simular apenas erros passando token errado ou alterando o endpoint do FCM.

Para ler o resultado, utilizo uma classe java (import org.json.JSONObject) para poder fazer o parsing da mensagem JSON.
Com isso consigo ler o conteúdo de "success" e "failure" para analisar a mensagem de retorno.


### LOG
O log é gravado no diretório temporário:
/tmp/logs/sendNotification.log

Exemplo de log:

* Sucesso:

> 2018-02-07 17:12:33.461  INFO 12107 --- [http-nio-8080-exec-1] com.sendMessageFCM.core.Send             : Begin process
> 2018-02-07 17:12:33.463  INFO 12107 --- [http-nio-8080-exec-1] com.sendMessageFCM.core.Send             : Request message: {"to":"c86VHZESwf0:APA91bGqaunz6Vc5t0sYjv1ZJAJOcaNS-0gouQMJ0tDZJVXwyMH77zYVe7jSiefs5B_2KRj2yElKj9EnhVrGhdV7LyFbUstCq2l9Hzgw6hek7WhX6EXRM9SRi4wsmax103e8KdyCVLVf", "notification" : {"title" : "Teste Maroto", "body":"Hello World!!!!"}}
> 2018-02-07 17:12:34.314  INFO 12107 --- [http-nio-8080-exec-1] com.sendMessageFCM.core.Send             : Response code: 200
> 2018-02-07 17:12:34.317  INFO 12107 --- [http-nio-8080-exec-1] com.sendMessageFCM.core.Send             : Response message: {"multicast_id":7308838308496722753,"success":1,"failure":0,"canonical_ids":0,"results":[{"message_id":"0:1518030754237057%992d8ca4992d8ca4"}]}
> 2018-02-07 17:12:34.325  INFO 12107 --- [http-nio-8080-exec-1] com.sendMessageFCM.core.Send             : End process

* Erro:

> 2018-02-07 17:15:53.282  INFO 12377 --- [http-nio-8080-exec-2] com.sendMessageFCM.core.Send             : Begin process
> 2018-02-07 17:15:53.285  INFO 12377 --- [http-nio-8080-exec-2] com.sendMessageFCM.core.Send             : Request message: {"to":"c86VHZESwf0:APA91bGqaunz6Vc5t0sYjv1ZJAJOcaNS-0gouQMJ0tDZJVXwyMH77zYVe7jSiefs5B_2KRj2yElKj9EnhVrGhdV7LyFbUstCq2l9Hzgw6hek7WhX6EXRM9SRi4wsmax103e8KdyCVLVf", "notification" : {"title" : "Teste Maroto", "body":"Hello World!!!!"}}
> 2018-02-07 17:15:54.213  INFO 12377 --- [http-nio-8080-exec-2] com.sendMessageFCM.core.Send             : Response code: 401
> 2018-02-07 17:15:54.213  INFO 12377 --- [http-nio-8080-exec-2] com.sendMessageFCM.core.Send             : Error getting response message from connection.
> 2018-02-07 17:15:54.213  INFO 12377 --- [http-nio-8080-exec-2] com.sendMessageFCM.core.Send             : End process

Depois mudei para:

logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5level] %logger{50} - %msg%n

> 2018-02-07 18:04:24.338 [INFO ] com.sendMessageFCM.spring.Controller - Begin process
> 2018-02-07 18:04:24.341 [INFO ] com.sendMessageFCM.core.Send - Request message: {"to":"c86VHZESwf0:APA91bGqaunz6Vc5t0sYjv1ZJAJOcaNS-0gouQMJ0tDZJVXwyMH77zYVe7jSiefs5B_2KRj2yElKj9EnhVrGhdV7LyFbUstCq2l9Hzgw6hek7WhX6EXRM9SRi4wsmax103e8KdyCVLVf", "notification" : {"title" : "Teste Maroto", "body":"Hello World!!!!"}}
> 2018-02-07 18:04:25.049 [INFO ] com.sendMessageFCM.core.Send - Response code: 200
> 2018-02-07 18:04:25.053 [INFO ] com.sendMessageFCM.core.Send - Response message: {"multicast_id":7355808595006060188,"success":1,"failure":0,"canonical_ids":0,"results":[{"message_id":"0:1518033864954807%992d8ca4992d8ca4"}]}
> 2018-02-07 18:04:25.062 [INFO ] com.sendMessageFCM.spring.Controller - End process
