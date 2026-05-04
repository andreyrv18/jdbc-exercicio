import java.sql.*;

public class ConexaoBD {
 private static final String URL = "jdbc:mysql://localhost:3306/escola"  ;
 private static final String USUARIO = "root";
 // == SENHA DO COMPUTADOR FAPI ==  INACREDITAVEL!!!!!!!!!!!!
 private static final String SENHA = "1234" ;

 public static Connection getConexao() throws SQLException {
    return DriverManager.getConnection(URL, USUARIO, SENHA);
 }
}
