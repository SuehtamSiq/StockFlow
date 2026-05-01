package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.ProdutoController;
import model.Produto;

public class PainelDashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private ProdutoController controller = new ProdutoController();
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public PainelDashboard(ProdutoController controller) {
		this.controller = controller;
		setLayout(null);
		
		JPanel PainelDashboard = new JPanel();
		PainelDashboard.setBackground(new Color(100, 149, 237));
		PainelDashboard.setBounds(0, 0, 741, 447);
		add(PainelDashboard);
		PainelDashboard.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 128), new Color(30, 144, 255), new Color(0, 0, 128)));
		panel.setBounds(96, 151, 187, 119);
		PainelDashboard.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Total de Materiais");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(32, 10, 121, 12);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Dashboard de Gestão");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(261, 11, 206, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		PainelDashboard.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(173, 216, 230), new Color(0, 0, 255), new Color(30, 144, 255)));
		panel_1.setLayout(null);
		panel_1.setBounds(410, 151, 187, 119);
		PainelDashboard.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Alertas de Estoque");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(36, 10, 107, 12);
		panel_1.add(lblNewLabel_1_1);
		
		         if (controller != null) {
		        	 int total = controller.getListaProdutos().size();
		lblNewLabel_1.setText("Total de Materiais:" + total);
		
		         int alertas = 0;
		         for (Produto p : controller.getListaProdutos()) {
		        	 if (p.getUnidade() < 30) { alertas++; }
		         }
	    lblNewLabel_1_1.setText("Alertas de Estoque: " + alertas);
			
			
		}

	}
}
