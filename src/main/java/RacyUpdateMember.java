import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class RacyUpdateMember extends Thread{
    public void run() {
        //System.out.println( "IN THREAD" );
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("inst");
        clientConfig.getNetworkConfig().addAddress("172.19.149.112:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<String, Value> map = client.getMap( "map" );
        String key = "1";
        map.put( key, new Value() );
        System.out.println( "Starting" );
        for ( int k = 0; k < 1000; k++ ) {
            if ( k % 100 == 0 ) System.out.println( "no locks At: " + k );
            Value value = map.get( key );
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            if (value != null){
                value.amount++;
                map.put( key, value );
            }

        }
        System.out.println( "Finished! Result with no locks = " + map.get(key).amount );
        client.shutdown();
    }


}