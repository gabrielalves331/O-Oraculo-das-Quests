package modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;
/**
 * Table Model para mapear objetos Missao para o JTable.
 */
public class MissaoTableModel extends AbstractTableModel {
    
    // Armazena a lista de missões que será exibida
    private final List<Missao> missoes;
    
    // Define os nomes das colunas na ordem em que aparecerão na tabela
    private final String[] colunas = {"ID", "Título", "Dificuldade", "Recompensa", "Status"};

    // Construtor que recebe a lista de dados do DAO
    public MissaoTableModel(List<Missao> missoes) {
        this.missoes = missoes;
    }

    // 1. OBRIGATÓRIO: Retorna o número de colunas (o tamanho do array 'colunas')
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    // 2. OBRIGATÓRIO: Retorna o número de linhas (o tamanho da lista 'missoes')
    @Override
    public int getRowCount() {
        return missoes.size();
    }
    
    // OPICIONAL: Retorna o nome da coluna no cabeçalho
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    // 3. OBRIGATÓRIO: Retorna o valor de uma célula específica
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    // Pega o objeto Missao na linha pedida
    Missao missao = missoes.get(rowIndex);
    
    // As colunas estão definidas como: 0:ID, 1:Título, 2:Dificuldade, 3:Recompensa, 4:Status
    return switch (columnIndex) {
        case 0 -> missao.getId();
        case 1 -> missao.getTitulo();
        case 2 -> missao.getDificuldade(); // ✅ CORRIGIDO: Dificuldade (index 2)
        case 3 -> missao.getRecompensa();  // ✅ CORRIGIDO: Recompensa (index 3)
        // Você está usando String para o status, o que é mais simples
        case 4 -> missao.isConcluida() ? "Concluída" : "Em Aberto"; 
        default -> null;
    };
}
    
    // OPICIONAL: Retorna o objeto Missao completo de uma linha
    public Missao getMissao(int rowIndex) {
        return missoes.get(rowIndex);
    }
    
    // OPICIONAL: Define o tipo de dado da coluna (importante para ordenação/edição)
    // CÓDIGO CORRIGIDO
@Override
public Class<?> getColumnClass(int columnIndex) {
    // Para o ID
    if (columnIndex == 0) {
        return Integer.class; 
    }
    // Como você retorna "Concluída" ou "Em Aberto" (uma String)
    // a coluna 4 também deve ser String
    if (columnIndex == 4) {
        return String.class; // ✅ CORRIGIDO: Agora é String, conforme o getValueAt.
    }
    return String.class;
}
}