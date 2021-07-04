package dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String nome;
    private Integer idade;
    private String cpf;
    private String senha;

    public Usuario(String txtLinhas) {
        String[] split = txtLinhas.split(";");
        this.nome = split[0].trim().replace("\"", "");
        this.idade = Integer.parseInt(split[1].trim().replace("\"", ""));
        this.cpf = split[2].trim().replace("\"", "");
        this.senha = split[3].trim().replace("\"", "");
    }

    public String toString() {
        return this.getNome() + ";" + this.getIdade() + ";" + this.getCpf() + ";" + this.getSenha();
    }

}
