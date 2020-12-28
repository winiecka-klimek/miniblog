package javaldz26.demo.miniblog26.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;

@Scope("prototype")
@Service
public class CacheService<T> {

    private final HelloDateTimeProvider helloDateTimeProvider;


    public CacheService(HelloDateTimeProvider helloDateTimeProvider) {
        this.helloDateTimeProvider = helloDateTimeProvider;
    }


    private T cachedGenericObject;
    private LocalDateTime expireTime;
//    private int numberOfGets;


    public T get(){
        if (expireTime != null && helloDateTimeProvider.getCurrentTime().isAfter(expireTime)) {
            return null;
        }
        return cachedGenericObject;
    }

    public void put(T object, Duration duration) {
       expireTime =  helloDateTimeProvider.getCurrentTime().plus(duration);
        cachedGenericObject = object;
    }

//    private Object cachedObject;
//
//    public Object get(){
//        return cachedObject;
//    }
//
//    public void put(Object object) {
//        cachedObject = object;
//    }
}
