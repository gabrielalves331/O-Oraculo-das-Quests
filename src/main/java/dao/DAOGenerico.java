package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement; // NOVO IMPORT

/**
 *
 * @author gabri
 */
public class DAOGenerico {
    
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        // A senha deve ser TEMP123 (ou a correta, se já tiver redefinido no MySQL)
        String USUARIO = "root";
        String SENHA = "TEMP123"; 
        String URL_BANCO = "jdbc:mysql://localhost:3306/oraculo_das_quests";
        
        // Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
    }
    
    /**
     * Executa comandos SQL (INSERT, UPDATE, DELETE) e retorna o ID gerado
     * para INSERTs com AUTO_INCREMENT, ou o número de linhas afetadas.
     * * @param query O comando SQL
     * @param params Os parâmetros do comando
     * @return O ID gerado (para INSERTs) ou o número de linhas afetadas.
     */
    public static int executarComando(String query, Object... params) throws SQLException, ClassNotFoundException {
        // 1. Abre a conexão e prepara o comando solicitando as chaves geradas
        PreparedStatement sql = getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        // 2. Define os parâmetros
        for (int i = 0; i < params.length; i++) {
            sql.setObject(i + 1, params[i]);
        }
        
        // 3. Executa o comando e obtém o número de linhas afetadas
        int result = sql.executeUpdate();
        
        // 4. Tenta recuperar o ID gerado (se for um INSERT)
        ResultSet rs = sql.getGeneratedKeys();
        
        if (rs.next()) {
            int idGerado = rs.getInt(1);
            rs.close();
            sql.close();
            return idGerado; // Retorna o ID gerado (o que o ModeloDAOJDBC espera)
        }
        
        // 5. Fecha e retorna o resultado (para UPDATEs ou DELETEs)
        sql.close();
        return result;
    }
    
    public static ResultSet executarConsulta(String query, Object... params) throws SQLException, ClassNotFoundException {
        // NOTA: Para consultas, o PreparedStatement não é fechado aqui 
        // porque o ResultSet retornado ainda está ativo e precisa da conexão.
        // O fechamento deve ser feito nos métodos listar() dos DAOs.
        PreparedStatement sql = getConexao().prepareStatement(query);
        
        for (int i = 0; i < params.length; i++) {
            sql.setObject(i + 1, params[i]);
        }
        return sql.executeQuery();
    }
    
}