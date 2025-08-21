package dao;

import entidade.Imovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImovelDAO extends BaseDAO {

    public void inserir(Imovel imovel) {
        String sql = "INSERT INTO imovel " +
                "(endereco, cidade, estado, tipo, quartos, banheiros, area_m2, valor_aluguel_base) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = con();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, imovel.getEndereco());
            stmt.setString(2, imovel.getCidade());
            stmt.setString(3, imovel.getEstado());
            stmt.setString(4, imovel.getTipo());
            stmt.setInt(5, imovel.getQuartos());
            stmt.setInt(6, imovel.getBanheiros());
            stmt.setDouble(7, imovel.getAreaM2());
            stmt.setDouble(8, imovel.getValorAluguelBase());

            stmt.executeUpdate();
            System.out.println("Imóvel cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir imóvel: " + e.getMessage());
        }
    }
}
