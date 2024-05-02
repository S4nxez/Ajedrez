package service;

import domain.Partida;
import domain.Usuario;

import java.util.List;
import java.util.Set;

public interface IJuegoService {

    Usuario buscarUsuarioPorId(int id);

    Set<Usuario> getUsuarios();

    void guardarPartida(Partida partida);

    Partida buscarPartidaPorId(int id);

    List<Partida> obtenerTodasLasPartidas();

    Usuario logIn(String user, String pwd);

    boolean createUser(String text, String text1);

    void guardarUsuarios();

    void cargarUsuarios();
}
