import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;
import java.util.Map;

public class Main {
    public static void main( String[] args ) {
        //HazelcastInstance hz = Hazelcast.newHazelcastInstance();


/*        RacyUpdateMember thread1 = new RacyUpdateMember();
        PessimisticUpdateMember thread2 = new PessimisticUpdateMember();
        OptimisticMember thread3 = new OptimisticMember();

        System.out.println( "OUT THREAD" );
        thread1.start();
        thread2.start();
        thread3.start();*/

        Queue_write thread1 = new Queue_write();
        Queue_Read_1 thread2 = new Queue_Read_1();
        Queue_Read_2 thread3 = new Queue_Read_2();

        System.out.println( "OUT THREAD" );
        thread1.start();
        thread2.start();
        thread3.start();

        //hz.shutdown();
    }


}
