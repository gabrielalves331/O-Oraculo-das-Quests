/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paginas;

import dao.DAOFactory;
import dao.MissaoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Missao;
import modelo.MissaoTableModel;

/**
 *
 * @author gabri
 */
public class QuadroQuests extends javax.swing.JFrame {

    private void carregarMissoes() {
        try {
            // 1. Chamar o DAO para listar
            MissaoDAO dao = DAOFactory.criarMissaoDAO();
            List<Missao> listaDeMissoes = dao.listar();

            // 2. Popular a tabela
            MissaoTableModel model = new MissaoTableModel(listaDeMissoes);

            
            
            // Conecta o modelo dinâmico à JTable
            jTableMissao.setModel(model);
            
        } catch (SQLException | ClassNotFoundException e) {
            // Captura erros de Driver ou Conexão
            JOptionPane.showMessageDialog(this, 
                "Erro de Conexão com o Banco de Dados: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);

        } catch (RuntimeException e) { 
            // Captura o RuntimeException lançado pela sua classe MissaoDAOJDBC
            JOptionPane.showMessageDialog(this, 
                "Erro Interno ao Listar Missões: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarTabela() {
    // Pega o modelo das colunas da tabela
    javax.swing.table.TableColumnModel colunas = jTableMissao.getColumnModel();

    // 1. AJUSTE DE LARGURA DAS COLUNAS
    
    // Coluna 0: ID (Muito pequena)
    colunas.getColumn(0).setPreferredWidth(30); 
    colunas.getColumn(0).setMaxWidth(30); 

    // Coluna 1: Título (Maior para caber o nome da missão)
    colunas.getColumn(1).setPreferredWidth(200);

    // Coluna 2: Dificuldade (Média)
    colunas.getColumn(2).setPreferredWidth(100);

    // Coluna 3: Recompensa (Média)
    colunas.getColumn(3).setPreferredWidth(100);

    // Coluna 4: Status (Média)
    colunas.getColumn(4).setPreferredWidth(100);

    // 2. AJUSTE DE ALINHAMENTO
    
    // Centraliza o texto nas colunas ID, Dificuldade e Status
    javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    colunas.getColumn(0).setCellRenderer(centerRenderer); // ID
    colunas.getColumn(2).setCellRenderer(centerRenderer); // Dificuldade
    colunas.getColumn(4).setCellRenderer(centerRenderer); // Status
    
    // O Título e Recompensa devem manter o alinhamento padrão (esquerda)
}
    
    public QuadroQuests() {
        initComponents();
        this.setSize(650, 500);
        this.setLocationRelativeTo(null);
        carregarMissoes();
        configurarTabela();
        
        jTableMissao.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // Verifica se foi um duplo clique (count == 2)
            if (evt.getClickCount() == 2 && !evt.isConsumed()) {
                evt.consume();
                
                int linhaSelecionada = jTableMissao.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    // Pega o modelo de dados (MissaoTableModel)
                    MissaoTableModel model = (MissaoTableModel) jTableMissao.getModel();
                    modelo.Missao missao = model.getMissao(linhaSelecionada);

                    // 1. Cria um JTextArea para exibir o texto longo
                    javax.swing.JTextArea textArea = new javax.swing.JTextArea(missao.getDescricao());

                    // 2. Configurações para quebra de linha e rolagem
                    textArea.setWrapStyleWord(true); // Quebra palavras inteiras
                    textArea.setLineWrap(true);      // Quebra de linha ativa
                    textArea.setEditable(false);     // Não pode ser editado

                    // 3. Coloca o JTextArea dentro de um JScrollPane para ter barra de rolagem
                    // Mantenha o tamanho para controlar a largura da janela
                    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(450, 250)); 

                    // 4. Exibe o JScrollPane no JOptionPane
                    JOptionPane.showMessageDialog(
                        QuadroQuests.this, 
                        scrollPane, // Passa o scrollPane
                        "Detalhes da Missão - ID: " + missao.getId() + ": " + missao.getTitulo(),
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        } // <--- 🚨 FIM do método mouseClicked
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHub = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMissao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 3, 24)); // NOI18N
        jLabel1.setText("Mural de Missões");

        btnHub.setText("Voltar");
        btnHub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHubActionPerformed(evt);
            }
        });

        jTableMissao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableMissao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addComponent(btnHub))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHub)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHubActionPerformed
        // TODO add your handling code here:
        HubQuests hub = new HubQuests();
        
        hub.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnHubActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuadroQuests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuadroQuests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuadroQuests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuadroQuests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuadroQuests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMissao;
    // End of variables declaration//GEN-END:variables
}
