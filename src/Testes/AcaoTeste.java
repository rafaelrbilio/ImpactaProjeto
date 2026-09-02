package Testes;

import Entidades.Acao;
import Entidades.Voluntario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AcaoTest {

    static class AcaoTeste extends Acao {

        public AcaoTeste(int id, String titulo, String descricao,
                         String data, int maxParticipantes) {
            super(id, titulo, descricao, data, maxParticipantes);
        }

        @Override
        public int calcularPontuacao() {
            return 10;
        }
    }

    @Test
    @DisplayName("Deve criar a ação corretamente")
    void deveCriarAcaoCorretamente() {
        Acao acao = new AcaoTeste(1, "Plantio de Árvores", "Ação de plantio de árvores", "10/09/2026", 10);
        assertEquals(1, acao.getId());
        assertEquals("Plantio de Árvores", acao.getTitulo());
        assertEquals("Ação de plantio de árvores", acao.getDescricao());
        assertEquals("10/09/2026", acao.getData());
        assertEquals(10, acao.getMaxParticipantes());
        assertNotNull(acao.getInscritos());
        assertTrue(acao.getInscritos().isEmpty());
    }

    @Test
    @DisplayName("Deve calcular a pontuação")
    void deveCalcularPontuacao() {
        Acao acao = new AcaoTeste(1, "Plantio de Árvores", "Ação de plantio", "10/09/2026", 10);
        assertEquals(10, acao.calcularPontuacao());
    }

    @Test
    @DisplayName("Deve adicionar um voluntário")
    void deveAdicionarVoluntario() {
        Acao acao = new AcaoTeste(1, "Plantio de Árvores", "Ação de plantio", "10/09/2026", 10);
        Voluntario voluntario = new Voluntario("Paulo", "paulo@email.com", "20260001");
        boolean resultado = acao.adicionarVoluntario(voluntario);
        assertTrue(resultado);
        assertEquals(1, acao.getInscritos().size());
        assertTrue(acao.getInscritos().contains(voluntario));
    }

    @Test
    @DisplayName("Não deve adicionar o mesmo voluntário duas vezes")
    void naoDeveAdicionarMesmoVoluntarioDuasVezes() {
        Acao acao = new AcaoTeste(1, "Plantio de Árvores", "Ação de plantio", "10/09/2026", 10);
        Voluntario voluntario = new Voluntario("Paulo", "paulo@email.com", "20260001"
        );
        assertTrue(acao.adicionarVoluntario(voluntario));
        assertFalse(acao.adicionarVoluntario(voluntario));
        assertEquals(1, acao.getInscritos().size());
    }

    @Test
    @DisplayName("Não deve ultrapassar o limite de participantes")
    void naoDeveUltrapassarLimiteDeParticipantes() {
        Acao acao = new AcaoTeste(1, "Plantio de Árvores", "Ação de plantio", "10/09/2026", 1);

        Voluntario voluntario1 = new Voluntario("Paulo", "paulo@email.com", "20260001");
        Voluntario voluntario2 = new Voluntario("Vinicius", "vinicius@email.com","20260002");
        assertTrue(acao.adicionarVoluntario(voluntario1));
        assertFalse(acao.adicionarVoluntario(voluntario2));
        assertEquals(1, acao.getInscritos().size());
    }
}