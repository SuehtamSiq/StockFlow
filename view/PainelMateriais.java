package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	private JTable table;
	private ProdutoController controller;

	/**
	 * Create the panel.
	 */
	public PainelMateriais(ProdutoController controllerRecebido) {
		this.controller = controllerRecebido;
		
		
		setBackground(new Color(100, 149, 237));
		setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 90, 110, 18);
		add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(10, 140, 110, 18);
		add(txtNome);
		
		txtUnidade = new JTextField();
		txtUnidade.setColumns(10);
		txtUnidade.setBounds(10, 190, 110, 18);
		add(txtUnidade);
		
		txtCusto = new JTextField();
		txtCusto.setColumns(10);
		txtCusto.setBounds(10, 240, 110, 18);
		add(txtCusto);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 68, 44, 12);
		add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(10, 118, 70, 12);
		add(lblNome);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Arial", Font.BOLD, 12));
		lblUnidade.setBounds(10, 168, 70, 12);
		add(lblUnidade);
		
		JLabel lblCusto = new JLabel("Custo");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 12));
		lblCusto.setBounds(10, 218, 44, 12);
		add(lblCusto);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBackground(new Color(192, 192, 192));
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
		btnSalvar.setBounds(20, 280, 84, 20);
		add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Cadastro de Materiais");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(192, 10, 215, 38);
		add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(148, 80, 365, 232);
		String[] colunas = {"Código", "Nome", "Unidade", "Custo"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		table.setModel(modelo);
		
		JScrollPane scrollPane = new JScrollPane (table);
		scrollPane.setBounds(148, 80, 365, 232);
		add(scrollPane);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String codigo = txtCodigo.getText();
					String nome = txtNome.getText();
					
				int unidade = Integer.parseInt(txtUnidade.getText());
				double custo = Double.parseDouble(txtCusto.getText().replace(",", "."));
				
				controller.cadastrarProduto(codigo, nome, unidade, custo);
				atualizarTabela();
				//depois entender como faz para listar o que ta guardado no contoller.getListaProdutos
				//depois disso adicionar essa lista no addRow
				System.out.println("Produtos cadastrados: " + controller.getListaProdutos().size());
				} catch (Exception erro) {
					System.out.println("Erro ao salvar: " + erro.getMessage());
				}
			}
		});
		    
	}
	public void atualizarTabela() {
    	DefaultTableModel modelo = (DefaultTableModel) table.getModel();
    	modelo.setRowCount(0);
    	for (Produto p:
    	controller.getListaProdutos()) {
    		modelo.addRow(new Object[] {
    				p.getCodigo(),
    				p.getNome(),
    				p.getUnidade(),
    				p.getCusto()
    		});
    	}
    }
}
