package dao;

import domain.Usuario;

public interface IUsuarioDAO<T> {
    boolean guardar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
    Usuario buscarPorId(int id);
    boolean logIn(String user, String pwd);
    void guardarUsuarios();
    void cargarUsuarios();
}
