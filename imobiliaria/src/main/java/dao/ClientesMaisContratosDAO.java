package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesMaisContratosDAO extends BaseDAO {

    public static class ClienteContratos {
        private String nome;
        private int totalContratos;

        public ClienteContratos(String nome, int totalContratos) {
            this.nome = nome;
            this.totalContratos = totalContratos;
        }

        public String getNome() {
            return nome;
        }

        public int getTotalContratos() {
            return totalContratos;
        }
    }

    // Método que retorna a lista de clientes com seus totais de contratos
    public List<ClienteContratos> listarClientesMaisContratos() {
        List<ClienteContratos> lista = new ArrayList<>();

        String sql =
                "SELECT CL.NOME, COUNT(CO.ID_CONTRATO) AS TOTAL_CONTRATOS " +
                        "FROM CLIENTE CL " +
                        "INNER JOIN CONTRATO CO ON CO.ID_CLIENTE = CL.ID_CLIENTE " +
                        "GROUP BY CL.ID_CLIENTE, CL.NOME " +
                        "ORDER BY TOTAL_CONTRATOS DESC";

        try (Connection conn = con();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("NOME");
                int total = rs.getInt("TOTAL_CONTRATOS");
                lista.add(new ClienteContratos(nome, total));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }

        return lista;
    }

    // Método para mostrar o relatório
    public void mostrarRelatorio() {
        System.out.println("===== RELATÓRIO: CLIENTES COM MAIS CONTRATOS =====");
        List<ClienteContratos> lista = listarClientesMaisContratos();
        for (ClienteContratos c : lista) {
            System.out.println("Cliente: " + c.getNome() + " | Total de contratos: " + c.getTotalContratos());
        }
    }
}
