package aplicacao;

import dao.ContaFileDao;
import dao.UsuarioFileDao;
import dominio.Usuario;
import lombok.Getter;
import servico.ContaFactory;
import view.ContaView;
import view.UsuarioView;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Aplicacao {
    private List<Usuario> usuarios;

    @Inject
    private UsuarioFileDao usuarioFileDao;

    @Inject
    private ContaFileDao contaFileDao;

    @Inject
    private UsuarioView usuarioView;

    @Inject
    private ContaView contaView;

    @Inject
    private ContaFactory contaFactory;

    @PostConstruct
    public void iniciar(){
        System.out.println("***      Iniciando a aplicacao      ***");
        usuarios = new ArrayList<>();
        getUsuarioFileDao().preparaArquivo();
    }
}
