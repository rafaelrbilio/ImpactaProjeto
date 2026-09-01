package Entidades;

public class MutiraoReciclagem extends Acao {

    private int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras){
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras=duracaoHoras;
    }
    @Override
    public int calcularPontuacao() {
        int pontos = duracaoHoras * 3;

        if (kitMaterial) {
            pontos += 10;
        }
        return pontos;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }
    public void setDuracaoHoras(int duracaoHoras){
        this.duracaoHoras = duracaoHoras;
    }
    public boolean isKitMaterial() {
        return kitMaterial;
    }
    public void setKitMaterial(boolean kitMaterial) {
        this.kitMaterial = kitMaterial;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Oficina Ecológica" +
                "\nDuração: " + duracaoHoras + " horas" +
                "\nKit de material: " + (kitMaterial ? "Sim" : "Não");
    }
}
