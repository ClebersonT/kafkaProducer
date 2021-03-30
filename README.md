

## <img src="https://ik.imagekit.io/cleber/kafka_axstshSXM.png"> kafkaProducer
Exemplo produzindo mensagens no Apache Kafka utilizando a linguagem java e Apache Maven 

### :books: sobre o projeto

Foi escrito com o intuito de ter sempre uma base para produzir mensagens para o Apache Kafka, consultando sempre a base e  verificando as configurações e chamadas realizadas. Junto aos comandos para a instalação.

---
### :computer: Instalação do Apache Kafka em ambiente Linux
- Foi utilizado o Ubuntu 20.04 para a instalação

Acesse o link do kafka para download do arquivo .tgz
<http://kafka.apache.org/downloads>


```sh 
$ cd Downloads
$ tar -xvf kafka_2.12-2.4.0.tgz
```

Renomeie a pasta criada para apenas kafka

```sh
$ mv kafka_2.12-2.4.0.tgz kafka
```

Movendo minha pasta para dentro do repositório home

```sh
$ mv kafka/ ~
```

Coloque o diretório do kafka no path do linux, assim qm qualquer lugar do sistema é possivel executar os comandos do Kafka, sem estar necessariamente dentro da pasta.

```sh
$ gedit .basrc
```

Caso não tenha, adicione ao final do arquivo:
<pre>
    export PATH=~/.local/bin:$PATH
    export PATH=/home/user/kafka/bin:$PATH
</pre>

Por padrão o Zookeeper e o kafka armazenam suas informações em pastas predefinidas que estão sendo citadas nos arquivos de configurações, porteriormente editaremos esses arquivos. não é nada obrigatório na instalação, porém visualmente ficaria mais organizado e de fácil manutenção, logo:
Dentro da pasta do seu kafka, crie uma pasta chamada data

```sh
$ mkdir data
```

Dentro da pasta data crie uma pasta chamada zookeeper 

```sh
$ mkdir zookeeper
```

Dentro da pasta data crie uma pasta chamada kafka

```sh
$ mkdir kafka
```

Alterando as configurações, acesse:
```sh 
$ cd /kafka/config
$ gedit zookeeper.properties
```

localize a variavel dataDir e altere para a pasta que criamos:
<pre>
    dataDir=/home/user/kafka/data/zookeeper
</pre>

```sh
$ gedit server.properties
```

localize a variavel logDirs e altere para a pasta que criamos:
<pre>
    logDirs=/home/user/kafka/data/kafka
</pre>


### :rocket: Start nos serviços do zookeper e kafka

```sh
$ zookeeper-server-start.sh /home/user/kafka/config/zookeeper.properties
$ kafka-server-start.sh /home/user/kafka/config/server.properties
```
---
### :smiley: Teste suas configurações

<h5>Crie um tópico</h5>

Já com o kafka e zookeeper iniciado execute o comando para a criação
```sh
$ kafka-topics.sh --bootstrap-server localhost:9092 --create --topic teste
```
Listando...
```sh
$ kafka-topics.sh --bootstrap-server localhost:9092 --list
```

<h5>Crie um produtor</h5>
```sh
$ kafka-console-producer.sh --broker-list localhost:9092 --topic teste
```
<h5>Crie um consumidor</h5>
```sh
$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic teste
```

<h2 align="center">
    <img src="https://ik.imagekit.io/cleber/kafka_4ZR7N_Zxxu.gif">
</h2>

