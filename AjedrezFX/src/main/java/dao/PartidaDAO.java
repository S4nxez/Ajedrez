package dao;

import domain.Juego;

import java.util.ArrayList;
import java.util.List;

public class PartidaDAO implements IPartidaDAO<Juego> {
    private final List<Juego> partidas;

    public PartidaDAO() {
        // Inicializar la lista de partidas
        this.partidas = new ArrayList<>();
    }

    @Override
    public void guardar(Juego partida) {
        // Agregar la partida a la lista
        partidas.add(partida);
    }

    @Override
    public void actualizar(Juego partida) {
        // Implementar lógica para actualizar una partida en la base de datos (si es necesario)
    }

    @Override
    public void eliminar(Juego partida) {
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
    public List<Juego> obtenerTodos() {
        // Devolver todas las partidas
        return partidas;
    }
}
