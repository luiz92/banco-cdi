package annotationLiteral;

import dominio.ContaEnum;
import servico.TipoConta;

import javax.enterprise.util.AnnotationLiteral;

public class ContaAnnotationLiteral extends AnnotationLiteral<TipoConta> implements TipoConta{

    private final ContaEnum contaEnum;

    public ContaAnnotationLiteral(ContaEnum contaEnum) {
        this.contaEnum = contaEnum;
    }

    @Override
    public ContaEnum value(){
        return contaEnum;
    }
}
