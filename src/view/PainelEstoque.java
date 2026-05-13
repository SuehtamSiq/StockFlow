package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ProdutoController;
import model.Produto;
import java.awt.Color;

public class PainelEstoque extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ProdutoController controller;
	private JLabel lblTotalMateriais;
	private JLabel lblAlertasEstoque;

	/**
	 * Create the panel.
	 */
	public PainelEstoque(ProdutoController controller) {
		setBackground(new Color(100, 149, 237));
		this.controller = controller;
		
		setLayout(null);
		
		JLabel lblControleEstoque = new JLabel("Controle de Estoque");
		lblControleEstoque.setFont(new Font("Arial", Font.BOLD, 18));
		lblControleEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblControleEstoque.setBounds(236, 21, 238, 51);
		add(lblControleEstoque);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 116, 363, 253);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] colunas = {"Material", "Quantidade", "Mínimo", "Status"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		table.setModel(modelo);
	}
	
public void carregarEstoque(List<Produto> lista) {
	DefaultTableModel modelo = (DefaultTableModel) table.getModel ();
	modelo.setNumRows(0);
	
	for (Produto p : lista) {
		int estoqueMinimo = 30;
		String status = "Ok";
		if (p.getUnidade() < estoqueMinimo) {
			status = "Baixo";
		}
		
		modelo.addRow(new Object[] {
				p.getNome(),
				p.getUnidade(),
				estoqueMinimo,
				status
		});
		}
}
}