package org.opchan;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarConsumer {
    private static String serviceUrl;
    private static String topicName;
    private static String subscriptionName;

    public static void main(String[] args) throws Exception {
        loadConfig();
        System.out.println("Connecting to Pulsar at " + serviceUrl);

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .build()) {

            System.out.println("Creating consumer for topic " + topicName);

            try (Consumer<byte[]> consumer = client.newConsumer()
                    .topic(topicName)
                    .subscriptionName(subscriptionName)
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

    private static void loadConfig() throws java.io.IOException {
        java.io.InputStream input = PulsarConsumer.class.getClassLoader().getResourceAsStream("config.properties");
        if (input == null) {
            System.out.println("Sorry, unable to find config.properties");
            return;
        }

        java.util.Properties prop = new java.util.Properties();
        prop.load(input);

        serviceUrl = prop.getProperty("pulsar.serviceUrl");
        topicName = prop.getProperty("pulsar.topicName");
        subscriptionName = prop.getProperty("pulsar.subscriptionName");
    }
}
