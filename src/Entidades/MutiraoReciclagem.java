package Entidades;

public class MutiraoReciclagem extends Acao {

    private int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao,
                             String data, int maxParticipantes, int duracaoHoras) {

        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        return duracaoHoras * 4;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Mutirão de Reciclagem" +
                "\nDuração: " + duracaoHoras + " horas";
    }
}