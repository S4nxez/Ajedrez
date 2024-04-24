package dao;

import domain.Usuario;

public class UsuarioDAO implements IUsuarioDAO<Usuario> {
    @Override
    public void guardar(Usuario usuario) {
        // Lógica para guardar un usuario en la base de datos
    }

    @Override
    public void actualizar(Usuario objeto) {

    }

    @Override
    public void eliminar(Usuario objeto) {

    }

    @Override
    public Usuario buscarPorId(int id) {
        return null;
    }

    // Implementar otros métodos de InterfazDAO según sea necesario
}