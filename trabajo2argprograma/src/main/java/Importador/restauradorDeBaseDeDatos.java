package Importador;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class restauradorDeBaseDeDatos {
	
    public synchronized static void restaurarDB() throws SQLException {
    	
    	
    	
    	String ruta = System.getProperty("user.dir");

        Path origenPath = FileSystems.getDefault().getPath( ruta+"/basededatosoriginal/tierra_media_basededatos.db" );
        Path destinoPath = FileSystems.getDefault().getPath( ruta+"/tierra_media_basededatos.db" );

        
		System.out.println(origenPath);
        
		System.out.println(destinoPath);
		
		Connection conn = ConnectionProvider.closeConnection();
		
        try {
            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        
        conn = ConnectionProvider.getConnection();
        
    }



}
