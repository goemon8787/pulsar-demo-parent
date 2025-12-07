package org.opchan;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarConsumer {
    private static final String SERVICE_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";
    private static final String SUBSCRIPTION_NAME = "my-subscription";

    public static void main(String[] args) throws PulsarClientException {
        System.out.println("Connecting to Pulsar at " + SERVICE_URL);

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .build()) {

            System.out.println("Creating consumer for topic " + TOPIC_NAME);

            try (Consumer<byte[]> consumer = client.newConsumer()
                    .topic(TOPIC_NAME)
                    .subscriptionName(SUBSCRIPTION_NAME)
                    .subscribe()) {

                System.out.println("Subscribed. Waiting for messages...");

                while (true) {
                    // Wait for a message
                    Message<byte[]> msg = consumer.receive();

                    try {
                        String content = new String(msg.getData());
                        System.out.println("Received message: " + content);

                        // Acknowledge the message so that it can be deleted by the message broker
                        consumer.acknowledge(msg);
                    } catch (Exception e) {
                        // Message failed to process, redeliver later
                        consumer.negativeAcknowledge(msg);
                    }
                }
            }
        }
    }
}
