# Preparando o Ambiante

[Documentação JDBC getting started](https://docs.oracle.com/javase/tutorial/jdbc/basics/gettingstarted.html)

## Criar Projeto Java'

Crie uma pasta onde ficara seu projeto após cria-la abra como *vscode*. No canto esquerdo no painel **Explorador** clique com o lado direito e selecione a opção `New Java Project...` vai abrir as opção de build do projeto, selecione `No Build Tools` após isso vai pedir para selecionar uma pasta, use a mesma que acabou de criar. Após selecionar o *VS Code* vai pedir para colocar um nome para o seu projeto java, coloque um e pressione o `Enter`.



## Mysql connector Java

Primeiro de tudo baixe o conector do mysql para java em: [mysql-connecto-j-9.7.0.jar](https://dev.mysql.com/downloads/connector/j/)

no campo *`Select Operating System:`* selecione *`Plataform Independent`* e baixe o arquivo `.zip`

após baixar extraia o arquivo e entre na pasta até achar o arquivo: `mysql-connector-j-9.7.0.jar` recorte esse arquivo e cole dentro da estrutura do seu projeto java em *`lib`*

Estrutura usando o `Tree` no terminal fica assim o arquivo:

```bash
├───.vscode
├───bin
├───lib
│      mysql-connector-j-9.7.0.jar        
└───src

```

---

## Mysql
> criar banco de dados, usar o banco depois criar a tabela de alunos antes de executar o JDBC


Tudo isso é no Mysql Workbanch ou DBeaver executando as Query

### Criar *Banco de dados*

```sql

    CREATE DATABASE escola;

    USE escola;
```


### Criar tabela *Aluno*


```sql

CREATE TABLE aluno (
    id INT  AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(250),
    matricula INT,
    nota1 DOUBLE,
    nota2 DOUBLE,
    nota3 DOUBLE
);
```
---

## Java *JDBC*

> para conectar com o mysql precisa ter rodado o banco na faculdade.

Rodar o Mysql Workbench


> Em casa primeiro rodar o docker desktop, depois rodar o container docker

```docker

docker run --name mysql-jdbc -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=escola -p 3306:3306 -d mysql
```

Após criar o container verificar com:

```docker
docker ps
``` 

Reiniciou o PC e está criado o container basta rodar:

```
docker start mysql-jdbc
```

### Conexão com o banco 
> crie um arquivo para conexão `ConexaoBD.java`

```java

    import java.sql.*;

    private static final String URL = "jdbc:mysql://localhost:3306/escola";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";
```


### Usando Java JDBC Inserir no Mysql os dados 

> Sempre realizar o import do sql.*

```java

import java.sql.*;
import java.util.*;
```


Exemplo de inserção no banco de dados usando **String** do Java para executar uma `Query SQL`

```java
  String sql = "INSERT INTO aluno(nome, matricula, nota1, nota2, nota3) VALUES (?,?,?,?,?)";
```

> Obs: Sempre usa o `throws SQLException` nos metodos para gerar a exeção quando executar

Use o `try()` para realizar a conexão e chame seu arquivo `ConexaoBD.java` use o sanitizador de query `PreparedStatement`;

```java
    try (Connection c = ConexaoBD.getConexao(); PreparedStatement ps = c.prepareStatement(sql)) {}
```

Ao listar os items do banco cria primeiro um novo array list:

```java
    List<Aluno> lista = new ArrayList<>();
```


 No `try` use um **ResultSet** no lugar o **prepareStatement**

```java

    try (Connection c = ConexaoBD.getConexao(); ResultSet rs = c.createStatement().executeQuery(sql)) {
        while (rs.next()) {
            lista.add(new Aluno());
        }
    }
```


Ao **Inserir**, **Atualizar**, **Excluir** use dentro do try o `executeUpdate();`


```java        
   ps.executeUpdate();
    
```