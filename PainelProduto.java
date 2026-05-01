package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import controller.ProdutoController;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

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

        JLabel lblTitulo = new JLabel("Cadastro de Produtos ");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(150, 11, 300, 30);
        add(lblTitulo);

        JLabel lblCod = new JLabel("Código:");
        lblCod.setFont(new Font("Arial", Font.BOLD, 12));
        lblCod.setBounds(20, 60, 60, 20);
        add(lblCod);

        txtCodProd = new JTextField();
        txtCodProd.setBounds(80, 60, 100, 20);
        add(txtCodProd);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 12));
        lblNome.setBounds(20, 90, 60, 20);
        add(lblNome);

        txtNomeProd = new JTextField();
        txtNomeProd.setBounds(80, 90, 300, 20);
        add(txtNomeProd);

        JLabel lblDesc = new JLabel("Descrição:");
        lblDesc.setFont(new Font("Arial", Font.BOLD, 12));
        lblDesc.setBounds(20, 120, 80, 20);
        add(lblDesc);

        JTextArea txtDescricao = new JTextArea();
        txtDescricao.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txtDescricao.setBounds(20, 140, 360, 50);
        add(txtDescricao);

        // Tabela de Produtos
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 210, 500, 150);
        add(scrollPane);

        table = new JTable();
        // Comentário exigido: Colunas específicas para o produto finalizado (SKU)
        String[] colunas = {"Cód. Produto", "Nome Comercial", "Descrição"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        JButton btnSalvar = new JButton("Salvar Produto");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(400, 160, 120, 30);
        add(btnSalvar);
        
       
        btnSalvar.addActionListener(e -> {
            
            modelo.addRow(new Object[]{txtCodProd.getText(), txtNomeProd.getText(), txtDescricao.getText()});
            txtCodProd.setText("");
            txtNomeProd.setText("");
            txtDescricao.setText("");
        });
    }
}