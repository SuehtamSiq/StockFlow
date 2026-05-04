package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ProdutoController;
import model.Produto;

public class PainelMateriais extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtUnidade;
    private JTextField txtCusto;
    private JComboBox<String> comboProdutosPai; // Combo para selecionar o Produto Final (BOM)
    private JTable table;
    private ProdutoController controller;

    public PainelMateriais(ProdutoController controllerRecebido) {
        this.controller = controllerRecebido;
        
        setBackground(new Color(100, 149, 237));
        setLayout(null);
        
        // --- SEÇÃO DE VÍNCULO (BOM) ---
        JLabel lblProdutoPai = new JLabel("Vincular ao Produto (Pai):");
        lblProdutoPai.setFont(new Font("Arial", Font.BOLD, 12));
        lblProdutoPai.setBounds(10, 50, 180, 12);
        add(lblProdutoPai);
        
        comboProdutosPai = new JComboBox<>();
        comboProdutosPai.setBounds(10, 65, 128, 22);
        add(comboProdutosPai);

        comboProdutosPai.addActionListener(e -> {
        atualizarTabela(); // Atualiza a tabela sempre que mudar a seleção
        });
        
        // --- CAMPOS DE CADASTRO DO MATERIAL ---
        JLabel lblCodigo = new JLabel("Código Material");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        lblCodigo.setBounds(10, 100, 110, 12);
        add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(10, 115, 110, 20);
        add(txtCodigo);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Arial", Font.BOLD, 12));
        lblNome.setBounds(10, 145, 70, 12);
        add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(10, 160, 110, 20);
        add(txtNome);
        
        JLabel lblUnidade = new JLabel("Quantidade");
        lblUnidade.setFont(new Font("Arial", Font.BOLD, 12));
        lblUnidade.setBounds(10, 190, 70, 12);
        add(lblUnidade);
        
        txtUnidade = new JTextField();
        txtUnidade.setBounds(10, 205, 110, 20);
        add(txtUnidade);
        
        JLabel lblCusto = new JLabel("Custo Unitário");
        lblCusto.setFont(new Font("Arial", Font.BOLD, 12));
        lblCusto.setBounds(10, 235, 110, 12);
        add(lblCusto);
        
        txtCusto = new JTextField();
        txtCusto.setBounds(10, 250, 110, 20);
        add(txtCusto);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(20, 290, 90, 25);
        add(btnSalvar);
        
        JLabel lblTitulo = new JLabel("Cadastro de Materiais (BOM)");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(148, 10, 365, 38);
        add(lblTitulo);
        
        // --- TABELA ---
        table = new JTable();
        String[] colunas = {"Código", "Nome", "Qtd", "Custo"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        table.setModel(modelo);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(148, 65, 365, 250);
        add(scrollPane);
        
        // --- LÓGICA DO BOTÃO SALVAR ---
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selecao = (String) comboProdutosPai.getSelectedItem();
                    if (selecao == null) {
                        JOptionPane.showMessageDialog(null, "Cadastre um Produto na aba 'Produtos' primeiro!");
                        return;
                    }
                    
                    // Extrai apenas o código do produto (o que vem antes do " - ")
                    String codPai = selecao.split(" - ")[0];
                    
                    String codigoMat = txtCodigo.getText();
                    String nomeMat = txtNome.getText();
                    int unidade = Integer.parseInt(txtUnidade.getText());
                    double custo = Double.parseDouble(txtCusto.getText().replace(",", "."));
                    
                    // Cria o objeto material
                    Produto material = new Produto(codigoMat, nomeMat, unidade, custo);
                    
                    // Vincula ao pai no controller
                    controller.adicionarMaterialAoProduto(codPai, material);
                    
                    atualizarTabela();
                    limparCampos();
                    
                    JOptionPane.showMessageDialog(null, "Material vinculado com sucesso!");
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro nos dados: Verifique números e campos vazios.");
                }
            }
        });
    }

    public void atualizarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
    modelo.setRowCount(0);

    String selecao = (String) comboProdutosPai.getSelectedItem();
    
    if (selecao != null) {
        // Extrai o código do pai selecionado
        String codPai = selecao.split(" - ")[0];
        
        // Busca o produto pai no controller
        Produto pai = controller.buscarPorCodigo(codPai);
        
        if (pai != null && pai.getMateriais() != null) {
            // Adiciona na tabela apenas os materiais DESTE pai
            for (Produto mat : pai.getMateriais()) {
                modelo.addRow(new Object[] {
                    mat.getCodigo(),
                    mat.getNome(),
                    mat.getUnidade(),
                    mat.getCusto()
                });
            }
        }
    }
}


    public void atualizarCombo() {
        comboProdutosPai.removeAllItems();
        List<Produto> lista = controller.getListaProdutos();
        for (Produto p : lista) {
            comboProdutosPai.addItem(p.getCodigo() + " - " + p.getNome());
        }
    }
    
    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtUnidade.setText("");
        txtCusto.setText("");
    }
}
