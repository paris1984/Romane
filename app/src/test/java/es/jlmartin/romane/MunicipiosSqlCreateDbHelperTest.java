package es.jlmartin.romane;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.jlmartin.romane.sql.MunicipiosSqlCreateDbHelper;
import es.jlmartin.romane.sql.entity.Municipio;

public class MunicipiosSqlCreateDbHelperTest {

    @Test
    public void getMunicipiosTest() throws IOException {


        InputStream input = new FileInputStream("src\\test\\java\\es\\jlmartin\\romane\\data_municipios.dat");
        List<Municipio> municipios = MunicipiosSqlCreateDbHelper.creation(input);
        Assert.assertNotNull(municipios);
        Assert.assertFalse(municipios.isEmpty());
        for(Municipio municipio:municipios){
            Assert.assertNotNull(municipio.getLatitud());
            Assert.assertNotNull(municipio.getLongitud());
            Assert.assertNotNull(municipio.getNombre());
            Assert.assertNotNull(municipio.getProvincia_id());
        }
    }
}
