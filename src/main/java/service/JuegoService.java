package service;

import dao.IPartidaDAO;
import dao.IUsuarioDAO;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import domain.Partida;
import domain.Usuario;

import java.util.List;
import java.util.Set;

public class JuegoService implements IJuegoService {
    private final IUsuarioDAO usuarioDAO;
    private final IPartidaDAO partidaDAO;

    public JuegoService(UsuarioDAO usuarioDAO, PartidaDAO partidaDAO) {
        this.usuarioDAO = usuarioDAO;
        this.partidaDAO = partidaDAO;
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public Set<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios();
    }

    @Override
    public void guardarPartida(Partida partida) {
        partidaDAO.guardar(partida);
    }

    @Override
    public Partida buscarPartidaPorId(int id) {
        return null;//partidaDAO.buscarPorId(id);
    }

    @Override
    public Set<Partida> obtenerTodasLasPartidas() {
        return partidaDAO.obtenerTodos();
    }

    @Override
    public Usuario logIn(String user, String pwd) {
        return usuarioDAO.logIn(user, pwd);
    }

    @Override
    public boolean addUser(String usuario, String pwd, boolean admin) {
        return usuarioDAO.guardar(new Usuario(admin, usuario, pwd));
    }

    @Override
    public void guardarUsuarios() {
        usuarioDAO.guardarUsuarios();
    }

    @Override
    public void cargarUsuarios() {
        usuarioDAO.cargarUsuarios();
    }

    @Override
    public boolean addUsuario(Usuario usuario) {
        return usuarioDAO.guardar(usuario);
    }

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        return usuarioDAO.delete(usuario);
    }

    @Override
    public boolean updateUsuario(Usuario user1, Usuario user2) {
        return usuarioDAO.update(user1, user2);
    }

    @Override
    public Set<Usuario> orderBy(String orden) {
        return usuarioDAO.orderBy(orden);
    }

    @Override
    public Partida getPartidaById(int partidaId) {
        return partidaDAO.getPartidaById(partidaId);
    }

}
