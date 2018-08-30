package es.jlmartin.romane;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.jlmartin.romane.sql.ContractSql;
import es.jlmartin.romane.sql.RomaneDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RomaneDbHelper helper = new RomaneDbHelper(this,null);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ContractSql.Actividad.COLUMNA_DESCRIPCION};

        Cursor cursor = db.query(
                ContractSql.Actividad.TABLA,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String des = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Actividad.COLUMNA_DESCRIPCION));
            itemIds.add(itemId);
        }
        cursor.close();
        db.close();
    }
}
