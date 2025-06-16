
import java.sql.*;
import java.util.*;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:estoque.db");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, quantidade INTEGER, preco REAL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarProduto(Produto produto) {
        try {
            String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produtos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarProduto(Produto produto) {
        try {
            String sql = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProduto(int id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
