package demo.springboot;
import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.GetResponse;
import com.google.protobuf.ByteString;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EtcdTest {

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            // create client
            Client client = Client.builder().endpoints("http://120.78.165.242:2379").build();
            KV kvClient = client.getKVClient();
            ByteSequence key = ByteSequence.fromString("test_key");
            ByteSequence value = ByteSequence.fromString("test_value");
            // put the key-value
            kvClient.put(key, value).get();
            // get the CompletableFuture
            CompletableFuture<GetResponse> getFuture = kvClient.get(key);
            // get the value from CompletableFuture
            GetResponse response = getFuture.get();
            System.out.println(response.getKvs().get(0).getKey());
            // delete the key
            kvClient.delete(key).get();
        }
}
