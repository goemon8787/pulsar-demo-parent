package org.opchan;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarProducer {
    private static final String SERVICE_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";

    public static void main(String[] args) throws PulsarClientException {
        System.out.println("Connecting to Pulsar at " + SERVICE_URL);

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .build()) {

            System.out.println("Creating producer for topic " + TOPIC_NAME);
            try (Producer<byte[]> producer = client.newProducer()
                    .topic(TOPIC_NAME)
                    .create()) {

                System.out.println("Producer created. Enter messages to send (type 'exit' to quit):");

                java.util.Scanner scanner = new java.util.Scanner(System.in);

                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();

                    if ("exit".equalsIgnoreCase(input)) {
                        System.out.println("Exiting...");
                        break;
                    }

                    producer.send(input.getBytes());
                    System.out.println("Message sent: " + input);
                }
            }
        }
    }
}
