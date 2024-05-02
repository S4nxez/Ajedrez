package ui;

import dao.PartidaDAO;
import dao.UsuarioDAO;
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


    public MainViewModel(JuegoService servicio) {
        this.servicio = servicio;
        usuarios = FXCollections.observableArrayList(servicio.getUsuarios());
    }

    public ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }
}
