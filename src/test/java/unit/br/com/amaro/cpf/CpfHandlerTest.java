package unit.br.com.amaro.cpf;

import br.com.amaro.cpf.CpfHandler;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CpfHandlerTest {

    @Test
    public void shouldGenerateFirstCheckDigit() {

        List<Integer> first8Digits = Arrays.asList(4, 2, 2, 0, 1, 6, 7, 8, 8);

        assertEquals(9, CpfHandler.generateCheckDigit(first8Digits));
    }

    @Test
    public void shouldGenerateSecondCheckDigit() {
        List<Integer> first9Digits = Arrays.asList(4, 2, 2, 0, 1, 6, 7, 8, 8, 9);

        assertEquals(8, CpfHandler.generateCheckDigit(first9Digits));
    }

    @Test
    public void shouldValidateCpf() {
        List<Integer> cpf = Arrays.asList(4, 2, 2, 0, 1, 6, 7, 8, 8, 9, 8);

        assertTrue(CpfHandler.validate(cpf));
    }

    @Test
    public void shouldGenerateRandomCpf() {
        assertTrue(CpfHandler.validate(CpfHandler.random()));
    }

    @Test
    public void allMethodsShouldBeNullSafe(){
        CpfHandler.validate(null);
        CpfHandler.generateCheckDigit(null);
    }
}
