package javaldz26.demo.miniblog26.hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SayingsServiceTest {

    @Mock
    private CacheService<String> cacheService;

    @InjectMocks
    private SayingsService sayingsService;

    @Test
    void should_return_cached_value_and_not_put_new_one_into_cache() {
        //given
        String cachedSaying = "CACHED_SAYING";
        when(cacheService.get()).thenReturn(cachedSaying);

        //when
        final String randomSaying = sayingsService.getRandomSaying();
        //than

        assertThat(randomSaying).isEqualTo(cachedSaying);
        verify(cacheService,VerificationModeFactory.times(1)).get();
        verify(cacheService, VerificationModeFactory.only()).get();
    }

    @Test
    void should_return_non_null_saying_and_put_it_into_cache_when_cache_is_empty() {
        //given
        when(cacheService.get()).thenReturn(null);

        //when
        final String randomSaying = sayingsService.getRandomSaying();

        //then
        assertThat(randomSaying).isNotNull();
                verify(cacheService).put(eq(randomSaying), any());
    }
}
