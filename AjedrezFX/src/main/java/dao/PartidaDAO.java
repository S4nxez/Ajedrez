package dao;

import domain.Partida;

import java.util.ArrayList;
import java.util.List;

public class PartidaDAO implements IPartidaDAO<Partida> {
    private final List<Partida> partidas;

    public PartidaDAO() {
        // Inicializar la lista de partidas
        this.partidas = new ArrayList<>();
    }

    @Override
    public void guardar(Partida partida) {
        // Agregar la partida a la lista
        partidas.add(partida);
    }

    @Override
    public void actualizar(Partida partida) {
        // Implementar lógica para actualizar una partida en la base de datos (si es necesario)
    }

    @Override
    public void eliminar(Partida partida) {
        // Implementar lógica para eliminar una partida de la base de datos (si es necesario)
        partidas.remove(partida);
    }

   /* @Override
    public Juego buscarPorId(int id) {
        // Implementar lógica para buscar una partida por su ID en la base de datos (si es necesario)
        for (Juego partida : partidas) {
            if (partida.getId() == id) {
                return partida;
            }
        }
        return null; // Si no se encuentra la partida con el ID especificado
    }*/

    @Override
    public List<Partida> obtenerTodos() {
        // Devolver todas las partidas
        return partidas;
    }
}
