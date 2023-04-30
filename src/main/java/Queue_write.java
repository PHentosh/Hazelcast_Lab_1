import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class Queue_write extends Thread {
    public void run() {
        Config config = new Config();
        QueueConfig queueConfig = config.getQueueConfig("MyQueue");
        config.addQueueConfig(queueConfig);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("inst");
        clientConfig.getNetworkConfig().addAddress("172.19.149.112:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IQueue<Integer> queue = client.getQueue("MyQueue");

        for ( int k = 1; k < 150; k++ ) {
            try {
                queue.put( k );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "Producing: " + k );
        }

    }
}
