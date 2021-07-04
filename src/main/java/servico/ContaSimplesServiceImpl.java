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
@TipoConta(value = ContaEnum.SIMPLES)
public class ContaSimplesServiceImpl extends Conta implements ContaService{
    private BigDecimal limite = BigDecimal.valueOf(-200);

    @Inject
    private ContaFileDao contaFileDao;

    @Override
    public void criarConta(Usuario usuario, Conta conta){
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setTipoConta("ContaSimples");
        conta.setUsuario(usuario.getCpf());

        contaFileDao.criarConta(usuario, conta);
    }

    @Override
    public void saqueConta(Usuario usuario, Conta conta, BigDecimal valor) {
        String tipoConta = "ContaSimples";
        conta = getContaFileDao().getConta(usuario.getCpf(), conta.getTipoConta());

        var newValor = conta.getSaldo().subtract(valor);
        if(newValor.compareTo(BigDecimal.valueOf(0)) < 0){
            System.out.println("Valor insuficiente para saque");
        } else {
            conta.setSaldo(newValor);
            getContaFileDao().alterarConta(tipoConta, conta);
        }

        System.out.println("====================");
        System.out.println("Saldo atual: " + conta.getSaldo());
        System.out.println("====================");
    }

    @Override
    public Conta depositoConta(Usuario usuario, Conta conta, BigDecimal valor) {
        String tipoConta = "ContaSimples";
        conta = getContaFileDao().getConta(usuario.getCpf(), conta.getTipoConta());

        var newValor = conta.getSaldo().add(valor);
        conta.setSaldo(newValor);
        getContaFileDao().alterarConta(tipoConta, conta);

        System.out.println("====================");
        System.out.println("Saldo atualizado: " + conta.getSaldo());
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
