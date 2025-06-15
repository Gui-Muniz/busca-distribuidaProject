# Projeto Final - Sistema de Busca Distribuída

Este projeto implementa um sistema de busca de artigos científicos distribuído utilizando Sockets em Java, conforme os requisitos da disciplina.

## Arquitetura

O sistema é composto por quatro componentes:
- *Cliente:* Interface do usuário para enviar termos de busca.
- *Servidor A (Coordenador):* Recebe a busca do cliente, a repassa para os servidores B e C, consolida os resultados e os devolve ao cliente.
- *Servidor B:* Armazena e realiza buscas na primeira metade dos dados.
- *Servidor C:* Armazena e realiza buscas na segunda metade dos dados.

---

## Como Compilar e Executar

Existem duas maneiras de executar este projeto.

### Método 1: Usando Apache Maven (Recomendado)

1.  *Compilar:* Na pasta raiz do projeto, execute o comando:
    bash
    mvn clean install
    
2.  *Executar:* Abra 4 terminais separados na raiz do projeto e execute os seguintes comandos, um em cada terminal e nesta ordem:
    bash
    java -cp target/projeto-busca-distribuida-1.0-SNAPSHOT.jar ServerB
    java -cp target/projeto-busca-distribuida-1.0-SNAPSHOT.jar ServerC
    java -cp target/projeto-busca-distribuida-1.0-SNAPSHOT.jar ServerA
    java -cp target/projeto-busca-distribuida-1.0-SNAPSHOT.jar Cliente
    

### Método 2: Manualmente (Requer JDK configurado no Path)

1.  *Baixar Dependência:* Crie uma pasta lib na raiz e coloque o arquivo json-20231013.jar dentro dela.
2.  *Compilar:* Na pasta raiz, execute:
    bash
    javac -cp "lib/json-20231013.jar" src/main/java/*.java -d target
    
3.  *Executar:* Abra 4 terminais e execute (no Windows, use ; no classpath; no Linux/Mac, use :):
    bash
    java -cp "target;lib/json-20231013.jar" ServerB
    java -cp "target;lib/json-20231013.jar" ServerC
    java -cp "target;lib/json-20231013.jar" ServerA
    java -cp "target;lib/json-20231013.jar" Cliente
