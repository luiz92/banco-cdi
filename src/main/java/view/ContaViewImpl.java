package view;

import aplicacao.Aplicacao;
import dao.ContaFileDao;
import dao.UsuarioFileDao;
import dominio.Conta;
import dominio.ContaEnum;
import dominio.Usuario;
import servico.ContaFactory;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Scanner;

public class ContaViewImpl implements ContaView {
    @Inject
    private ContaFactory contaFactory;

    @Inject
    private UsuarioFileDao usuarioFileDao;

    @Inject
    private ContaFileDao contaFileDao;

    @Override
    public void tipoDeConta(Scanner scanner, Aplicacao aplicacao, Usuario usuario) {
        if (usuario != null) {
            System.out.println("Tipo da conta: \n\t1 - Conta Simples\n\t2 - Conta Poupança\n\t3 - Conta Especial");
            int opcaoConta = scanner.nextInt();
            ContaEnum contaEnum = null;
            switch (opcaoConta) {
                case 1:
                    contaEnum = ContaEnum.SIMPLES;
                    break;
                case 2:
                    contaEnum = ContaEnum.POUPANCA;
                    break;
                case 3:
                    contaEnum = ContaEnum.ESPECIAL;
                    break;
            }
            aplicacao.getContaView(). createConta(contaEnum, usuario, new Conta());
        }
    }

    @Override
    public void acessarConta() {
        System.out.println("É necessário estar autenticado\n\tInsira o CPF da conta");
        String cpf = scanner.next();
        if (usuarioFileDao.validarUsuario(cpf)) {
            System.out.println("Insira a senha");
            String senha = scanner.next();
            if (usuarioFileDao.validarSenha(senha)) {
                menuContas(cpf);
                return;
            }
        }
        System.err.println("Erro de autenticação!");
    }

    @Override
    public void createConta(ContaEnum contaEnum, Usuario usuario, Conta conta) {
        contaFactory.createConta(contaEnum).criarConta(usuario, conta);
    }

    @Override
    public void saque(ContaEnum contaEnum, Usuario usuario, Conta conta) {
        System.out.println("Informe o valor do saque");
        BigDecimal valor = scanner.nextBigDecimal();
        contaFactory.saque(contaEnum).saqueConta(usuario, conta, valor);
    }

    @Override
    public void deposito(ContaEnum contaEnum, Usuario usuario, Conta conta) {
        System.out.println("Informe o valor do deposito");
        BigDecimal valor = scanner.nextBigDecimal();
        contaFactory.deposito(contaEnum).depositoConta(usuario, conta, valor);
    }

    @Override
    public void saldo(ContaEnum contaEnum, Usuario usuario, Conta conta) {
        contaFactory.saldo(contaEnum).saldoConta(usuario, conta);
    }

    public void menuContas(String cpf) {
        Conta conta = null;
        String tipoConta;
        Usuario usuario = usuarioFileDao.getUsuario(cpf);
        if (usuario != null) {
            System.out.println("Tipo da conta: \n\t1 - Conta Simples\n\t2 - Conta Poupança\n\t3 - Conta Especial\n\t0 - Sair");
            int opcaoConta = scanner.nextInt();
            ContaEnum contaEnum = null;
            switch (opcaoConta) {
                case 1:
                    contaEnum = ContaEnum.SIMPLES;
                    tipoConta = "ContaSimples";
                    conta = contaFileDao.getConta(cpf, tipoConta);
                    if(conta != null)subMenu(contaEnum, usuario, conta);
                    break;
                case 2:
                    contaEnum = ContaEnum.POUPANCA;
                    tipoConta = "ContaPoupanca";
                    conta = contaFileDao.getConta(cpf, tipoConta);
                    if(conta != null)subMenu(contaEnum, usuario, conta);
                    break;
                case 3:
                    contaEnum = ContaEnum.ESPECIAL;
                    tipoConta = "ContaEspecial";
                    conta = contaFileDao.getConta(cpf, tipoConta);
                    if(conta != null)subMenu(contaEnum, usuario, conta);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Valor inválido!");
            }
        }
    }

    public void subMenu(ContaEnum contaEnum, Usuario usuario, Conta conta) {
        System.out.println("Escolha uma opção:\n\t1 - Saque\n\t2 - Deposito\n\t3 - Saldo\n\t0 - Sair");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                saque(contaEnum, usuario, conta);
                break;
            case 2:
                deposito(contaEnum, usuario, conta);
                break;
            case 3:
                saldo(contaEnum, usuario, conta);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.err.println("Digite um valor válido");
        }
    }

}
