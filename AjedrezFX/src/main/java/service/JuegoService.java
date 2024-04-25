package service;

import dao.IPartidaDAO;
import dao.IUsuarioDAO;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Juego;
import domain.Usuario;

import java.util.List;

public class JuegoService implements IJuegoService {
    private final IUsuarioDAO usuarioDAO;
    private final IPartidaDAO partidaDAO;

    public JuegoService() {
        this.usuarioDAO = new UsuarioDAO();
        this.partidaDAO = new PartidaDAO();
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return null;//usuarioDAO.obtenerTodos();
    }

    @Override
    public void guardarPartida(Juego partida) {
        partidaDAO.guardar(partida);
    }

    @Override
    public Juego buscarPartidaPorId(int id) {
        return null;//partidaDAO.buscarPorId(id);
    }

    @Override
    public List<Juego> obtenerTodasLasPartidas() {
        return partidaDAO.obtenerTodos();
    }

    @Override
    public boolean logIn(String user, String pwd) {
        return usuarioDAO.logIn(user, pwd);
    }

    @Override
    public boolean createUser(String usuario, String pwd) {
        if (usuarioDAO.guardar(new Usuario(3, false, usuario, pwd))) {
            return true;
        }
        return false;
    }

}
