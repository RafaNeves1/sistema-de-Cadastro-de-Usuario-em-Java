public class Usuario {
    private int id;
    private String nome;
    private String email;
    private int idade;

    public Usuario(int id, String nome, String email, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getIdade() { return idade; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setIdade(int idade) { this.idade = idade; }

    @Override
    public String toString() {
        return id + "," + nome + "," + email + "," + idade;
    }

    public static Usuario fromCSV(String linha) {
        String[] dados = linha.split(",");
        return new Usuario(
            Integer.parseInt(dados[0]),
            dados[1],
            dados[2],
            Integer.parseInt(dados[3])
        );
    }
}
