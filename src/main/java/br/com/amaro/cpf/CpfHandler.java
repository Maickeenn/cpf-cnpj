package br.com.amaro.cpf;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CpfHandler {

    /**
     * Generate a random cpf
     *
     * @return Random cpf
     */
    public static List<Integer> random() {

        List<Integer> collect = new Random().ints(9, 0, 10)
                .boxed()
                .collect(Collectors.toList());
        collect.add(generateCheckDigit(collect));
        collect.add(generateCheckDigit(collect));
        return collect;
    }

    /**
     * Generate a check digit for the first n digits
     *
     * @param digits first n digits
     * @return Check Digit
     */
    public static Integer generateCheckDigit(List<Integer> digits) {

        if(digits == null){
            return 0;
        }

        final AtomicInteger counter = new AtomicInteger();
        final int weight = digits.size() + 1;

        final int number = digits.stream().reduce(0, (a, b) -> a + b * (weight - counter.getAndIncrement()));

        final int mod = 11 - number % 11;

        return mod >= 10 ? 0 : mod;
    }

    /**
     * Validate a cpf
     *
     * @param cpf Digits of a CPF
     * @return {@code true} if the argument is a valid CPF and {@code false} otherwise
     */
    public static boolean validate(List<Integer> cpf) {
        return  cpf != null &&
                cpf.size() == 11 &&
                Objects.equals(cpf.get(9), generateCheckDigit(cpf.subList(0, 9))) &&
                Objects.equals(cpf.get(10), generateCheckDigit(cpf.subList(0, 10)));
    }
}
