package view;

import controller.ProdutoController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import model.Produto;

// Painel responsável pela exibição de indicadores gerais do sistema,
// como total de materiais cadastrados e alertas de estoque.
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
    /**
     * Este método deve ser chamado toda vez que a tela do Dashboard for exibida
     */
        /**
     * Este método deve ser chamado toda vez que a tela do Dashboard for exibida
     */
    public void atualizarDados() {
        if (controller != null) {
            int totalMateriaisInsumos = 0;
            int alertas = 0;

            // Percorre as árvores BOM para isolar apenas os materiais
            for (Produto p : controller.getListaProdutos()) {
                if (p.getMateriais() != null) {
                    // Contabiliza o tamanho da lista de insumos de cada pai
                    totalMateriaisInsumos += p.getMateriais().size();
                    
                    // Verifica o status individual de cada material
                    for (Produto mat : p.getMateriais()) {
                        if (mat.getUnidade() < ProdutoController.ESTOQUE_MINIMO) {
                            alertas++;
                        }
                    }
                }
            }

            // Exibe estritamente o total de materiais/insumos vinculados
            lblTotalMateriais.setText("Total de Materiais: " + totalMateriaisInsumos);
            
            // Gerencia os alertas visuais do painel
            lblAlertasEstoque.setText("Alertas de Estoque: " + alertas);
            if (alertas > 0) {
                lblAlertasEstoque.setForeground(Color.RED);
            } else {
                lblAlertasEstoque.setForeground(Color.BLACK);
            }
        }
    }

}