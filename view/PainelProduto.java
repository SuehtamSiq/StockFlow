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

        JLabel lblCodProd = new JLabel("Código");
        lblCodProd.setFont(new Font("Arial", Font.BOLD, 12));
        lblCodProd.setBounds(20, 60, 100, 12);
        add(lblCodProd);
        txtCodProd = new JTextField();
        txtCodProd.setBounds(80, 60, 100, 20);
        add(txtCodProd);

        JLabel lblNomeProd = new JLabel("Nome");
        lblNomeProd.setFont(new Font("Arial", Font.BOLD, 12));
        lblNomeProd.setBounds(20, 90, 100, 12);
        add(lblNomeProd);
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
        
        // AÇÃO DE SALVAR ATUALIZADA COM VALIDAÇÕES
        btnSalvar.addActionListener(e -> {
            try {
                String codigo = txtCodProd.getText().trim();
                String nome = txtNomeProd.getText().trim();
                
                // Valida se os campos de texto estão vazios
                if (codigo.isEmpty() || nome.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Todos os campos (Código e Nome) devem ser preenchidos!", 
                        "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int quantidadePadrao = 0; 
                double custoPadrao = 0.0;

                // Salva no Controller (pode lançar a exceção de código duplicado)
                controller.cadastrarProduto(codigo, nome, quantidadePadrao, custoPadrao);
                
                atualizarTabelaLocal();

                // Limpa os campos após o sucesso
                txtCodProd.setText("");
                txtNomeProd.setText("");
                txtDescricao.setText("");
                
                javax.swing.JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
                
            } catch (IllegalArgumentException erro) {
                javax.swing.JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro de Cadastro", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception erro) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro inesperado: " + erro.getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
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
