package Testes;

import Entidades.MutiraoReciclagem;
import Entidades.OficinaEcologica;
import Entidades.Plantio;
import Entidades.Voluntario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoluntarioTeste {

    private Voluntario voluntario;

    @BeforeEach
    public void setUp() {
        voluntario = new Voluntario("João", "joao@email.com", "12345");
    }

    @Test
    @DisplayName("Deve criar um voluntário corretamente")
    public void deveCriarVoluntario() {

        assertEquals("João", voluntario.getNome());
        assertEquals("joao@email.com", voluntario.getEmail());
        assertEquals("12345", voluntario.getMatricula());
        assertEquals(0, voluntario.getAcoes().size());
        assertEquals(0, voluntario.getPontuacao());
    }

    @Test
    @DisplayName("Deve calcular a pontuação total do voluntário")
    public void deveCalcularPontuacao() {

        Plantio plantio = new Plantio(
                1,
                "Plantio",
                "Plantio de mudas",
                "10/06/2026",
                20,
                10
        );

        MutiraoReciclagem mutirao = new MutiraoReciclagem(
                2,
                "Reciclagem",
                "Coleta seletiva",
                "12/06/2026",
                15,
                3
        );

        OficinaEcologica oficina = new OficinaEcologica(
                3,
                "Oficina",
                "Educação ambiental",
                "15/06/2026",
                30,
                2,
                true
        );

        voluntario.adicionarAcao(plantio);
        voluntario.adicionarAcao(mutirao);
        voluntario.adicionarAcao(oficina);

        assertEquals(53, voluntario.getPontuacao());
    }

    @Test
    @DisplayName("Deve adicionar ações ao voluntário")
    public void deveAdicionarAcoes() {

        Plantio plantio = new Plantio(
                1,
                "Plantio",
                "Plantio de mudas",
                "10/06/2026",
                20,
                5
        );

        voluntario.adicionarAcao(plantio);

        assertEquals(1, voluntario.getAcoes().size());
        assertEquals(15, voluntario.getPontuacao());
    }
}
