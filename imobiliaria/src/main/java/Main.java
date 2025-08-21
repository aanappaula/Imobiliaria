import dao.*;
import entidade.Cliente;
import entidade.Contrato;
import entidade.Imovel;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Cliente
        Cliente cliente1 = new Cliente("Larissa Manuela", "12349868900", "99999-9999", "joao@email.com");
        ClienteDAO.inserir(cliente1);

        // Imóvel
        Imovel imovel1 = new Imovel("Rua A, 123", "São Paulo", "SP", "Apartamento",
                3, 2, 85.5, 2500.00);
        ImovelDAO imovelDAO = new ImovelDAO();
        imovelDAO.inserir(imovel1);

        // Contrato
        Date dataInicio = Date.valueOf("2025-01-01");
        Date dataFim = Date.valueOf("2025-12-31");
        Contrato contrato1 = new Contrato(1, 1, 1500.00, dataInicio, dataFim, "ativo");
        ContratoDAO.inserir(contrato1);

        // Relatório Clientes com mais contratos
        ClientesMaisContratosDAO clientesMaisContratosDAO = new ClientesMaisContratosDAO();
        List<ClientesMaisContratosDAO.ClienteContratos> clientes = clientesMaisContratosDAO.listarClientesMaisContratos();

        for (ClientesMaisContratosDAO.ClienteContratos c : clientes) {
            System.out.println(c.getNome() + " - " + c.getTotalContratos());
        }

        // Relatório contratos ativos
        ContratosAtivosDAO contratosAtivosDAO = new ContratosAtivosDAO();
        contratosAtivosDAO.mostrarRelatorio();

        // Relatório contratos expirando em 30 dias
        ContratosExp30DAO contratosExp30DAO = new ContratosExp30DAO();
        contratosExp30DAO.mostrarRelatorio();

        // Relatório imóveis disponíveis
        ImoveisDisponiveisDAO imoveisDisponiveisDAO = new ImoveisDisponiveisDAO();
        imoveisDisponiveisDAO.mostrarRelatorio();
    }
}
