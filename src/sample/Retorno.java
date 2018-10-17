package sample;

import java.util.List;

public class Retorno implements java.io.Serializable {
    private static final long serialVersionUID = 8367908553994431734L;
    private List<Hotel> hoteis;
    private List<Voo> voos;
   // private List<Pacote> pacotes;


    public List<Hotel> getHoteis() {
        return hoteis;
    }

    public void setHoteis(List<Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    public List<Voo> getVoos() {
        return voos;
    }

    public void setVoos(List<Voo> voos) {
        this.voos = voos;
    }

   // public List<Pacote> getPacotes() {
    //    return pacotes;
   // }

   // public void setPacotes(List<Pacote> pacotes) {
    //    this.pacotes = pacotes;
   // }
}
