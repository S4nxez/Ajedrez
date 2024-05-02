package dao;

import domain.Usuario;

import java.util.List;
import java.util.Set;

public interface IUsuarioDAO<T> {
    Set<T> getUsuarios();
    boolean guardar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
    Usuario buscarPorId(int id);
    Usuario logIn(String user, String pwd);
    void guardarUsuarios();
    void cargarUsuarios();
}
