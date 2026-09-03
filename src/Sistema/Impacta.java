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

public class Impacta {

    private ArrayList<Voluntario> voluntarios;
    private ArrayList<Acao> acoes;
    private int proximoId;

    public Impacta() {
        voluntarios = new ArrayList<>();
        acoes = new ArrayList<>();
        proximoId = 1;
    }

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

    public String exibirVoluntario(String email) {

        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail().equalsIgnoreCase(email)) {

                return voluntario.toString();
            }
        }

        return null;
    }

    public String[] listarVoluntarios() {

        ArrayList<Voluntario> ordenados = new ArrayList<>(voluntarios);

        for (int i = 0; i < ordenados.size() - 1; i++) {
            for (int j = i + 1; j < ordenados.size(); j++) {

                if (ordenados.get(j).getPontuacao()
                        > ordenados.get(i).getPontuacao()) {

                    Voluntario aux = ordenados.get(i);
                    ordenados.set(i, ordenados.get(j));
                    ordenados.set(j, aux);
                }
            }
        }

        String[] resultado = new String[ordenados.size()];

        for (int i = 0; i < ordenados.size(); i++) {
            resultado[i] = ordenados.get(i).getNome();
        }

        return resultado;
    }

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

    public boolean inscreverVoluntario(
            String emailVoluntario,
            int idAcao) {

        Voluntario voluntarioEncontrado = null;
        Acao acaoEncontrada = null;

        for (Voluntario voluntario : voluntarios) {

            if (voluntario.getEmail()
                    .equalsIgnoreCase(emailVoluntario)) {

                voluntarioEncontrado = voluntario;
                break;
            }
        }

        for (Acao acao : acoes) {

            if (acao.getId() == idAcao) {

                acaoEncontrada = acao;
                break;
            }
        }

        if (voluntarioEncontrado == null) {
            return false;
        }

        if (acaoEncontrada == null) {
            return false;
        }

        if (acaoEncontrada.getInscritos()
                .contains(voluntarioEncontrado)) {

            throw new VoluntarioJaInscritoException(
                    "Voluntário já está inscrito nesta ação."
            );
        }

        if (acaoEncontrada.getInscritos().size()
                >= acaoEncontrada.getMaxParticipantes()) {

            throw new AcaoLotadaException(
                    "A ação está lotada."
            );
        }

        acaoEncontrada.adicionarVoluntario(
                voluntarioEncontrado
        );

        voluntarioEncontrado.adicionarAcao(
                acaoEncontrada
        );

        return true;
    }

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