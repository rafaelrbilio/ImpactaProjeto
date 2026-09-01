package Entidades;

public class OficinaEcologica extends Acao {

    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public boolean isKitMaterial() {
        return kitMaterial;
    }

    public void setKitMaterial(boolean kitMaterial) {
        this.kitMaterial = kitMaterial;
    }

    @Override
    public int calcularPontuacao() {
        int pontuacao = 3 * duracaoHoras;

        if(kitMaterial){
            pontuacao +=10;
        }
        return pontuacao;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: Oficina Ecológica"+
                "\nDuracão:" + duracaoHoras + "horas"+
                "\nKit de Material=" + (kitMaterial?"sim":"não");
    }
}
