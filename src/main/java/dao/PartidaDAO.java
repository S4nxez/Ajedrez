package dao;

import domain.Partida;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import common.Constantes;

@Log4j2
public class PartidaDAO implements IPartidaDAO<Partida> {
    private final Set<Partida> partidas;
    private static final String FICHEROB = new config.Config().loadPathPartidasProperties();
    @Getter
    @Setter
    private static int autonumerico;

    public PartidaDAO() {
        // Inicializar la lista de partidas
        this.partidas = leerFicheroBinario();
        autonumerico = partidas.size();
    }

    @Override
    public boolean crearFicheros() throws IOException {
        File fichero = new File(FICHEROB);
        if (!fichero.exists())
            return (fichero.createNewFile());
        return false;
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
        } else {
            try {
                if (crearFicheros())
                    log.info(Constantes.CREANDO_BINARIO + "{}", FICHEROB);
            } catch (IOException ex) {
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
            log.info(Constantes.ESCRIBIENDO_FICHERO + "{}", FICHEROB);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PartidaDAO.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return escrito;
    }

    @Override
    public void guardar(Partida partida) {
        // Agregar la partida a la lista
        partidas.remove(partida);
        if (partidas.add(partida) && escribirFicheroBinario())
            log.info(Constantes.PARTIDA_GUARDADA + "{}", partida.getId());
    }

    @Override
    public void eliminar(Partida partida) {
        // Implementar lógica para eliminar una partida de la base de datos (si es necesario)
        partidas.remove(partida);
        log.info(Constantes.PARTIDA_ELIMINADA + "{}", partida.getId());
    }

    @Override
    public Set<Partida> obtenerTodos() {
        return partidas;
    }

    @Override
    public Partida getPartidaById(int partidaId) {
        return partidas.stream().filter(partida -> partida.getId() == partidaId).findFirst().orElse(null);
    }
}
