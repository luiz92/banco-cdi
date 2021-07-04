package servico;

import dao.UsuarioFileFileDaoImpl;
import dominio.Usuario;
import javax.inject.Inject;

public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    private UsuarioFileFileDaoImpl usuarioFileDao;

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioFileDao.criarUsuario(usuario);
    }

}
