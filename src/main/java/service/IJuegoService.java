package service;

import domain.Partida;
import domain.Usuario;

import java.util.Set;

public interface IJuegoService {

    Usuario buscarUsuarioPorId(int id);

    Set<Usuario> getUsuarios();

    void guardarPartida(Partida partida);

    Partida buscarPartidaPorId(int id);

    Set<Partida> obtenerTodasLasPartidas();

    Usuario logIn(String user, String pwd);

    boolean addUser(String nombre, String contrasenya, boolean admin);

    void guardarUsuarios();

    void cargarUsuarios();

    boolean addUsuario(Usuario usuario);

    boolean deleteUsuario(Usuario usuario);

    boolean updateUsuario(Usuario user1, Usuario user2);

    Set<Usuario> orderBy(String orden);

    Partida getPartidaById(int partidaId);
}
