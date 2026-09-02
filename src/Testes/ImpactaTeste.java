package Testes;

import Sistema.Impacta;
import Execoes.AcaoLotadaException;
import Execoes.EmailJaCadastradoException;
import Execoes.VoluntarioJaInscritoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImpactaTeste {

    private Impacta impacta;

    @BeforeEach
    public void configurar() {
        impacta = new Impacta();
    }

    @Test
    @DisplayName("Deve cadastrar um voluntário")
    public void deveCadastrarVoluntario() {

        boolean resultado = impacta.cadastrarVoluntario(
                "Rafael",
                "rafael@gmail.com",
                "001"
        );

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Não deve permitir e-mail duplicado")
    public void naoDevePermitirEmailDuplicado() {

        impacta.cadastrarVoluntario(
                "Rafael",
                "rafael@gmail.com",
                "001"
        );

        assertThrows(
                EmailJaCadastradoException.class,
                () -> impacta.cadastrarVoluntario(
                        "Italo",
                        "rafael@gmail.com",
                        "002"
                )
        );
    }

    @Test
    @DisplayName("Deve calcular a pontuação do plantio")
    public void deveCalcularPontuacaoDoPlantio() {

        int idPlantio = impacta.cadastrarPlantio(
                "Plantio de Mudas",
                "Plantio de árvores",
                "10/09/2026",
                20,
                50
        );

        String detalhes =
                impacta.exibirDetalhesAcao(idPlantio);

        assertTrue(detalhes.contains("Pontuação: 105"));
    }

    @Test
    @DisplayName("Deve calcular a pontuação do mutirão")
    public void deveCalcularPontuacaoDoMutirao() {

        int idMutirao = impacta.cadastrarMutirao(
                "Mutirão de Reciclagem",
                "Coleta de materiais recicláveis",
                "11/09/2026",
                20,
                5
        );

        String detalhes =
                impacta.exibirDetalhesAcao(idMutirao);

        assertTrue(detalhes.contains("Pontuação: 20"));
    }

    @Test
    @DisplayName("Deve calcular a pontuação da oficina com kit")
    public void deveCalcularPontuacaoDaOficinaComKit() {

        int idOficina = impacta.cadastrarOficina(
                "Oficina Ecológica",
                "Oficina sobre sustentabilidade",
                "12/09/2026",
                20,
                4,
                true
        );

        String detalhes =
                impacta.exibirDetalhesAcao(idOficina);

        assertTrue(detalhes.contains("Pontuação: 22"));
    }

    @Test
    @DisplayName("Deve calcular a pontuação da oficina sem kit")
    public void deveCalcularPontuacaoDaOficinaSemKit() {

        int idOficina = impacta.cadastrarOficina(
                "Oficina Ecológica",
                "Oficina sobre sustentabilidade",
                "13/09/2026",
                20,
                4,
                false
        );

        String detalhes =
                impacta.exibirDetalhesAcao(idOficina);

        assertTrue(detalhes.contains("Pontuação: 12"));
    }

    @Test
    @DisplayName("Deve inscrever um voluntário em uma ação")
    public void deveInscreverVoluntario() {

        impacta.cadastrarVoluntario(
                "Rafael",
                "rafael@gmail.com",
                "001"
        );

        int idAcao = impacta.cadastrarPlantio(
                "Plantio",
                "Plantio de mudas",
                "15/09/2026",
                10,
                20
        );

        boolean resultado =
                impacta.inscreverVoluntario(
                        "rafael@gmail.com",
                        idAcao
                );

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Não deve permitir inscrição duplicada")
    public void naoDevePermitirDuplaInscricao() {

        impacta.cadastrarVoluntario(
                "Rafael",
                "rafael@gmail.com",
                "001"
        );

        int idAcao = impacta.cadastrarPlantio(
                "Plantio",
                "Plantio de mudas",
                "15/09/2026",
                10,
                20
        );

        impacta.inscreverVoluntario(
                "rafael@gmail.com",
                idAcao
        );

        assertThrows(
                VoluntarioJaInscritoException.class,
                () -> impacta.inscreverVoluntario(
                        "rafael@gmail.com",
                        idAcao
                )
        );
    }

    @Test
    @DisplayName("Não deve permitir inscrição em ação lotada")
    public void naoDevePermitirAcaoLotada() {

        impacta.cadastrarVoluntario(
                "Rafael",
                "rafael@gmail.com",
                "001"
        );

        impacta.cadastrarVoluntario(
                "Italo",
                "italo@gmail.com",
                "002"
        );

        int idAcao = impacta.cadastrarPlantio(
                "Plantio",
                "Plantio de mudas",
                "20/09/2026",
                1,
                20
        );

        impacta.inscreverVoluntario(
                "rafael@gmail.com",
                idAcao
        );

        assertThrows(
                AcaoLotadaException.class,
                () -> impacta.inscreverVoluntario(
                        "italo@gmail.com",
                        idAcao
                )
        );
    }

    @Test
    @DisplayName("Deve ordenar os voluntários por pontuação")
    public void deveOrdenarVoluntariosPorPontuacao() {

        impacta.cadastrarVoluntario(
                "Paulo",
                "paulo@gmail.com",
                "001"
        );

        impacta.cadastrarVoluntario(
                "Vinicius",
                "vinicius@gmail.com",
                "002"
        );

        impacta.cadastrarVoluntario(
                "Raiff",
                "raiff@gmail.com",
                "003"
        );

        int plantio = impacta.cadastrarPlantio(
                "Plantio",
                "Plantio de mudas",
                "21/09/2026",
                10,
                50
        );

        int mutirao = impacta.cadastrarMutirao(
                "Mutirão",
                "Reciclagem",
                "22/09/2026",
                10,
                5
        );

        impacta.inscreverVoluntario(
                "vinicius@email.com",
                plantio
        );

        impacta.inscreverVoluntario(
                "raiff@gmail.com",
                plantio
        );

        impacta.inscreverVoluntario(
                "paulo@gmail.com",
                mutirao
        );

        String[] ranking =
                impacta.listarVoluntarios();

        assertEquals("Vinicius", ranking[0]);
        assertEquals("Raiff", ranking[1]);
        assertEquals("Paulo", ranking[2]);
    }
}