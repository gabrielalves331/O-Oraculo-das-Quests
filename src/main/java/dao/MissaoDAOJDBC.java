package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Missao;

public class MissaoDAOJDBC implements MissaoDAO {

    private Connection conn;

    public MissaoDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Missao m) {
        String sql = "INSERT INTO missoes (titulo, descricao, dificuldade, recompensa, concluida) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getTitulo());
            st.setString(2, m.getDescricao());
            st.setString(3, m.getDificuldade());
            st.setString(4, m.getRecompensa());
            st.setBoolean(5, m.isConcluida());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir missão: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Missao m) {
        String sql = "UPDATE missoes SET titulo=?, descricao=?, dificuldade=?, recompensa=?, concluida=? WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, m.getTitulo());
            st.setString(2, m.getDescricao());
            st.setString(3, m.getDificuldade());
            st.setString(4, m.getRecompensa());
            st.setBoolean(5, m.isConcluida());
            st.setInt(6, m.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar missão: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM missoes WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar missão: " + e.getMessage());
        }
    }

    @Override
    public Missao buscarPorId(int id) {
        String sql = "SELECT * FROM missoes WHERE id=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Missao m = new Missao(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("dificuldade"),
                    rs.getString("recompensa"),
                    rs.getBoolean("concluida")
                );
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar missão: " + e.getMessage());
        }
    }

    @Override
public List<Missao> listar() {
    String sql = "SELECT * FROM missoes";
    List<Missao> lista = new ArrayList<>();
    
    // try-with-resources garante que o PreparedStatement feche.
    try (PreparedStatement st = conn.prepareStatement(sql)) { 
        
        ResultSet rs = st.executeQuery();
        
        // Loop que itera sobre cada linha retornada do banco
        while (rs.next()) {
            
            // ESSA É A PARTE ESSENCIAL QUE ESTAVA FALTANDO:
            // 1. Mapeamento (criação e preenchimento) do objeto Missao
            Missao m = new Missao(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("descricao"),
                rs.getString("dificuldade"),
                rs.getString("recompensa"),
                rs.getBoolean("concluida")
            );
            
            // 2. Adiciona o objeto Missao à lista
            lista.add(m); 
        }
        
    } catch (SQLException e) {
        // Envolve o SQLException em um RuntimeException
        throw new RuntimeException("Erro ao listar missões: " + e.getMessage()); 
    }
    return lista;
}
}
