package dao;

import domain.Juego;

import java.util.List;

public interface IPartidaDAO<T> {
    void guardar(Juego partida);

    void actualizar(Juego partida);

    void eliminar(Juego partida);

    List<Juego> obtenerTodos();
}
