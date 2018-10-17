package sample.core.models;

import sample.Hospedagem;
import sample.Passagem;

public class Pacote {
    private Hospedagem hospedagem;
    private Passagem passagem;


    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }
}
