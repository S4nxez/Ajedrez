package dao;

import domain.Partida;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PartidaDAO implements IPartidaDAO<Partida> {
    private final Set<Partida> partidas;
    private static final String FICHEROB = "binarioPartidas";
    @Getter
    @Setter
    private static int autonumerico;



    public PartidaDAO() {
        // Inicializar la lista de partidas
        this.partidas = leerFicheroBinario();
        autonumerico = partidas.size();
    }

    @Override
    public void crearFicheros() throws IOException {
        File fichero = new File(FICHEROB);
        if (!fichero.exists())
            fichero.createNewFile();
    }

    @Override
    public Set<Partida> leerFicheroBinario() {
        Set<Partida> ret = new HashSet<>();
        File file = new File(FICHEROB);

        if (file.exists() && file.length() != 0) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = is.readObject();
                if (obj instanceof Set)
                    ret = (Set<Partida>) obj;
                else
                    ret = new HashSet<>();

            } catch (IOException | ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(PartidaDAO.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return ret;
    }

    @Override
    public boolean escribirFicheroBinario() {
        boolean escrito = false;
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FICHEROB))) {
            os.writeObject(this.partidas);
            escrito = true;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PartidaDAO.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return escrito;
    }

    @Override
    public void guardar(Partida partida) {
        // Agregar la partida a la lista
        partidas.add(partida);
        escribirFicheroBinario();
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
    public Set<Partida> obtenerTodos() {
        return partidas;
    }
}
