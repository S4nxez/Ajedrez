package dao;

import domain.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UsuarioDAO implements IUsuarioDAO<Usuario> {

    private Set<Usuario> usuarios;
    public static final String FICHERO = "Usuarios";

    public UsuarioDAO(){
        usuarios = leerDiccionarioUsuarios(FICHERO);
    }
    private void crearFichero() {
        File fichero1 = new File(FICHERO);
        if (!fichero1.exists()) {
            try {
                fichero1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(fichero1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for(Usuario usuario :  crearDiccionarioUsuarios()){
                pw.print(usuario);
            }
            pw.close();
        }
    }

    private Set<Usuario> leerDiccionarioUsuarios(String fichero){
        Set<Usuario> ret = null;

        crearFichero();
        try (Scanner sc = new Scanner(new File(fichero))) {
            ret = new HashSet<>();
            while (sc.hasNextLine()) {
                String cadena = sc.nextLine();
                ret.add(new Usuario(cadena));
            }
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex); //no entiendo
        }
        return ret;
    }

    private static Set<Usuario> crearDiccionarioUsuarios() {
        Set<Usuario> users = new HashSet<>();
        users.add(new Usuario(1, true, "admin", "admin"));
        users.add(new Usuario(2, false, "user", "user"));
        return users;
    }

    @Override
    public boolean guardar(Usuario usuario) {
        boolean ret = usuarios.add(usuario);

        if (ret)
            guardarUsuarios();

        return ret;
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

    @Override
    public boolean logIn(String user, String pwd) {
        cargarUsuarios();

        for(Usuario usuario : usuarios){
            if(usuario.getNombreUsuario().equals(user) && usuario.getContrasenya().equals(pwd)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(new File(FICHERO))) {
            for (Usuario usuario : usuarios) {
                pw.println(usuario);
            }
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void cargarUsuarios() {
        usuarios = leerDiccionarioUsuarios(FICHERO);
    }

}