package es.jlmartin.romane;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.jlmartin.romane.sql.ContractSql;
import es.jlmartin.romane.sql.RomaneDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RomaneDbHelper helper = new RomaneDbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ContractSql.Actividad._ID, 1);
        values.put(ContractSql.Actividad.COLUMNA_DESCRIPCION, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ContractSql.Actividad.TABLA, null, values);
    }
}
