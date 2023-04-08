package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Usuario;

public interface IUsuarioService {
    public Usuario buscarUsuario(String dato);
    public void guardar(Usuario usuario);
}
