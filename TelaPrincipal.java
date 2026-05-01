package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ProdutoController;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ProdutoController controllerGeral = new ProdutoController();
	private JPanel PainelConteudo;
	private PainelDashboard painelDashboard;
	private PainelMateriais painelMateriais;
	private PainelProduto painelProduto;
	private PainelEstoque painelEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 600));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBackground(SystemColor.GRAY);
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		PainelConteudo = new JPanel();
		PainelConteudo.setBackground(new Color(100, 149, 237));
		contentPane.add(PainelConteudo, BorderLayout.CENTER);
		PainelConteudo.setLayout(new CardLayout());
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(e -> {
			PainelConteudo.removeAll();
			PainelDashboard telaDash = new PainelDashboard(controllerGeral);
			PainelConteudo.add(telaDash);
			PainelConteudo.revalidate();
			PainelConteudo.repaint();
		});
		
		btnDashboard.setForeground(new Color(0, 0, 128));
		btnDashboard.setBackground(new Color(192, 192, 192));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnDashboard);
		
		JButton btnMateriais = new JButton("Materiais");
		btnMateriais.addActionListener(e -> {
			public void actionPerformed(ActionEvent e) {
				PainelConteudo.removeAll();
				PainelMateriais telaMateriais = new PainelMateriais(controllerGeral);
						
				PainelConteudo.add(telaMateriais);
				((PainelMateriais)painelMateriais).atualizarTabela();
				PainelConteudo.revalidate();
				PainelConteudo.repaint();
			}
		});
		btnMateriais.setBackground(new Color(192, 192, 192));
		btnMateriais.setForeground(new Color(0, 0, 128));
		btnMateriais.setHorizontalAlignment(SwingConstants.LEFT);
		btnMateriais.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnMateriais);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(e -> {
			PainelConteudo.removeAll();
			PainelProduto telaProdutos = new PainelProduto(controllerGeral);
			PainelConteudo.add(telaProdutos);
			PainelConteudo.revalidate();
			PainelConteudo.repaint();
			
		});
		btnProduto.setForeground(new Color(0, 0, 128));
		btnProduto.setBackground(new Color(192, 192, 192));
		btnProduto.setFont(new Font("Arial", Font.BOLD, 12));
		btnProduto.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnProduto);
		
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBackground(new Color(192, 192, 192));
		btnEstoque.setForeground(new Color(0, 0, 128));
		btnEstoque.setFont(new Font("Arial", Font.BOLD, 12));
		btnEstoque.setHorizontalAlignment(SwingConstants.LEFT);
		btnEstoque.addActionListener(e -> {
			PainelConteudo.removeAll();
			PainelEstoque telaEstoque = new PainelEstoque(controllerGeral);
		PainelConteudo.add(telaEstoque);
		   PainelConteudo.revalidate();
		   PainelConteudo.repaint();
		});
		panel.add(btnEstoque);
		
	}

}
