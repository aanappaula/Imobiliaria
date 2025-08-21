package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Visibilidade de pacote
class ConnectionFactory {

    // Atributo global / estático
    private static ConnectionFactory instance;

    // Construtor privado
    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        if (instance == null)
            instance = new ConnectionFactory();
        return instance;
    }

    // Criação da conexão com o seu banco de dados.
    public Connection get() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/imobiliaria";
        String user = "postgres";
        String password = "";

        return  DriverManager.getConnection(url, user, password);

    }
}