import java.io.*;
import java.util.*;

public class UsuarioDAO {
    private final String arquivo = "usuarios.csv";

    public UsuarioDAO() {
        try {
            File file = new File(arquivo);
            if (!file.exists()) {
                FileWriter fw = new FileWriter(arquivo);
                fw.write("id,nome,email,idade\n");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                usuarios.add(Usuario.fromCSV(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void salvar(List<Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write("id,nome,email,idade\n");
            for (Usuario u : usuarios) {
                fw.write(u.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Usuario usuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(usuario);
        salvar(usuarios);
    }

    public void atualizar(Usuario usuarioAtualizado) {
        List<Usuario> usuarios = listar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuarioAtualizado.getId()) {
                usuarios.set(i, usuarioAtualizado);
                break;
            }
        }
        salvar(usuarios);
    }

    public void deletar(int id) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(u -> u.getId() == id);
        salvar(usuarios);
    }
}
