

##<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true" focusable="false" width="1em" height="1em" style="-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); transform: rotate(360deg);" preserveAspectRatio="xMidYMid meet" viewBox="0 0 24 24"><path d="M15.54 12.97c-.68 0-1.3.25-1.78.67l-1.29-.75c.09-.29.13-.6.13-.92c0-.32-.04-.63-.1-.92l1.23-.73c.48.44 1.12.68 1.81.68c1.49 0 2.7-1.19 2.7-2.68s-1.21-2.69-2.7-2.69s-2.7 1.21-2.7 2.7c0 .17.02.37.05.55l-1.25.72c-.43-.45-1-.78-1.64-.95V7.26a2.705 2.705 0 0 0 1.88-2.57C11.88 3.2 10.67 2 9.18 2C7.69 2 6.5 3.2 6.5 4.69c0 1.2.76 2.21 1.84 2.57v1.4a3.421 3.421 0 0 0-2.58 3.31c0 1.6 1.1 2.94 2.58 3.31v1.45A2.692 2.692 0 0 0 6.5 19.3c0 1.49 1.19 2.7 2.68 2.7c1.49 0 2.7-1.21 2.7-2.7c0-1.2-.79-2.22-1.88-2.57v-1.44c.64-.16 1.2-.49 1.64-.94l1.26.73c-.04.19-.06.38-.06.58c0 1.49 1.21 2.7 2.7 2.7s2.7-1.21 2.7-2.7s-1.21-2.69-2.7-2.69m0-5.97c.74 0 1.33.59 1.33 1.32s-.59 1.34-1.33 1.34s-1.33-.6-1.33-1.34S14.8 7 15.54 7M7.85 4.69c0-.74.59-1.34 1.33-1.34c.74 0 1.32.6 1.32 1.34s-.58 1.34-1.32 1.34c-.74 0-1.33-.6-1.33-1.34M10.5 19.3c0 .74-.58 1.34-1.32 1.34c-.74 0-1.33-.6-1.33-1.34c0-.74.59-1.34 1.33-1.34c.74 0 1.32.6 1.32 1.34m-1.32-5.41a1.92 1.92 0 1 1 .001-3.841a1.92 1.92 0 0 1-.001 3.841M15.54 17c-.74 0-1.33-.6-1.33-1.34s.59-1.33 1.33-1.33s1.33.6 1.33 1.33S16.28 17 15.54 17z" fill="#626262"/></svg> kafkaProducer
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

#####Crie um tópico
Já com o kafka e zookeeper iniciado execute o comando para a criação
```sh
$ kafka-topics.sh --bootstrap-server localhost:9092 --create --topic teste
```
Listando...
```sh
$ kafka-topics.sh --bootstrap-server localhost:9092 --list
```

#####Crie um produtor
```sh
$ kafka-console-producer.sh --broker-list localhost:9092 --topic teste
```
#####Crie um consumidor
```sh
$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic teste
```

<h2 align="center">
    <img src="https://ik.imagekit.io/cleber/kafka_4ZR7N_Zxxu.gif">
</h2>

