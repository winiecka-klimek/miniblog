package javaldz26.demo.miniblog26.hello;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringStatisticsServiceTest {
    final StringStatisticsService stringStatisticsService = new StringStatisticsService();

    @Test
    void should_return_correct_sum_when_ther_are_null_elements() {
        //given
        String firsWord = "Ala";
        String secondWord = null;
        String thirdWord = "Kasia";
//       final StringStatisticsService stringStatisticsService = new StringStatisticsService();
        //when
        int sumAllCharacters = stringStatisticsService.sumAllCharacters(firsWord, secondWord, thirdWord);
        //then
        Assertions.assertThat(sumAllCharacters)
                .isEqualTo(8);
    }

    @Test
    void should_return_correct_number_of_elements() {
        //given
        String firsWord = "Ala";
        String secondWord = "Marta";
        String thirdWord = "Kasia";

        //when
        int sumAllCharacters = stringStatisticsService.sumAllCharacters(firsWord, secondWord, thirdWord);
        //then
        Assertions.assertThat(sumAllCharacters)
                .isEqualTo(13);
    }

    @Test
    void should_return_true_on_even_length() {
        //given
        String  firstWord = "ala";
        String secondWord = "ma kota";

        //when
        final boolean lengthEven = stringStatisticsService.isLengthEven(firstWord, secondWord);

        //then
        Assertions.assertThat(lengthEven)
                .isTrue();
    }

    @Test
    void should_return_false_on_even_length() {
        //given
        String firstWord = "ala";
        String secondWord = "ma kotka";

        //when
        final boolean lengthEven = stringStatisticsService.isLengthEven(firstWord, secondWord);

        //then
        Assertions.assertThat(lengthEven)
                .isFalse();
    }

    @Test
    void should_return_characters_count() {
        //given
        String firstWord = "ala";
        String secondWord = "ma";

        //when
        final Map<String, Integer> charactersOccurrence = stringStatisticsService.charactersOccurrence(firstWord, secondWord);

        //then
        Assertions.assertThat(charactersOccurrence)
                .hasSize(3)
                .containsEntry("a", 3)
                .containsEntry("l", 1)
                .containsEntry("m", 1);
    }

}
