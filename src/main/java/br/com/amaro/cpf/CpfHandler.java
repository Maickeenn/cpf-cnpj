package br.com.amaro.cpf;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CpfHandler {

    public static List<Integer> random() {

        List<Integer> collect = new Random().ints(9, 0, 10)
                .boxed()
                .collect(Collectors.toList());
        collect.add(generateCheckDigit(collect));
        collect.add(generateCheckDigit(collect));
        return collect;
    }

    public static Integer generateCheckDigit(List<Integer> digits) {

        final AtomicInteger counter = new AtomicInteger();
        final int weight = digits.size() + 1;

        final int number = digits.stream().reduce(0, (a, b) -> a + b * (weight - counter.getAndIncrement()));

        final int mod = 11 - number % 11;

        return mod >= 10 ? 0 : mod;
    }

    public static boolean validate(List<Integer> digits){
        return  digits.size() == 11 &&
                digits.get(9) == generateCheckDigit(digits.subList(0,9)) &&
                digits.get(10) == generateCheckDigit(digits.subList(0,10));
    }
}
