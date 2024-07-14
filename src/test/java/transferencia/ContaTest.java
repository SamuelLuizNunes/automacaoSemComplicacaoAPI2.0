package transferencia;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContaTest {
    Cliente samuel;
    Cliente ana;
    Conta contaDoSamuel;
    Conta contaDaAna;

    @BeforeEach
    void setUp() {
        samuel = new Cliente("Samuel", "12345678916", "505558886");
        ana = new Cliente("Ana", "32145698789", "789877897");

        contaDoSamuel = new Conta(2968, 398999, 100, samuel);
        contaDaAna = new Conta(8978, 189875, 500, ana);
    }

    @Order(1)
    @Test
    public void realizarTransacao(){
        contaDaAna.transfere(300, contaDoSamuel);

        assertEquals(200, contaDaAna.getSaldo());
        assertEquals(400, contaDoSamuel.getSaldo());
    }

    @Order(2)
    @Test
    public void validarTransferenciaInvalida(){
        boolean resultado = contaDaAna.transfere(501, contaDoSamuel);
        assertFalse(resultado);
    }

}