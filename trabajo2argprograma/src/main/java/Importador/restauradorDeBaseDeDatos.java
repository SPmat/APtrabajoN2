package Importador;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class restauradorDeBaseDeDatos {
	
    public static void main(String[] args) {
    	
    	
    	
    	String ruta = System.getProperty("user.dir");

        Path origenPath = FileSystems.getDefault().getPath( ruta+"/basededatosoriginal/tierra_media_basededatos.db" );
        Path destinoPath = FileSystems.getDefault().getPath( ruta+"/tierra_media_basededatos.db" );

        
        
		System.out.println(origenPath);
        
		System.out.println(destinoPath);

		
		
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
        
        

    }

}
