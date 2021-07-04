package aplicacao;

import dominio.Usuario;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final WeldContainer container = new Weld().initialize();
        final Aplicacao aplicacao = container.select(Aplicacao.class).get();
        init(aplicacao);
    }

    public static void teclaContinuar(){
        Scanner continuar = new Scanner(System.in);
        for(int i = 0; i < 1; i++) {
            System.out.print("* Pressione Enter para continuar *");
            continuar.nextLine();
        }
    }

    private static void init(Aplicacao aplicacao){
        int opcao = 0;
        System.out.println("Bem-vindo ao seu banco digital!!!");
        Scanner scanner = new Scanner (System.in);

        do{
            teclaContinuar();
            System.out.println("\nO que gostaria de fazer?\n1 - Cadastrar Usuário\n2 - Criar Conta\n3 - Acessar Conta\n0 - Sair");
            opcao = scanner.nextInt();
            definirOpcao(scanner, opcao, aplicacao);
        } while (opcao > 0);
    }

    private static void definirOpcao(Scanner scanner, int opcao, Aplicacao aplicacao){
        switch (opcao){
            case 1:
                aplicacao.getUsuarioView().create(scanner);
                break;
            case 2:
                System.out.println("Informe seu CPF");
                String cpf = scanner.next();
                Usuario usuario = aplicacao.getUsuarioFileDao().getUsuario(cpf);
                if (usuario != null) {
                    aplicacao.getContaView().tipoDeConta(scanner, aplicacao, usuario);
                } else {
                    System.err.println("CPF não cadastrado!");
                }
                break;
            case 3:
                aplicacao.getContaView().acessarConta();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.err.println("Digite um valor válido");
        }
    }
}
