package servico;

import dao.ContaFileDao;
import dominio.Conta;
import dominio.ContaEnum;
import dominio.Usuario;
import lombok.Getter;
import lombok.ToString;
import javax.inject.Inject;
import java.math.BigDecimal;

@Getter
@ToString
@TipoConta(value = ContaEnum.ESPECIAL)
public class ContaEspecialServiceImpl extends Conta implements ContaService{

    @Inject
    private ContaFileDao contaFileDao;

    @Override
    public void criarConta(Usuario usuario, Conta conta){
        conta.setSaldo(BigDecimal.valueOf(400));
        conta.setTipoConta("ContaEspecial");
        conta.setUsuario(usuario.getCpf());

        contaFileDao.criarConta(usuario, conta);
    }

    @Override
    public void saqueConta(Usuario usuario, Conta conta, BigDecimal valor) {
        String tipoConta = "ContaEspecial";
        conta = getContaFileDao().getConta(usuario.getCpf(), conta.getTipoConta());

        var newValor = conta.getSaldo().subtract(valor);
        if(newValor.compareTo(BigDecimal.valueOf(-200)) < 0){
            System.out.println("Não há saldo suficiente para esse saque");
            System.out.println("Seu limite é de até -R$200,00");
            System.out.println("Faça um deposito!!!");
        } else {
            conta.setSaldo(newValor);
            getContaFileDao().alterarConta(tipoConta, conta);
        }

        System.out.println("====================");
        System.out.printf("Saldo atual: R$%.2f %n", conta.getSaldo());
        System.out.println("====================");
    }

    @Override
    public Conta depositoConta(Usuario usuario, Conta conta, BigDecimal valor) {
        String tipoConta = "ContaEspecial";
        conta = getContaFileDao().getConta(usuario.getCpf(), conta.getTipoConta());

        var newValor = conta.getSaldo().add(valor);
        conta.setSaldo(newValor);
        getContaFileDao().alterarConta(tipoConta, conta);

        System.out.println("====================");
        System.out.printf("Saldo atualizado: R$%.2f %n", conta.getSaldo());
        System.out.println("====================");
        return conta;
    }

    @Override
    public Conta saldoConta(Usuario usuario, Conta conta) {
        System.out.println("====================");
        System.out.printf("Seu extrato da %s é:%n", conta.getTipoConta());
        System.out.printf("Saldo atual: R$%.2f %n", conta.getSaldo());
        System.out.println("====================");
        return conta;
    }
}
