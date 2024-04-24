package service;

import dao.IPartidaDAO;
import dao.IUsuarioDAO;
import domain.Juego;
import domain.Usuario;

import java.util.List;

public class JuegoService {
    private IUsuarioDAO usuarioDAO;
    private IPartidaDAO partidaDAO;

    public JuegoService(IUsuarioDAO usuarioDAO, IPartidaDAO partidaDAO) {
        this.usuarioDAO = usuarioDAO;
        this.partidaDAO = partidaDAO;
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return null;//usuarioDAO.obtenerTodos();
    }

    public void guardarPartida(Juego partida) {
        partidaDAO.guardar(partida);
    }

    public Juego buscarPartidaPorId(int id) {
        return null;//partidaDAO.buscarPorId(id);
    }

    public List<Juego> obtenerTodasLasPartidas() {
        return partidaDAO.obtenerTodos();
    }

}
