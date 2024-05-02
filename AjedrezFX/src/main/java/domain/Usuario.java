package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private boolean isAdmin;
    private String nombreUsuario;
    private String contrasenya;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    public Usuario(String cadena){
        String[] trozos = cadena.split(":");
        this.id = Integer.parseInt(trozos[0]);
        this.isAdmin = Boolean.parseBoolean(trozos[1]);
        this.nombreUsuario = trozos[2];
        this.contrasenya = trozos[3];
    }

    public boolean getIsAdmin() {// lo escribo aunque tenga lombok porque sino PropertyValueFactory fallaba
        return isAdmin;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombreUsuario);
    }

    @Override
    public String toString() {
        return id + ":" + isAdmin + ":" + nombreUsuario + ":" + contrasenya;
    }
}