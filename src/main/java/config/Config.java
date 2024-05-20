package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public String loadPathUsuariosProperties(){
        Properties properties= new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
            return (String)properties.get("pathUsuariosJson");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;//path no encontrado
    }

    public String loadPathPartidasProperties(){
        Properties properties= new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
            return (String)properties.get("pathPartidas");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;//path no encontrado
    }
}
