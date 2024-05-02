package dao;

import domain.Partida;

import java.util.List;

public interface IPartidaDAO<T> {
    void guardar(Partida partida);

    void actualizar(Partida partida);

    void eliminar(Partida partida);

    List<Partida> obtenerTodos();
}
