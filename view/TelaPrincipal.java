package view;

import controller.ProdutoController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel painelConteudo;
    private CardLayout cardLayout;

    // Controller (por enquanto único)
    private ProdutoController controllerGeral = new ProdutoController();

    // Telas (instanciadas uma única vez)
    private PainelDashboard painelDashboard;
    private PainelMateriais painelMateriais;
    private PainelProduto painelProduto;
    private PainelEstoque painelEstoque;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPrincipal frame = new TelaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaPrincipal() {
        setTitle("StockFlow - Sistema de Gestão");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Sidebar
        JPanel menuLateral = new JPanel();
        menuLateral.setPreferredSize(new Dimension(200, 600));
        menuLateral.setBackground(new Color(45, 45, 45));
        menuLateral.setLayout(new GridLayout(0, 1, 0, 5));
        contentPane.add(menuLateral, BorderLayout.WEST);

        // Painel central com CardLayout
        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);
        painelConteudo.setBackground(Color.WHITE);
        contentPane.add(painelConteudo, BorderLayout.CENTER);

        // Instanciar telas uma vez
        inicializarTelas();

        // Criar botões do menu
        menuLateral.add(criarBotao("Dashboard", "dashboard"));
        menuLateral.add(criarBotao("Materiais", "materiais"));
        menuLateral.add(criarBotao("Produto", "produto"));
        menuLateral.add(criarBotao("Estoque", "estoque"));

        // Tela inicial
        cardLayout.show(painelConteudo, "dashboard");
    }

    /**
     * Inicializa e registra as telas no CardLayout
     */
    private void inicializarTelas() {
        painelDashboard = new PainelDashboard(controllerGeral);
        painelMateriais = new PainelMateriais(controllerGeral);
        painelProduto = new PainelProduto(controllerGeral);
        painelEstoque = new PainelEstoque(controllerGeral);

        painelConteudo.add(painelDashboard, "dashboard");
        painelConteudo.add(painelMateriais, "materiais");
        painelConteudo.add(painelProduto, "produto");
        painelConteudo.add(painelEstoque, "estoque");
    }

    /**
     * Cria botões do menu lateral com comportamento padrão
     */
    private JButton criarBotao(String texto, String nomeTela) {
        JButton botao = new JButton(texto);

        botao.setFocusPainted(false);
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(70, 70, 70));
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setFont(new Font("Arial", Font.BOLD, 14));

        botao.addActionListener(e -> {
        if (nomeTela.equals("estoque")) {
        painelEstoque.atualizar(); // <--- Adicione esta linha
        }
    // ... manter as outras verificações (dashboard, materiais, etc)
        if (nomeTela.equals("materiais")) {
        painelMateriais.atualizarCombo(); // Garante que a lista de produtos pai está fresca
        painelMateriais.atualizarTabela();
        }
        if (nomeTela.equals("dashboard")) {
            painelDashboard.atualizarDados(); // <--- Chama o novo método aqui
        }
            cardLayout.show(painelConteudo, nomeTela);
        });

        return botao;
    }
}