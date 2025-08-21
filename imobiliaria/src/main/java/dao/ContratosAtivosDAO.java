package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidade.Contrato;

public class ContratosAtivosDAO {

    // Classe interna para representar um contrato ativo (se quiser pode usar entidade.Contrato diretamente)
    public static class ContratoAtivo {
        private int idContrato;
        private int idCliente;
        private int idImovel;
        private String status;
        private java.sql.Date dataInicio;
        private java.sql.Date dataFim;
        private double valorAluguel;

        public ContratoAtivo(int idContrato, int idCliente, int idImovel, String status,
                             java.sql.Date dataInicio, java.sql.Date dataFim, double valorAluguel) {
            this.idContrato = idContrato;
            this.idCliente = idCliente;
            this.idImovel = idImovel;
            this.status = status;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
            this.valorAluguel = valorAluguel;
        }

        // Getters
        public int getIdContrato() { return idContrato; }
        public int getIdCliente() { return idCliente; }
        public int getIdImovel() { return idImovel; }
        public String getStatus() { return status; }
        public java.sql.Date getDataInicio() { return dataInicio; }
        public java.sql.Date getDataFim() { return dataFim; }
        public double getValorAluguel() { return valorAluguel; }
    }

    // Método para buscar os contratos ativos e retornar como lista
    public List<ContratoAtivo> listarContratosAtivos() {
        List<ContratoAtivo> lista = new ArrayList<>();

        String sql = "SELECT * FROM CONTRATO WHERE STATUS = 'Ativo'";

        try (Connection conn = BaseDAO.con();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContratoAtivo contrato = new ContratoAtivo(
                        rs.getInt("ID_CONTRATO"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ID_IMOVEL"),
                        rs.getString("STATUS"),
                        rs.getDate("DATA_INICIO"),
                        rs.getDate("DATA_FIM"),
                        rs.getDouble("VALOR_ALUGUEL")
                );
                lista.add(contrato);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }

        return lista;
    }

    // Método para mostrar o relatório
    public void mostrarRelatorio() {
        System.out.println("==== CONTRATOS ATIVOS ====");
        List<ContratoAtivo> contratos = listarContratosAtivos();
        for (ContratoAtivo c : contratos) {
            System.out.println("ID: " + c.getIdContrato() +
                    " | Cliente: " + c.getIdCliente() +
                    " | Imóvel: " + c.getIdImovel() +
                    " | Status: " + c.getStatus() +
                    " | Início: " + c.getDataInicio() +
                    " | Fim: " + c.getDataFim() +
                    " | Valor: R$ " + c.getValorAluguel());
        }
    }
}
