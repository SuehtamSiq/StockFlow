package view;

import controller.ProdutoController;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Produto;

//Tela responsável pela visualização do controle de estoque dos materiais.
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
		scrollPane.setBounds(225, 145, 263, 224);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] colunas = {"Material", "Quantidade", "Mínimo", "Status"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		table.setModel(modelo);
	}
	
public void carregarEstoque(ArrayList<Produto>lista) {
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
// Atualiza a tabela exibindo os materiais vinculados aos produtos.
public void atualizar() {
    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
    modelo.setRowCount(0);
    
    // Percorremos os produtos finais
    for (Produto p : controller.getListaProdutos()) {
        // Para cada produto, percorremos seus materiais (a BOM)
        for (Produto mat : p.getMateriais()) {
            // Utiliza a constante centralizada do Controller
            int estoqueMinimo = ProdutoController.ESTOQUE_MINIMO;
            String status = (mat.getUnidade() < estoqueMinimo) ? "Baixo" : "Ok";
            
            modelo.addRow(new Object[] {
                mat.getNome() + " (Ref: " + p.getCodigo() + ")", 
                mat.getUnidade(),
                estoqueMinimo,
                status
            });
        }
    }
}

}

