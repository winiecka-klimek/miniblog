package javaldz26.demo.miniblog26.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Set;

@Service
public class SayingsService {

//    private String cachedSaying;

    private final CacheService<String> cacheService;

    private static final Set<String> sayings = Set.of(
            "A bird in the hand is worth two in the bush",
            "A bunch of fives",
            "A chain is only as strong as its weakest link",
            "A change is as good as a rest",
            "A countenance more in sorrow than in anger",
            "A Daniel come to judgement"
    );

    @Autowired
    public SayingsService(CacheService<String> cacheService) {
        this.cacheService = cacheService;
    }

//    public String getRandomSaying() {
//
//        if (cachedSaying != null) {
//            return cachedSaying;
//        }
//        final String saying = sayings.stream()
//                .skip(new Random().nextInt(sayings.size()))
//                .findFirst()
//                .orElse(null);
//        cachedSaying = saying;
//
//        return saying;
//    }

    public String getRandomSaying() {
        String cachedSaying = cacheService.get();
        if (cachedSaying != null) {
            return cachedSaying;
        }
        final String saying = sayings.stream()
                .skip(new Random().nextInt(sayings.size()))
                .findFirst()
                .orElse(null);
        cacheService.put(saying, Duration.of(10, ChronoUnit.SECONDS));

        return saying;
    }
}
