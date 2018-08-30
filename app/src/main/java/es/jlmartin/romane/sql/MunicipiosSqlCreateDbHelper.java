package es.jlmartin.romane.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import es.jlmartin.romane.sql.entity.Municipio;

public class MunicipiosSqlCreateDbHelper implements Runnable {

    private List<Municipio> municipios = new ArrayList<>();
    private boolean finalizado = false;
    private InputStream inputStream;

    public MunicipiosSqlCreateDbHelper(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void creation(InputStream inputStream) {


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linea;
            while ((linea = br.readLine()) != null) {
                Integer provincia_id = Integer.parseInt(linea.substring(0, linea.indexOf(",")));
                linea = linea.substring(linea.indexOf(",")+3);
                String nombre = linea.substring(0, linea.indexOf("'"));
                linea  = linea.substring(linea.lastIndexOf("'")+3);
                String[] coordenadas = linea.split(", ");
                Double latitud = Double.parseDouble(coordenadas[0]);
                Double longitud = Double.parseDouble(coordenadas[1]);
                Municipio municipio = new Municipio(nombre,provincia_id,latitud,longitud);
                municipios.add(municipio);
            }
            finalizado  = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        creation(this.inputStream);
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
}
