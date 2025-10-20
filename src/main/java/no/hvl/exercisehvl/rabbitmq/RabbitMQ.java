package no.hvl.exercisehvl.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import no.hvl.exercisehvl.PollManager;
import no.hvl.exercisehvl.entity.Poll;
import java.nio.charset.StandardCharsets;

public class RabbitMQ {
    private final ConnectionFactory factory;

    public RabbitMQ() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

    public void subscribeToPoll(Poll poll) throws Exception {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel(); {
    channel.queueDeclare(poll.getQuestion(), false, false, false, null);
            System.out.println(" [*] Waiting for messages in poll: " + poll.getQuestion());

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "' in poll: " + poll.getQuestion());
                PollManager.updateVotes(poll, message);
            };
            channel.basicConsume(poll.getQuestion(), true, deliverCallback, consumerTag -> { });
        }
    }

    public void registerPoll(Poll poll) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(poll.getQuestion(), false, false, false, null);
            System.out.println(" [x] Poll registered: '" + poll.getQuestion() + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendVote(Poll poll, String vote) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(poll.getQuestion(), false, false, false, null);
            channel.basicPublish("", poll.getQuestion(), null, vote.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + vote + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}