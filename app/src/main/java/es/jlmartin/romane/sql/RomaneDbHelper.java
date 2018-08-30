package es.jlmartin.romane.sql;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.List;

import es.jlmartin.romane.R;
import es.jlmartin.romane.sql.entity.Municipio;

public class RomaneDbHelper extends SQLiteOpenHelper {

    private ProgressBar progressBar;
    //ACTIVIDAD
    private static final String SQL_CREATE_ACTIVIDAD =
            "CREATE TABLE " + ContractSql.Actividad.TABLA + " (" +
                    ContractSql.Actividad._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Actividad.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_ACTIVIDAD =
            "DROP TABLE IF EXISTS " + ContractSql.Actividad.TABLA;

    //PERIODO HISTORICO
    private static final String SQL_CREATE_PERIODO_HISTORICO =
            "CREATE TABLE " + ContractSql.PeriodoHistorico.TABLA + " (" +
                    ContractSql.PeriodoHistorico._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.PeriodoHistorico.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_PERIODO_HISTORICO =
            "DROP TABLE IF EXISTS " + ContractSql.PeriodoHistorico.TABLA;

    //TIPO
    private static final String SQL_CREATE_TIPO =
            "CREATE TABLE " + ContractSql.Tipo.TABLA + " (" +
                    ContractSql.Tipo._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Tipo.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_TIPO =
            "DROP TABLE IF EXISTS " + ContractSql.Tipo.TABLA;

    //MUNICIPIO
    private static final String SQL_CREATE_MUNICIPIO =
            "CREATE TABLE " + ContractSql.Municipio.TABLA + " (" +
                    ContractSql.Municipio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Municipio.COLUMNA_NOMBRE + " TEXT," +
                    ContractSql.Municipio.COLUMNA_PROVINCIA_ID + " INTEGER," +
                    ContractSql.Municipio.COLUMNA_LATITUD + " REAL," +
                    ContractSql.Municipio.COLUMNA_LONGITUD + " REAL)";

    private static final String SQL_DELETE_MUNICIPIO =
            "DROP TABLE IF EXISTS " + ContractSql.Municipio.TABLA;

    //PATRIMONIO
    private static final String SQL_CREATE_PATRIMONIO =
            "CREATE TABLE " + ContractSql.Patrimonio.TABLA + " (" +
                    ContractSql.Patrimonio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Patrimonio.COLUMNA_DENOMINACION + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_DESCRIPCION + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_BIBLIOGRAFIA + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_DATOSHISTORICOS + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_CODTIPO + " INTEGER," +
                    ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO + " INTEGER," +
                    ContractSql.Patrimonio.COLUMNA_CODPADRE + " INTEGER," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODTIPO+") REFERENCES "+ContractSql.Tipo.TABLA+"("+ContractSql.Tipo._ID+")," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO+") REFERENCES "+ContractSql.Municipio.TABLA+"("+ContractSql.Municipio._ID+")," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODPADRE+") REFERENCES "+ContractSql.Patrimonio.TABLA+"("+ContractSql.Patrimonio._ID+")" +
                    ")";

    private static final String SQL_DELETE_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    //PATRIMONIO
    private static final String SQL_CREATE_TIPOLOGIA_PATRIMONIO =
            "CREATE TABLE " + ContractSql.TipologiaPatrimonio.TABLA + " (" +
                    ContractSql.TipologiaPatrimonio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_DESCRIPCION + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CRONOLOGIA + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODPATRIMONIO + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODPERIODOHISTORICO + " INTEGER," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODACTIVIDAD + " INTEGER,"+
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODPATRIMONIO+") REFERENCES "+ContractSql.Patrimonio.TABLA+"("+ContractSql.Patrimonio._ID+")," +
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODPERIODOHISTORICO+") REFERENCES "+ContractSql.PeriodoHistorico.TABLA+"("+ContractSql.PeriodoHistorico._ID+")," +
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODACTIVIDAD+") REFERENCES "+ContractSql.Actividad.TABLA+"("+ContractSql.Actividad._ID+")" +
                    ")";

            ;

    private static final String SQL_DELETE_TIPOLOGIA_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Romane.db";

    public RomaneDbHelper(Context context, ProgressBar progressBar) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.progressBar = progressBar;
    }

    private Handler handler = new Handler();
    public void onCreate(SQLiteDatabase db) {

        handler.post(new Runnable() {
            public void run() {
                progressBar.setProgress(10);
            }
        });


        db.execSQL(SQL_CREATE_ACTIVIDAD);
        db.execSQL(SQL_CREATE_PERIODO_HISTORICO);
        db.execSQL(SQL_CREATE_TIPO);
        db.execSQL(SQL_CREATE_MUNICIPIO);
        db.execSQL(SQL_CREATE_PATRIMONIO);
        db.execSQL(SQL_CREATE_TIPOLOGIA_PATRIMONIO);

        //datos municipios
        MunicipiosSqlCreateDbHelper helper = new MunicipiosSqlCreateDbHelper(Resources.getSystem().openRawResource(R.raw.datas_municipios));
        Thread hilo = new Thread(helper);
        hilo.start();
        while(hilo.getState().equals(Thread.State.RUNNABLE)){
            System.out.println("ejecutando");
        }
        List<Municipio> municipios = helper.getMunicipios();
        int count=0;
        handler.post(new Runnable() {
            public void run() {
                progressBar.setProgress(50);
            }
        });
        for (Municipio municipio:municipios) {
            ContentValues values = new ContentValues();
            values.put(ContractSql.Municipio.COLUMNA_NOMBRE,municipio.getNombre());
            values.put(ContractSql.Municipio.COLUMNA_PROVINCIA_ID,municipio.getProvincia_id());
            values.put(ContractSql.Municipio.COLUMNA_LATITUD,municipio.getLatitud());
            values.put(ContractSql.Municipio.COLUMNA_LONGITUD,municipio.getLongitud());
            if(db.insert(ContractSql.Municipio.TABLA, null, values)!=-1){
                count++;
            }else{
                System.err.println("Error al insertar:"+municipio);
            }
        }
        handler.post(new Runnable() {
            public void run() {
                progressBar.setProgress(100);
            }
        });
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(ContractSql.Actividad._ID, 1);
        values.put(ContractSql.Actividad.COLUMNA_DESCRIPCION, "Actividad");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ContractSql.Actividad.TABLA, null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TIPOLOGIA_PATRIMONIO);
        db.execSQL(SQL_DELETE_PATRIMONIO);
        db.execSQL(SQL_DELETE_ACTIVIDAD);
        db.execSQL(SQL_DELETE_PERIODO_HISTORICO);
        db.execSQL(SQL_DELETE_TIPO);
        db.execSQL(SQL_DELETE_MUNICIPIO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
