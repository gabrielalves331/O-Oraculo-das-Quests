package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory simples para criar DAOs com conexão JDBC.
 */
public class DAOFactory {

    // Retorna nova Connection. Chame this por cada DAO (ou adapte para pooling).
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // A senha deve ser TEMP123 (ou a correta, se já tiver redefinido no MySQL)
        String USUARIO = "root";
        String SENHA = "TEMP123"; 
        String URL_BANCO = "jdbc:mysql://localhost:3306/oraculo_das_quests";
        
        // Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
    }

    public static MissaoDAO criarMissaoDAO() throws SQLException, ClassNotFoundException {
        return new MissaoDAOJDBC(getConnection());
    }

    public static ModeloDAO criarModeloDAO() throws SQLException, ClassNotFoundException {
        return new ModeloDAOJDBC(getConnection());
    }
}
