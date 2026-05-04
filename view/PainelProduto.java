package view; // Ajustado de model para view

import controller.ProdutoController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class PainelProduto extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField txtCodProd;
    private JTextField txtNomeProd;
    private JTable table;
    private ProdutoController controller;

    public PainelProduto(ProdutoController controller) {
        setBackground(new Color(100, 149, 237));
        this.controller = controller;
        setLayout(null);
        
        JLabel lblTitulo = new JLabel("Cadastro de Produtos Finalizados");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(150, 11, 350, 30);
        add(lblTitulo);

        txtCodProd = new JTextField();
        txtCodProd.setBounds(80, 60, 100, 20);
        add(txtCodProd);

        txtNomeProd = new JTextField();
        txtNomeProd.setBounds(80, 90, 300, 20);
        add(txtNomeProd);

        JTextArea txtDescricao = new JTextArea();
        txtDescricao.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txtDescricao.setBounds(20, 140, 360, 50);
        add(txtDescricao);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 210, 500, 150);
        add(scrollPane);

        table = new JTable();
        String[] colunas = {"Cód. Produto", "Nome Comercial", "Descrição"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        JButton btnSalvar = new JButton("Salvar Produto");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 160, 120, 30);
        add(btnSalvar);
        
        // AÇÃO DE SALVAR ATUALIZADA
        btnSalvar.addActionListener(e -> {
            try {
                String codigo = txtCodProd.getText();
                String nome = txtNomeProd.getText();
                // Como esta tela não tem campo de 'quantidade' e 'custo', 
                // definimos valores padrão ou tratamos a descrição
                int quantidadePadrao = 0; 
                double custoPadrao = 0.0;

                // 1. Salva no Controller (Centraliza os dados)
                controller.cadastrarProduto(codigo, nome, quantidadePadrao, custoPadrao);
                
                // 2. Atualiza a tabela local da tela
                atualizarTabelaLocal();

                // 3. Limpa os campos
                txtCodProd.setText("");
                txtNomeProd.setText("");
                txtDescricao.setText("");
                
            } catch (Exception erro) {
                System.out.println("Erro ao salvar no PainelProduto: " + erro.getMessage());
            }
        });
    }

    // Método para manter a tabela desta tela sincronizada com o Controller
    public void atualizarTabelaLocal() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        for (Produto p : controller.getListaProdutos()) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNome(), "Produto cadastrado via painel"});
        }
    }
}
