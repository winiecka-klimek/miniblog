package javaldz26.demo.miniblog26.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Primary
@Service
public class RandomTemperatureService implements TemperatureProvider {

//    private Integer cashedTemp;

    private final CacheService<Integer> cacheService;

    @Autowired
    public RandomTemperatureService(CacheService<Integer> cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public Integer currentTemperature() {
        final Integer cachedTemp = cacheService.get();
        if(cachedTemp != null) {
            return cachedTemp;
        }
        final Random random = new Random();
        int randomTemp = random.nextInt(31);

        cacheService.put(randomTemp, Duration.ofSeconds(5));

        return randomTemp;
    }
}
