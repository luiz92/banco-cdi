package dao;

import dominio.Conta;
import dominio.Usuario;

public interface ContaFileDao {

    void preparaArquivo(String tipoConta);
    void gravaArquivo(Conta conta);
    void criarConta(Usuario usuario, Conta conta);
    Conta getConta(String nome, String tipoConta);
    void alterarConta(String tipoConta, Conta conta);
    boolean validarConta(Conta conta, Usuario usuario);

}
