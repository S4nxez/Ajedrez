package dao;

import domain.Usuario;

public interface IUsuarioDAO<T> {
    void guardar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
    Usuario buscarPorId(int id);
}
