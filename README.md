# Proyecto Ajedrez

Este es un proyecto de ajedrez desarrollado con JavaFX. La aplicación permite a los usuarios registrarse, iniciar sesión, jugar partidas de ajedrez y gestionar usuarios (en el caso de administradores). Los datos de usuarios y partidas se almacenan en archivos JSON y se gestionan en memoria con un `HashSet` en la capa DAO.

## Librerías Utilizadas

- **JavaFX**: Para la interfaz gráfica de usuario.
- **Lombok**: Para la generación automática de código (getters, setters, constructores, etc.).
- **LocalTime**: Para manejar la fecha y hora.
- **Log4j2**: Para la gestión de logs.
- **Gson**: Para la manipulación de archivos JSON.

## Estructura del Proyecto

El proyecto está organizado en las siguientes carpetas:

- **common**: Contiene las clases comunes utilizadas en todo el proyecto.
- **dao**: Clases que acceden a la lista de usuarios y de partidas.
- **service**: Comunicación entre la interfaz de usuario (UI) y el DAO.
- **ui**: Interfaz gráfica.
- **domain**: Lógica de juego y la clase Usuario.
- **config**: Ruta del archivo `.properties`.

La carpeta **resources** está organizada en:

- **fxml**: Contiene todos los archivos FXML.
- **imagenes**: Contiene una imagen generada con inteligencia artificial.
- Archivos en la raíz:
  - `config.properties`
  - `log4j2.xml`
  - `style.css`

## Funcionalidades

### Pantalla de Inicio de Sesión

- **Login**: Los usuarios pueden iniciar sesión ingresando su nombre de usuario y contraseña.
  - Se verifica que ambos campos cumplan con una expresión regular (regex).
- **Registro**: Si el usuario no tiene una cuenta, puede ir a una pantalla para crear una nueva.

### Pantallas de Usuario

Dependiendo del tipo de usuario (administrador o no administrador), la aplicación redirige a diferentes pantallas después del inicio de sesión.

#### Administrador

- **Gestión de Usuarios**:
  - Tabla de usuarios que puede ordenarse.
  - Añadir, modificar y eliminar usuarios.
  - Campos a la derecha que se rellenan con la información del usuario seleccionado.

#### Usuario Regular

- **Jugar Partida**:
  - Muestra un tablero de ajedrez para jugar.
  - Permite guardar la partida.
- **Menú de Partidas**:
  - Muestra todas las partidas de ajedrez guardadas con sus fechas.
  - Permite recuperar las partidas guardadas.

## Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/S4nxez/Ajedrez Ajedrez

2. Navega al directorio del proyecto:
   ```bash
	cd ./Ajedrez

3. Compila y ejecuta el proyecto utilizando tu IDE favorito o desde la línea de comandos:
   ```bash
	mvn clean install
	mvn javafx:run

## Contribución

1. Haz un fork del repositorio.
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
3. Realiza los cambios y haz commit (git commit -m 'Añadir nueva funcionalidad').
4. Push a la rama (git push origin feature/nueva-funcionalidad).
5. Abre una Pull Request.

#Licencia

Este proyecto está licenciado bajo la Licencia MIT. Para más información, consulta el archivo LICENSE.