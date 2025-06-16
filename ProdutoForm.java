
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ProdutoForm extends JFrame {
    private ProdutoDAO dao = new ProdutoDAO();
    private JTable table;
    private DefaultTableModel model;

    public ProdutoForm() {
        setTitle("Controle de Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 50, 25);
        JTextField txtNome = new JTextField();
        txtNome.setBounds(60, 10, 150, 25);

        JLabel lblQtd = new JLabel("Qtd:");
        lblQtd.setBounds(220, 10, 40, 25);
        JTextField txtQtd = new JTextField();
        txtQtd.setBounds(260, 10, 50, 25);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(320, 10, 50, 25);
        JTextField txtPreco = new JTextField();
        txtPreco.setBounds(370, 10, 60, 25);

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.setBounds(440, 10, 100, 25);

        model = new DefaultTableModel(new String[]{"ID", "Nome", "Qtd", "Preço"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 50, 560, 300);

        add(lblNome); add(txtNome);
        add(lblQtd); add(txtQtd);
        add(lblPreco); add(txtPreco);
        add(btnAdd); add(scroll);

        atualizarTabela();

        btnAdd.addActionListener(e -> {
            String nome = txtNome.getText();
            int qtd = Integer.parseInt(txtQtd.getText());
            double preco = Double.parseDouble(txtPreco.getText());
            dao.adicionarProduto(new Produto(nome, qtd, preco));
            atualizarTabela();
        });

        setVisible(true);
    }

    private void atualizarTabela() {
        model.setRowCount(0);
        List<Produto> produtos = dao.listarProdutos();
        for (Produto p : produtos) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidade(), p.getPreco()});
        }
    }
}
