package Entidades;

public class Plantio extends Acao{
    private int qntMudas;

    public Plantio(int id, String titulo, String descricao, String data, int maxParticipantes, int qntMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qntMudas = qntMudas;
    }

    public int getQntMudas() {
        return qntMudas;
    }

    public void setQntMudas(int qntMudas) {
        this.qntMudas = qntMudas;
    }

    @Override
    public int calcularPontuacao() {

        return 5 + (2*qntMudas);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: plantio de mudas" +
                "\nquantidade das mudas: " + qntMudas;
    }
}
