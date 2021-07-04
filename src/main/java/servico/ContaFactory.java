package servico;

import annotationLiteral.ContaAnnotationLiteral;
import dominio.ContaEnum;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class ContaFactory {

    @Inject
    @Any
    private Instance<ContaService> contaServiceInstance;

    public ContaService createConta(ContaEnum contaEnum){
       final ContaAnnotationLiteral literal = new ContaAnnotationLiteral(contaEnum);
       return contaServiceInstance.select(literal).get();
    }

    public ContaService saque(ContaEnum contaEnum){
        final ContaAnnotationLiteral literal = new ContaAnnotationLiteral(contaEnum);
        return contaServiceInstance.select(literal).get();
    }

    public ContaService deposito(ContaEnum contaEnum){
        final ContaAnnotationLiteral literal = new ContaAnnotationLiteral(contaEnum);
        return contaServiceInstance.select(literal).get();
    }

    public ContaService saldo(ContaEnum contaEnum){
        final ContaAnnotationLiteral literal = new ContaAnnotationLiteral(contaEnum);
        return contaServiceInstance.select(literal).get();
    }

}
