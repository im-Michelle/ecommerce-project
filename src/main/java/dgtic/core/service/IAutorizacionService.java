package dgtic.core.service;

import dgtic.core.entity.Autorizacion;
import dgtic.core.entity.Cliente;

public interface IAutorizacionService {
    public Autorizacion buscarCategoria(String id);

    public void guardar(Autorizacion autorizacion);
}
