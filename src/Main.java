import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            ConexaoBD.getConexao();
            System.out.println("Conectado!");
        } catch (Exception exception) {
            System.out.println("Erro de conexão!");
        }

        System.out.println("\n=== Inserir Alunos ===");
        Aluno[] alunosInserir = {
                new Aluno(1, "Andrey Viniewski", 777, 9.78, 8.77, 7.77),
                new Aluno(2, "Nicolas", 120, 8.66, 8.12, 7.61),
                new Aluno(3, "Michael", 255, 8.99, 8.26, 3.44),
                new Aluno(4, "Adrian", 654, 9.10, 5.99, 5.01),
                new Aluno(5, "Evelin", 987, 5.01, 8.23, 9.89),
                new Aluno(6, "Adrina", 123, 6.57, 7.50, 10.00),
                new Aluno(7, "Jean", 852, 7.74, 10.00, 7.80),
        };

        for (Aluno aluno : alunosInserir) {
            alunoDAO.inserir(aluno);
            System.out.printf("Aluno: %s inserido no banco %n", aluno.getNome());

        }



        System.out.println("\n=== Listar todos alunos: ===");

        List<Aluno> alunosListar = alunoDAO.listarTodosAlunos();

        for (Aluno aluno : alunosListar) {
            System.out.printf("Aluno: %s - Matricula %d%n ", aluno.getNome(), aluno.getMatricula());
        }

        
        
        System.out.println("\n=== Atualizar Aluno ===");

        Aluno alunoAtualizar = new Aluno(1, "Andrey Viniewski", 777, 6.78, 6.77, 6.89);
        alunoDAO.atualizar(alunoAtualizar);
        System.out.printf("Aluno %s atualizado: ", alunoAtualizar.getNome());

        
        
        System.out.println("\n=== Excluir Aluno ===");
        int matriculaExcluir = 123;
        alunoDAO.excluir(matriculaExcluir);
        System.out.printf("Excluindo aluno: %d", matriculaExcluir);



        System.out.println("\n=== Buscar Aluno por Matricula ===");

        Aluno alunoEncontrado = alunoDAO.bucarPorMatricula(777);
        System.out.println(alunoEncontrado.getNome());
    }
}
