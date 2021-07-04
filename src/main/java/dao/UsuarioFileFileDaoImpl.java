package dao;

import dominio.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioFileFileDaoImpl implements UsuarioFileDao {
    private List<Usuario> registroLinhas = new ArrayList<>();

    @Override
    public void preparaArquivo() {
        try (Stream<String> streamLinhas = Files.lines(Path.of("src\\main\\Usuarios.txt"))) {
            registroLinhas = streamLinhas
                    .filter(Predicate.not(String::isEmpty))
                    .map(Usuario::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravaArquivo(){
        var leArquivo = new File("src\\main\\Usuarios.txt");
        Usuario conteudo;
        try{
            var arquivo = new FileWriter (leArquivo, false);
            arquivo.flush();
            for (Usuario registroLinha : registroLinhas) {
                conteudo = registroLinha;
                arquivo.write(String.valueOf(conteudo+"\n"));
            }
            arquivo.close();
        }catch (IOException e)  {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario criarUsuario(Usuario usuario){
        preparaArquivo();
        if(!validarUsuario(usuario.getCpf())) {
            registroLinhas.add(usuario);
            gravaArquivo();
            System.out.println("Usuário criado com sucesso!");
            return usuario;
        }
        System.err.println("Usuário já existente!");
        return usuario;
    }

    @Override
    public Usuario getUsuario(String cpf) {
        preparaArquivo();
        for (Usuario registroLinha : registroLinhas) {
            if (registroLinha.getCpf().equals(cpf)) {
                return registroLinha;
            }
        }
        return null;
    }

    @Override
    public boolean validarUsuario(String cpf){
        preparaArquivo();
        for (Usuario registroLinha : registroLinhas) {
            if (registroLinha.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validarSenha(String senha) {
        preparaArquivo();
        for (Usuario registroLinha : registroLinhas) {
            if (registroLinha.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

}
