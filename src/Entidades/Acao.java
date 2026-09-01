package Entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Acao {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected String data;
    protected int maxParticipantes;
    protected List<Voluntario> inscritos;

    public Acao(int id, String titulo, String descricao, String data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.inscritos = new ArrayList<>();
    }

    public abstract int calcularPontuacao();

    public boolean adicionarVoluntario(Voluntario voluntario){
        if(inscritos.size()>=maxParticipantes){
            return false;
        }
        if(inscritos.contains(voluntario)){
            return false;
        }
        inscritos.add(voluntario);
        return true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public List<Voluntario> getInscritos() {
        return inscritos;
    }
}
