package Entidades;
import java.util.ArrayList;
import java.util.List;

public class Voluntario {
    private String nome;
    private String email;
    private String matricula;
    private List<Acao> acoes;


    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.acoes = new ArrayList<>();
    }

    public void adicionarAcao(Acao acao) {
        acoes.add(acao);
    }

    public int getPontuacao() {
        int pontos = 0;

        for (Acao acao : acoes) {
            pontos += acao.calcularPontuacao();
        }
        return pontos;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }


    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nEmail: " + email +
                "\nMatrícula: " + matricula +
                "\nQuantidade de ações: " + acoes.size() +
                "\nPontuação: " + getPontuacao();
    }
}