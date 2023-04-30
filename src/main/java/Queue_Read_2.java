import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.HazelcastInstance;


public class Queue_Read_2 extends Thread {
    public void run() {
        Config config = new Config();
        QueueConfig queueConfig = config.getQueueConfig("MyQueue");
        config.addQueueConfig(queueConfig);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("inst");
        clientConfig.getNetworkConfig().addAddress("172.19.149.112:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IQueue<Integer> queue = client.getQueue("MyQueue");
        int value = 0;
        for ( int k = 1; k < 150; k++ ) {
            try {
                value = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "Reader 2 get: " + value);
        }

    }
}
