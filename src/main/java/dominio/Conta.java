package dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Conta {
    private String usuario;
    private String tipoConta;
    private BigDecimal saldo;

    public Conta(String txtLinhas) {
        String[] split = txtLinhas.split(";");
        this.usuario = split[0].trim().replace("\"", "");
        this.tipoConta = split[1].trim().replace("\"", "");
        this.saldo = BigDecimal.valueOf(Double.parseDouble(split[2].trim().replace("\"", "")));
    }

    public String toString() {
        return  this.getUsuario() +";"+ this.getTipoConta() +";"+ this.getSaldo();
    }

}
