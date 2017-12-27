package common.util;


import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.resource.DefaultClientResources;

import java.util.List;
import java.util.Map;

public class RedisUtil {


    private static RedisClient client = null;
    private static StatefulRedisConnection<String, String> connection = null;

    static {
        if (connection == null) {
            connection = getStatefulConnection();
        }
    }

    public static boolean delete(String redisKey) {
        return connection.sync().del(redisKey) != 0;
    }

    public static boolean hdel(String redisKey) {
        return connection.sync().hdel(redisKey) != 0;
    }

    public static String get(String redisKey) {
        return connection.sync().get(redisKey);
    }

    public static boolean exists(String redisKey) {
        return connection.sync().exists(new String[]{redisKey}) != 0;
    }

    public static boolean put(String redisKey, String value) {
        connection.sync().set(redisKey, value);
        return true;
    }

    public static boolean hmput(String redisKey, Map<String, String> value) {
        connection.sync().hmset(redisKey, value);
        return true;
    }

    public static boolean mPut(String redisKey,String mapKey,String value){
        return connection.sync().hset(redisKey,mapKey,value);
    }

    public static String  mGet(String redisKey,String mapKey){
        return connection.sync().hget(redisKey,mapKey);
    }

    public static List<String>  hmGet(String redisKey,String ... mapKey){
        return connection.sync().hmget(redisKey,mapKey);
    }


    public static boolean put(String redisKey, String value, long expireSeconds) {
        connection.sync().set(redisKey, value);
        connection.sync().expire(redisKey, expireSeconds);
        return true;
    }

    public static List<String> keys(String pattern) {
        return connection.sync().keys(pattern);
    }

    public static Long getExpireTime(String key) {
        return connection.sync().ttl(key);
    }

    public static void batchDelete(String... keys) {
        connection.sync().del(keys);
    }

    public static StatefulRedisConnection<String, String> getStatefulConnection() {
        String redisHost = PropertyPlaceholder.getProperty("redis.host", "127.0.0.1");
        String port = PropertyPlaceholder.getProperty("redis.port", "6379");
        String index = PropertyPlaceholder.getProperty("redis.index", "0");
        RedisURI redisUri = RedisURI.Builder.redis(redisHost).withPort(Integer.valueOf(port)).withDatabase(Integer.valueOf(index))
                .build();
        if (client == null) {
            client = RedisClient.create(DefaultClientResources.create(), redisUri);
        }

        return client.connect();
    }

}
