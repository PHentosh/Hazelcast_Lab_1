import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class OptimisticMember extends Thread{
    public void run( ) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("inst");
        clientConfig.getNetworkConfig().addAddress("172.19.149.112:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<String, Value> map = client.getMap( "map" );
        String key = "3";
        map.put( key, new Value() );
        System.out.println( "Starting" );
        for ( int k = 0; k < 1000; k++ ) {
            if ( k % 10 == 0 ) System.out.println( "optimistic At: " + k );
            for (; ; ) {
                Value oldValue = map.get( key );
                Value newValue = new Value( oldValue );
                try {
                    Thread.sleep( 10 );
                } catch (InterruptedException ignored) {
                }
                newValue.amount++;
                if ( map.replace( key, oldValue, newValue ) )
                    break;
            }
        }
        System.out.println( "Finished! Result optimistic lock = " + map.get( key ).amount );
        client.shutdown();
    }

}