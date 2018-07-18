package es.jlmartin.romane.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RomaneDbHelper extends SQLiteOpenHelper {

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
                    ContractSql.Patrimonio.COLUMNA_CODPADRE + " INTEGER)";

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
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODACTIVIDAD + " INTEGER)";

    private static final String SQL_DELETE_TIPOLOGIA_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Romane.db";

    public RomaneDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACTIVIDAD);
        db.execSQL(SQL_CREATE_PERIODO_HISTORICO);
        db.execSQL(SQL_CREATE_TIPO);
        db.execSQL(SQL_CREATE_MUNICIPIO);
        db.execSQL(SQL_CREATE_PATRIMONIO);
        db.execSQL(SQL_CREATE_TIPOLOGIA_PATRIMONIO);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ACTIVIDAD);
        db.execSQL(SQL_DELETE_PERIODO_HISTORICO);
        db.execSQL(SQL_DELETE_TIPO);
        db.execSQL(SQL_DELETE_MUNICIPIO);
        db.execSQL(SQL_DELETE_PATRIMONIO);
        db.execSQL(SQL_DELETE_TIPOLOGIA_PATRIMONIO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
