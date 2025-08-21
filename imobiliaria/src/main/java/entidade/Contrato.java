package entidade;

import java.sql.Date;

public class Contrato {
    private int idImovel;
    private int idCliente;
    private double valorAluguel;
    private Date dataInicio;
    private Date dataFim;
    private String status;

    // Construtor
    public Contrato(int idImovel, int idCliente, double valorAluguel,
                    Date dataInicio, Date dataFim, String status) {
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.valorAluguel = valorAluguel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    // Getters e Setters
    public int getIdImovel() {
        return idImovel;
    }
    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }
    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
