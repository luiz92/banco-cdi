package view;

import aplicacao.Aplicacao;
import dominio.Conta;
import dominio.ContaEnum;
import dominio.Usuario;
import java.util.Scanner;

public interface ContaView {
    Scanner scanner = new Scanner(System.in);

    void acessarConta();
    void tipoDeConta(Scanner scanner, Aplicacao aplicacao, Usuario usuario);
    void createConta(ContaEnum contaEnum, Usuario usuario, Conta conta);
    void saque(ContaEnum contaEnum, Usuario usuario, Conta conta);
    void deposito(ContaEnum contaEnum, Usuario usuario, Conta conta);
    void saldo(ContaEnum contaEnum, Usuario usuario, Conta conta);

}
