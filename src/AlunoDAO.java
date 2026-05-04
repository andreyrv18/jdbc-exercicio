import java.sql.*;
import java.util.*;

public class AlunoDAO {

    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno(nome, matricula, nota1, nota2, nota3) VALUES (?,?,?,?,?)";

        try (Connection connection = ConexaoBD.getConexao(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getMatricula());
            ps.setDouble(3, aluno.getNota1());
            ps.setDouble(4, aluno.getNota2());
            ps.setDouble(5, aluno.getNota3());
            ps.executeUpdate();
        }

    }

    public List<Aluno> listarTodosAlunos() throws SQLException {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno ORDER BY nome";

        try (Connection connection = ConexaoBD.getConexao();
                ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                lista.add(new Aluno(
                        0,
                        resultSet.getString("nome"),
                        resultSet.getInt("matricula"),
                        resultSet.getDouble("nota1"),
                        resultSet.getDouble("nota2"),
                        resultSet.getDouble("nota3")));
            }
        }

        return lista;
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nota1=?, nota2=?, nota3=? WHERE matricula=?";

        try (Connection connection = ConexaoBD.getConexao(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, aluno.getNota1());
            ps.setDouble(2, aluno.getNota2());
            ps.setDouble(3, aluno.getNota3());
            ps.setInt(4, aluno.getMatricula());
            ps.executeUpdate();
        }
    }

    public void excluir(int matricula) throws SQLException {
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (Connection connection = ConexaoBD.getConexao(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, matricula);

        }

    }

    public Aluno bucarPorMatricula(int mat) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE matricula=?";

        try (Connection connection = ConexaoBD.getConexao(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, mat);

            try (ResultSet rs = ps.executeQuery()) {

                return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("matricula"),
                        rs.getDouble("nota1"), rs.getDouble("nota2"), rs.getDouble("nota3"));

            }
        }
    }
}
