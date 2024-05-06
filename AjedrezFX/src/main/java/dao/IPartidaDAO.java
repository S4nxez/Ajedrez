package dao;

import domain.Partida;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IPartidaDAO<T> {
    void crearFicheros() throws IOException;

    Set<Partida> leerFicheroBinario();

    boolean escribirFicheroBinario();

    void guardar(Partida partida);

    void actualizar(Partida partida);

    void eliminar(Partida partida);

    Set<Partida> obtenerTodos();

}
