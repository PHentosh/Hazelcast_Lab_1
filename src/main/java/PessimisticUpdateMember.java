import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class PessimisticUpdateMember extends Thread{
    public void run(  ) {

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("inst");
        clientConfig.getNetworkConfig().addAddress("172.19.149.112:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<String, Value> map = client.getMap( "map" );
        String key = "2";
        map.put( key, new Value() );
        System.out.println( "Starting" );
        for ( int k = 0; k < 1000; k++ ) {
            if ( k % 10 == 0 ) System.out.println( "pessimistic At: " + k );
            map.lock( key );
            try {
                Value value = map.get( key );
                try {
                    Thread.sleep( 10 );
                } catch (InterruptedException ignored) {
                }
                value.amount++;
                map.put( key, value );
            } finally {
                map.unlock( key );
            }
        }
        System.out.println( "Finished! Result pessimistic lock = " + map.get( key ).amount );
        client.shutdown();
    }



}