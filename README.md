# Projeto Final - Busca Distribuída

Este projeto implementa um sistema de busca de artigos científicos distribuído utilizando Sockets em Java.

## Arquitetura

O sistema é composto por quatro componentes principais:
- **Cliente:** Interface do usuário para enviar termos de busca.
- **Servidor A (Coordenador):** Recebe a busca do cliente, a repassa para os servidores B e C, consolida os resultados e os devolve ao cliente.
- **Servidor B:** Mantém a primeira metade dos dados e realiza buscas nela.
- **Servidor C:** Mantém a segunda metade dos dados e realiza buscas nela.

---

## Como Compilar

Este é um projeto Maven. Para compilar e criar os pacotes necessários, execute o seguinte comando na raiz do projeto:

```bash
mvn clean install