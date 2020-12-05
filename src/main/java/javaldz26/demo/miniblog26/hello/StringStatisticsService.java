package javaldz26.demo.miniblog26.hello;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StringStatisticsService {

//    public int sumAllCharacters(String...inputStrings) {
//
//        int countedChars = 0;
//
////        for(String inputString : inputStrings) {
////            countedChars += inputString.length();
////        }
//
////        if(inputStrings != null) {
////            for(String inputString : inputStrings) {
////                countedChars += inputString.length();
////            }
////        }
//
////        for(String inputString : inputStrings) {
////            countedChars += Optional.ofNullable(inputString)
////                    .map(s -> s.length())
////                    .orElse(0);
////        }
//
////        for(String inputString : inputStrings) {
////            countedChars += Optional.ofNullable(inputString)
////                    .map(String::length)
////                    .orElse(0);
////        }
//
//        for(String inputString : inputStrings) {
//            if(inputString == null) {
//                continue;
//            }
//            countedChars += inputString.length();
//        }
//
//        return countedChars;
//    }

    public int sumAllCharacters(String...inputStrings) {
       return Arrays.stream(inputStrings)
//                .filter(s -> s != null)
                .filter(Objects::nonNull)
                .mapToInt(String::length)
                .sum();
    }

    public boolean isLengthEven(String ...inputStrings) {
        return sumAllCharacters(inputStrings) % 2 == 0;
    }

//    public Map<String, Integer> charactersOccurrence(String... inputStrings) {
//       Map<String, Integer> charactersCountMap = new HashMap<>();
//       for (String inputString : inputStrings) {
//           if (inputString == null) {
//               continue;
//           }
//           final char[] chars = inputString.toCharArray();
//           for (char ch : chars) {
//               final String s = Character.toString(ch);
//               final Integer integer = charactersCountMap.get(s);
//               if(integer == null) {
//                   charactersCountMap.put(s, 1);
//               } else {
//                   charactersCountMap.put(s, integer + 1);
//               }
//
////               LUB
////               Integer currentCount = charactersCountMap.getOrDefault(s, 0);
////               charactersCountMap.put(s, currentCount  + 1);
//           }
//       }
//        return charactersCountMap;
//    }

    public Map<String, Integer> charactersOccurrence(String... inputStrings) {
        return Arrays.stream(inputStrings)
        .filter(Objects::nonNull)
//        .flatMapToInt(s -> s.chars())
                .flatMapToInt(String::chars)
//                .mapToObj(i -> Integer.valueOf(i))
                .boxed()
                //.collect(Collectors.groupingBy(c -> Character.toString(c),Collectors.counting()));
               .collect(Collectors.toMap(c -> Character.toString(c), c -> 1, (existingInt, newInt) -> existingInt + newInt));
    }
}
