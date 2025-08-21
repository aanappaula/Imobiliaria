package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratosExp30DAO {

    public static class ContratoExpirando {
        private int idContrato;
        private int idCliente;
        private int idImovel;
        private String status;
        private java.sql.Date dataFim;

        public ContratoExpirando(int idContrato, int idCliente, int idImovel, String status, java.sql.Date dataFim) {
            this.idContrato = idContrato;
            this.idCliente = idCliente;
            this.idImovel = idImovel;
            this.status = status;
            this.dataFim = dataFim;
        }

        public int getIdContrato() { return idContrato; }
        public int getIdCliente() { return idCliente; }
        public int getIdImovel() { return idImovel; }
        public String getStatus() { return status; }
        public java.sql.Date getDataFim() { return dataFim; }
    }

    public List<ContratoExpirando> listarContratosExp30() {
        List<ContratoExpirando> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONTRATO WHERE DATA_FIM <= CURRENT_DATE + INTERVAL '30 DAY'";

        try (Connection conn = BaseDAO.con();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContratoExpirando contrato = new ContratoExpirando(
                        rs.getInt("ID_CONTRATO"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ID_IMOVEL"),
                        rs.getString("STATUS"),
                        rs.getDate("DATA_FIM")
                );
                lista.add(contrato);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
        return lista;
    }

    public void mostrarRelatorio() {
        System.out.println("==== CONTRATOS EXPIRANDO EM ATÉ 30 DIAS ====");
        List<ContratoExpirando> contratos = listarContratosExp30();
        for (ContratoExpirando c : contratos) {
            System.out.println("ID: " + c.getIdContrato() +
                    " | Cliente: " + c.getIdCliente() +
                    " | Imóvel: " + c.getIdImovel() +
                    " | Status: " + c.getStatus() +
                    " | Data Fim: " + c.getDataFim());
        }
    }
}
