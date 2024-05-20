package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Usuario;
import lombok.Getter;
import config.Config;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import common.Constantes;

@Log4j2
public class UsuarioDAO implements IUsuarioDAO<Usuario> {

    @Getter
    private Set<Usuario> usuarios;
    public static final String FICHERO = new Config().loadPathUsuariosProperties();
    @Getter
    private static int autonumerico;

    public UsuarioDAO(){
        usuarios = leerDiccionarioUsuarios(FICHERO);
        autonumerico = usuarios.size();
    }

    public void setAutonumerico(int auto) {
        autonumerico = auto;
    }

    private void crearFichero() {
        File fichero1 = new File(FICHERO);
        if (!fichero1.exists()) {
            try {
                fichero1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (FileWriter writer = new FileWriter(fichero1)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                List<Usuario> userList = crearDiccionarioUsuarios().stream().collect(Collectors.toList());
                String json = gson.toJson(userList);
                writer.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Set<Usuario> leerDiccionarioUsuarios(String fichero){
        Set<Usuario> ret = new HashSet<>();

        crearFichero();
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            Usuario[] usuariosArray = gson.fromJson(br, Usuario[].class);
            ret.addAll(Arrays.asList(usuariosArray));
        } catch (FileNotFoundException e) {
            java.util.logging.Logger.getLogger(UsuarioDAO.class.getName()).log(java.util.logging.Level.SEVERE, e.getMessage(), e);
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
        if (ret) {
            setAutonumerico(usuarios.size());
            Usuario nuevo = new Usuario(autonumerico, usuario.isAdmin(), usuario.getNombreUsuario(), usuario.getContrasenya());
            update(nuevo, usuario);
            log.info(Constantes.USUARIO_ANYADIDO + "{}", usuario.getId());
            guardarUsuarios();
        }
        return ret;
    }


    @Override
    public Usuario buscarPorId(int id) {
        return null;
    }

    @Override
    public Usuario logIn(String user, String pwd) {
        cargarUsuarios();
        return usuarios.stream()
                .filter(usuario -> usuario.getNombreUsuario().equals(user) && usuario.getContrasenya().equals(pwd))
                .findFirst().orElse(null);
    }


    @Override
    public void guardarUsuarios() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Usuario> userList = new ArrayList<>(usuarios);
        String json = gson.toJson(userList);
        try (FileWriter writer = new FileWriter(FICHERO, false)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarUsuarios() {
        usuarios = leerDiccionarioUsuarios(FICHERO);
    }

    @Override
    public boolean delete(Usuario usuario) {
        boolean ret = usuarios.remove(usuario);
        log.info(Constantes.USUARIO_ELIMINADO + "{}", usuario.getId());
        guardarUsuarios();
        return ret;
    }

    @Override
    public boolean update(Usuario user2, Usuario user1) {
        boolean ret = usuarios.remove(user1);
        if (user1.getNombreUsuario().equals(user2.getNombreUsuario()) &&
                user1.getContrasenya().equals(user2.getContrasenya()) &&
                user1.isAdmin() == user2.isAdmin() &&
                user1.getId() == user2.getId()){
            ret = false;
        }
        if (ret && usuarios.add(user2)) {
            guardarUsuarios();
            log.info(Constantes.USUARIO_ACTUALIZADO + "{}", user1.getId());
        }else {
            ret = false;
            usuarios.add(user1);
        }
        return ret;
    }

    @Override
    public Set<Usuario> orderBy(String orden) {
        Comparator<Usuario> comparator;

        if (orden.equals("Nombre")) {
            comparator = Comparator.comparing(Usuario::getNombreUsuario);
        } else {
            comparator = Comparator.comparing(Usuario::getId);
        }
        return usuarios.stream().sorted(comparator).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}