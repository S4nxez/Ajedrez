package ui;

import domain.Partida;
import domain.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import service.IJuegoService;
import service.JuegoService;

public class MainViewModel {
    @Getter
    private final IJuegoService servicio;
    private final ObservableList<Usuario> usuarios;
    private final ObservableList<Partida> partidas;


    public MainViewModel(JuegoService servicio) {
        this.servicio = servicio;
        usuarios = FXCollections.observableArrayList(servicio.getUsuarios());
        partidas = FXCollections.observableArrayList(servicio.obtenerTodasLasPartidas());
    }

    public ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ObservableList<Partida> getPartidas() {
        return partidas;
    }
}
