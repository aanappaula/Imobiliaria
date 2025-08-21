package entidade;

public class Imovel {
    private String endereco;
    private String cidade;
    private String estado;
    private String tipo;
    private int quartos;
    private int banheiros;
    private double areaM2;
    private double valorAluguelBase;

    // Construtor
    public Imovel(String endereco, String cidade, String estado, String tipo,
                  int quartos, int banheiros, double areaM2, double valorAluguelBase) {
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.quartos = quartos;
        this.banheiros = banheiros;
        this.areaM2 = areaM2;
        this.valorAluguelBase = valorAluguelBase;
    }

    // Getters e Setters
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuartos() {
        return quartos;
    }
    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public int getBanheiros() {
        return banheiros;
    }
    public void setBanheiros(int banheiros) {
        this.banheiros = banheiros;
    }

    public double getAreaM2() {
        return areaM2;
    }
    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    public double getValorAluguelBase() {
        return valorAluguelBase;
    }
    public void setValorAluguelBase(double valorAluguelBase) {
        this.valorAluguelBase = valorAluguelBase;
    }
}
