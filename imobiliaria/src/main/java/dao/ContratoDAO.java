package dao;

import entidade.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContratoDAO extends BaseDAO {

    public static void inserir(Contrato contrato) {
        String sql = "INSERT INTO contrato " +
                "(id_imovel, id_cliente, valor_aluguel, data_inicio, data_fim, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = con();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getIdImovel());
            stmt.setInt(2, contrato.getIdCliente());
            stmt.setDouble(3, contrato.getValorAluguel());
            stmt.setDate(4, contrato.getDataInicio());
            stmt.setDate(5, contrato.getDataFim());
            stmt.setString(6, contrato.getStatus());

            stmt.executeUpdate();
            System.out.println("Contrato cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir contrato: " + e.getMessage());
        }
    }
}
