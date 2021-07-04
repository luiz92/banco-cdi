package servico;

import dominio.Conta;
import dominio.Usuario;
import java.math.BigDecimal;

public interface ContaService {

    void criarConta(Usuario usuario, Conta conta);
    void saqueConta(Usuario usuario, Conta conta, BigDecimal valor);
    Conta depositoConta(Usuario usuario, Conta conta, BigDecimal valor);
    Conta saldoConta(Usuario usuario, Conta conta);

}
