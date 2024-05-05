package dao;

import domain.Partida;

import java.io.IOException;
import java.util.List;

public interface IPartidaDAO<T> {
    void crearFicheros() throws IOException;

    List<Partida> leerFicheroBinario();

    boolean escribirFicheroBinario();

    void guardar(Partida partida);

    void actualizar(Partida partida);

    void eliminar(Partida partida);

    List<Partida> obtenerTodos();

}
