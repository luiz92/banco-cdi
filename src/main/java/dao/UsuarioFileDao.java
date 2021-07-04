package dao;

import dominio.Usuario;

public interface UsuarioFileDao {

    void preparaArquivo();
    void gravaArquivo();
    Usuario criarUsuario(Usuario usuario);
    Usuario getUsuario(String cpf);
    boolean validarUsuario(String cpf);
    boolean validarSenha(String senha);

}
