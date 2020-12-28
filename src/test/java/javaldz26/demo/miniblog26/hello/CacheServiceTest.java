package javaldz26.demo.miniblog26.hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CacheServiceTest {

    @Mock
    private HelloDateTimeProvider helloDateTimeProvider;

    @InjectMocks
    private CacheService<String> stringCacheService;

    @Test
    void should_return_cached_value_when_not_expired() {
        final LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 6, 13, 23, 17);
        when(helloDateTimeProvider.getCurrentTime())
                .thenReturn(localDateTime)
                .thenReturn(localDateTime.plusSeconds(5));

//         when(helloDateTimeProvider.getCurrentTime())
//                .thenReturn(LocalDateTime.of(2020, 12, 6, 13, 23, 17));
        //given
        final String testString = "TEST_STRING";
//        final CacheService<String> stringCacheService = new CacheService<>(helloDateTimeProvider);
        stringCacheService.put(testString, Duration.ofSeconds(10));

        //when
       final String stringFromCache = stringCacheService.get();

       //then
       assertThat(stringFromCache)
               .isEqualTo(testString);
    }

    @Test
    public void should_return_null_when_expired() {
        final LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 6, 13, 23, 17);
        when(helloDateTimeProvider.getCurrentTime())
                .thenReturn(localDateTime)
                .thenReturn(localDateTime.plusSeconds(5));

        //given
        final String testString = "EXPIRED_TEST_STRING";
        stringCacheService.put(testString, Duration.ofSeconds(3));

        //when
        final String stringFromCache = stringCacheService.get();

        //then
        assertThat(stringFromCache).isNull();

    }

}
