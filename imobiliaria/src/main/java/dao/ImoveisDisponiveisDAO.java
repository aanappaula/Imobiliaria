package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImoveisDisponiveisDAO extends BaseDAO {

    public static class ImovelDisponivel {
        private int id;
        private String endereco;
        private String cidade;
        private String estado;
        private String tipo;
        private int quartos;
        private int banheiros;
        private double areaM2;
        private double valorAluguelBase;

        public ImovelDisponivel(int id, String endereco, String cidade, String estado,
                                String tipo, int quartos, int banheiros, double areaM2, double valorAluguelBase) {
            this.id = id;
            this.endereco = endereco;
            this.cidade = cidade;
            this.estado = estado;
            this.tipo = tipo;
            this.quartos = quartos;
            this.banheiros = banheiros;
            this.areaM2 = areaM2;
            this.valorAluguelBase = valorAluguelBase;
        }

        // Getters
        public int getId() { return id; }
        public String getEndereco() { return endereco; }
        public String getCidade() { return cidade; }
        public String getEstado() { return estado; }
        public String getTipo() { return tipo; }
        public int getQuartos() { return quartos; }
        public int getBanheiros() { return banheiros; }
        public double getAreaM2() { return areaM2; }
        public double getValorAluguelBase() { return valorAluguelBase; }
    }

    public List<ImovelDisponivel> listarImoveisDisponiveis() {
        List<ImovelDisponivel> lista = new ArrayList<>();
        String sql = "SELECT I.ID_IMOVEL, I.ENDERECO, I.CIDADE, I.ESTADO, I.TIPO, I.QUARTOS, I.BANHEIROS, I.AREA_M2, I.VALOR_ALUGUEL_BASE " +
                "FROM IMOVEL I " +
                "LEFT JOIN CONTRATO C ON C.ID_IMOVEL = I.ID_IMOVEL AND C.STATUS = 'Ativo' " +
                "WHERE C.ID_CONTRATO IS NULL";

        try (Connection conn = con();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ImovelDisponivel imovel = new ImovelDisponivel(
                        rs.getInt("ID_IMOVEL"),
                        rs.getString("ENDERECO"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("TIPO"),
                        rs.getInt("QUARTOS"),
                        rs.getInt("BANHEIROS"),
                        rs.getDouble("AREA_M2"),
                        rs.getDouble("VALOR_ALUGUEL_BASE")
                );
                lista.add(imovel);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }

        return lista;
    }

    public void mostrarRelatorio() {
        System.out.println("===== RELATÓRIO: IMÓVEIS DISPONÍVEIS =====");
        List<ImovelDisponivel> imoveis = listarImoveisDisponiveis();
        for (ImovelDisponivel i : imoveis) {
            System.out.println("ID: " + i.getId() +
                    " | Endereço: " + i.getEndereco() +
                    " | Cidade: " + i.getCidade() +
                    " | Estado: " + i.getEstado() +
                    " | Tipo: " + i.getTipo() +
                    " | Quartos: " + i.getQuartos() +
                    " | Banheiros: " + i.getBanheiros() +
                    " | Área: " + i.getAreaM2() + " m²" +
                    " | Valor Base: R$ " + i.getValorAluguelBase());
        }
    }
}
