package service;

import dao.IPartidaDAO;
import dao.IUsuarioDAO;
import domain.Juego;
import domain.Usuario;

import java.util.List;

public interface IJuegoService {


    void guardarUsuario(Usuario usuario);

    Usuario buscarUsuarioPorId(int id);

    List<Usuario> obtenerTodosLosUsuarios();

    void guardarPartida(Juego partida);

    Juego buscarPartidaPorId(int id);

    List<Juego> obtenerTodasLasPartidas();

    boolean logIn(String user, String pwd);

    boolean createUser(String text, String text1);
}