package Sistema;

import Entidades.Acao;
import Entidades.Plantio;
import Entidades.MutiraoReciclagem;
import Entidades.OficinaEcologica;
import Entidades.Voluntario;

import Execoes.AcaoLotadaException;
import Execoes.EmailJaCadastradoException;
import Execoes.VoluntarioJaInscritoException;

import java.util.ArrayList;
import java.util.Comparator;

public class Impacta {

    private ArrayList<Voluntario> voluntarios;
    private ArrayList<Acao> acoes;
    private int proximoId;

    public Impacta() {
        voluntarios = new ArrayList<>();
        acoes = new ArrayList<>();
        proximoId = 1;
    }

    // ==========================================
    // CADASTRAR VOLUNTÁRIO
    // ==========================================

    public boolean cadastrarVoluntario(
            String nome,
            String email,
            String matricula) {

        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {

                throw new EmailJaCadastradoException(
                        "E-mail já cadastrado."
                );
            }
        }

        Voluntario voluntario =
                new Voluntario(nome, email, matricula);

        voluntarios.add(voluntario);

        return true;
    }

    // ==========================================
    // EXIBIR VOLUNTÁRIO
    // ==========================================

    public String exibirVoluntario(String email) {

        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {

                return voluntario.toString();
            }
        }

        return null;
    }

    // ==========================================
    // LISTAR VOLUNTÁRIOS
    // ==========================================

    public String[] listarVoluntarios() {

        voluntarios.sort(
                Comparator
                        .comparingInt(Voluntario::getPontuacao)
                        .reversed()
                        .thenComparing(Voluntario::getNome)
        );

        String[] resultado =
                new String[voluntarios.size()];

        for (int i = 0; i < voluntarios.size(); i++) {

            resultado[i] =
                    voluntarios.get(i).getNome();
        }

        return resultado;
    }

    // ==========================================
    // CADASTRAR PLANTIO
    // ==========================================

    public int cadastrarPlantio(
            String titulo,
            String descricao,
            String data,
            int maxParticipantes,
            int qtdMudas) {

        Plantio plantio = new Plantio(
                proximoId,
                titulo,
                descricao,
                data,
                maxParticipantes,
                qtdMudas
        );

        acoes.add(plantio);

        int id = proximoId;

        proximoId++;

        return id;
    }

    // ==========================================
    // CADASTRAR MUTIRÃO
    // ==========================================

    public int cadastrarMutirao(
            String titulo,
            String descricao,
            String data,
            int maxParticipantes,
            int duracaoHoras) {

        MutiraoReciclagem mutirao =
                new MutiraoReciclagem(
                        proximoId,
                        titulo,
                        descricao,
                        data,
                        maxParticipantes,
                        duracaoHoras
                );

        acoes.add(mutirao);

        int id = proximoId;

        proximoId++;

        return id;
    }

    // ==========================================
    // CADASTRAR OFICINA
    // ==========================================

    public int cadastrarOficina(
            String titulo,
            String descricao,
            String data,
            int maxParticipantes,
            int duracaoHoras,
            boolean kitMaterial) {

        OficinaEcologica oficina =
                new OficinaEcologica(
                        proximoId,
                        titulo,
                        descricao,
                        data,
                        maxParticipantes,
                        duracaoHoras,
                        kitMaterial
                );

        acoes.add(oficina);

        int id = proximoId;

        proximoId++;

        return id;
    }

    // ==========================================
    // INSCREVER VOLUNTÁRIO
    // ==========================================

    public boolean inscreverVoluntario(
            String emailVoluntario,
            int idAcao) {

        Voluntario voluntarioEncontrado = null;
        Acao acaoEncontrada = null;

        // Procurar voluntário pelo e-mail
        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail()
                    .equalsIgnoreCase(emailVoluntario)) {

                voluntarioEncontrado = voluntario;
                break;
            }
        }

        // Procurar ação pelo ID
        for (Acao acao : acoes) {

            if (acao.getId() == idAcao) {

                acaoEncontrada = acao;
                break;
            }
        }

        // Voluntário não encontrado
        if (voluntarioEncontrado == null) {
            return false;
        }

        // Ação não encontrada
        if (acaoEncontrada == null) {
            return false;
        }

        // Verificar inscrição duplicada
        if (acaoEncontrada.getInscritos()
                .contains(voluntarioEncontrado)) {

            throw new VoluntarioJaInscritoException(
                    "Voluntário já está inscrito nesta ação."
            );
        }

        // Verificar se a ação está lotada
        if (acaoEncontrada.getInscritos().size()
                >= acaoEncontrada.getMaxParticipantes()) {

            throw new AcaoLotadaException(
                    "A ação está lotada."
            );
        }

        // Adicionar voluntário na ação
        acaoEncontrada.adicionarVoluntario(
                voluntarioEncontrado
        );

        // Adicionar ação ao voluntário
        voluntarioEncontrado.adicionarAcao(
                acaoEncontrada
        );

        return true;
    }

    // ==========================================
    // EXIBIR DETALHES DA AÇÃO
    // ==========================================

    public String exibirDetalhesAcao(int idAcao) {

        for (Acao acao : acoes) {

            if (acao.getId() == idAcao) {

                String resultado =
                        "ID: " + acao.getId()
                                + "\nTítulo: " + acao.getTitulo()
                                + "\nDescrição: " + acao.getDescricao()
                                + "\nData: " + acao.getData()
                                + "\nMáximo de participantes: "
                                + acao.getMaxParticipantes()
                                + "\nPontuação: "
                                + acao.calcularPontuacao()
                                + "\nVoluntários inscritos:";

                for (Voluntario voluntario :
                        acao.getInscritos()) {

                    resultado +=
                            "\n- " + voluntario.getNome();
                }

                return resultado;
            }
        }

        return null;
    }
}