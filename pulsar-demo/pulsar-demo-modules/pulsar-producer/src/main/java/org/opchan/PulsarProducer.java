package org.opchan;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

public class PulsarProducer {
    private static String serviceUrl;
    private static String topicName;

    public static void main(String[] args) throws Exception {
        loadConfig();
        System.out.println("Connecting to Pulsar at " + serviceUrl);

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(serviceUrl)
                .build()) {

            System.out.println("Creating producer for topic " + topicName);
            try (Producer<byte[]> producer = client.newProducer()
                    .topic(topicName)
                    .create()) {

                System.out.println("Producer created. Enter messages to send (type 'exit' to quit):");

                try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
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

    private static void loadConfig() throws java.io.IOException {
        java.io.InputStream input = PulsarProducer.class.getClassLoader()
                .getResourceAsStream("configs/config.properties");
        if (input == null) {
            System.out.println("Sorry, unable to find config.properties");
            return;
        }

        java.util.Properties prop = new java.util.Properties();
        prop.load(input);

        serviceUrl = prop.getProperty("pulsar.serviceUrl");
        topicName = prop.getProperty("pulsar.topicName");
    }
}
