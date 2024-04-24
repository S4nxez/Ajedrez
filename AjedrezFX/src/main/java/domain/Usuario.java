package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contraseña;
    // Otros campos y métodos relacionados con el usuario...
}