# Exemplo de um servico spring boot no Weblogic

## Sobre
* Projeto dynamic web project convertido para maven

## Pontos importantes
Colocar os seguintes arquivos dentro de WEB-INF:
* dispatcherServlet-servlet.xml
* weblogic.xml

Dentro do arquivo **weblogic.xml** existe a tag:
```xml
<wls:context-root>/path</wls:context-root>
```
onde podemos definir parte da URI.

## Como acessar?
Depois de feito o deploy no weblogic, acessar através da URI:

> http://localhost:7001/path/resource

Deverá aparecer o seguinte texto no browser:

> hello!
