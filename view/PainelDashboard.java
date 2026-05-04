package view;

import controller.ProdutoController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import model.Produto;

public class PainelDashboard extends JPanel {

    private static final long serialVersionUID = 1L;
    private ProdutoController controller;
    // Transformamos os labels em atributos da classe para o método acessar
    private JLabel lblTotalMateriais;
    private JLabel lblAlertasEstoque;

    public PainelDashboard(ProdutoController controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(new Color(100, 149, 237));

        // Painel de Total
        JPanel panelTotal = new JPanel();
        panelTotal.setBackground(Color.WHITE);
        panelTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(0, 0, 128), new Color(30, 144, 255), new Color(0, 0, 128)));
        panelTotal.setBounds(96, 151, 200, 119);
        panelTotal.setLayout(null);
        add(panelTotal);

        lblTotalMateriais = new JLabel("Total de Materiais: 0");
        lblTotalMateriais.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalMateriais.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalMateriais.setBounds(10, 50, 180, 20);
        panelTotal.add(lblTotalMateriais);

        // Painel de Alertas
        JPanel panelAlertas = new JPanel();
        panelAlertas.setBackground(Color.WHITE);
        panelAlertas.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(173, 216, 230), Color.BLUE, new Color(30, 144, 255)));
        panelAlertas.setBounds(410, 151, 200, 119);
        panelAlertas.setLayout(null);
        add(panelAlertas);

        lblAlertasEstoque = new JLabel("Alertas de Estoque: 0");
        lblAlertasEstoque.setFont(new Font("Arial", Font.BOLD, 13));
        lblAlertasEstoque.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlertasEstoque.setBounds(10, 50, 180, 20);
        panelAlertas.add(lblAlertasEstoque);

        JLabel lblTitulo = new JLabel("Dashboard de Gestão");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(261, 30, 250, 30);
        add(lblTitulo);

        // Chamada inicial para não começar zerado
        atualizarDados();
    }

    /**
     * Este método deve ser chamado toda vez que a tela do Dashboard for exibida
     */
    public void atualizarDados() {
        if (controller != null) {
            int total = controller.getListaProdutos().size();
            lblTotalMateriais.setText("Total de Produtos: " + total);//

            int alertas = 0;
            for (Produto p : controller.getListaProdutos()) {
                if (p.getUnidade() < 30) {
                    alertas++;
                }
            }
            lblAlertasEstoque.setText("Alertas de Estoque: " + alertas);
            
            // Dica: Se quiser que o alerta mude de cor quando houver itens baixos
            if (alertas > 0) {
                lblAlertasEstoque.setForeground(Color.RED);
            } else {
                lblAlertasEstoque.setForeground(Color.BLACK);
            }
        }
    }
}