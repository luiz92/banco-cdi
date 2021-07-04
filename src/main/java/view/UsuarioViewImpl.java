package view;

import java.util.Scanner;
import dao.UsuarioFileDao;
import dominio.Usuario;
import servico.UsuarioService;

import javax.inject.Inject;

public class UsuarioViewImpl implements UsuarioView{
    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioFileDao usuarioFileDao;

    @Inject Usuario usuario;

    @Override
    public void create(Scanner scanner){
        System.out.println("Informe o nome do usuário");
        usuario.setNome(scanner.next());
        System.out.println("Digite a idade");
        usuario.setIdade(scanner.nextInt());
        System.out.println("Digite o CPF");
        usuario.setCpf(scanner.next());
        System.out.println("Digite a senha");
        usuario.setSenha(scanner.next());

         usuarioService.create(usuario);
    }

}
