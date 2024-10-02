package HelloWorld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
public class Send {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        //Opprett en ConnectionFactory og sett verten til RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        //Opprett en forbindelse og kanal
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Deklarere en kø
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //Meldingen som skal sendes
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        }
    }
}