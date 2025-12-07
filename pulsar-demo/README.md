# Pulsar Demo Project

This project demonstrates a simple Apache Pulsar Producer and Consumer implementation in Java.

## Prerequisites

- Apache Pulsar must be running locally on `pulsar://localhost:6650`.
- Java JDK 17+
- Maven

## Usage

### 1. Start the Consumer
The consumer listens for messages on the topic `my-topic`.

Open a terminal and run:
```bash
cd pulsar-demo
mvn exec:java -Dexec.mainClass="org.opchan.PulsarConsumer"
```

### 2. Start the Producer
The producer allows you to send messages to `my-topic` interactively.

Open a second terminal and run:
```bash
cd pulsar-demo
mvn exec:java -Dexec.mainClass="org.opchan.PulsarProducer"
```

Once started, type your message and press Enter to send it. Type `exit` to quit.

## Example Interaction

**Producer Terminal:**
```text
Producer created. Enter messages to send (type 'exit' to quit):
> Hello Pulsar
Message sent: Hello Pulsar
```

**Consumer Terminal:**
```text
Message received: Hello Pulsar
```
