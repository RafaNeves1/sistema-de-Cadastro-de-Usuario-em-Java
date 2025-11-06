import java.util.*;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioDAO dao = new UsuarioDAO();

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== SISTEMA DE CADASTRO DE USUÁRIOS =====");
            System.out.println("1. Listar usuários");
            System.out.println("2. Adicionar usuário");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Deletar usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> listarUsuarios();
                case 2 -> adicionarUsuario();
                case 3 -> atualizarUsuario();
                case 4 -> deletarUsuario();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = dao.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            usuarios.forEach(u -> System.out.println(
                "ID: " + u.getId() + " | Nome: " + u.getNome() +
                " | Email: " + u.getEmail() + " | Idade: " + u.getIdade()
            ));
        }
    }

    private static void adicionarUsuario() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Idade: ");
        int idade = sc.nextInt();

        Usuario usuario = new Usuario(id, nome, email, idade);
        dao.adicionar(usuario);
        System.out.println("Usuário adicionado com sucesso!");
    }

    private static void atualizarUsuario() {
        System.out.print("Informe o ID do usuário: ");
        int id = sc.nextInt();
        sc.nextLine();

        List<Usuario> usuarios = dao.listar();
        Usuario existente = usuarios.stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElse(null);

        if (existente == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        System.out.print("Nova idade: ");
        int idade = sc.nextInt();

        existente.setNome(nome);
        existente.setEmail(email);
        existente.setIdade(idade);

        dao.atualizar(existente);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void deletarUsuario() {
        System.out.print("Informe o ID do usuário a excluir: ");
        int id = sc.nextInt();
        dao.deletar(id);
        System.out.println("Usuário removido com sucesso!");
    }
}
