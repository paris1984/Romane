package es.jlmartin.romane.sql;

import android.provider.BaseColumns;

public class ContractSql {
    private ContractSql(){

    }
    public static class Actividad implements BaseColumns {
        public static final String TABLA = "actividad";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }

    public static class PeriodoHistorico implements BaseColumns {
        public static final String TABLA = "periodo_historico";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }


    public static class Tipo implements BaseColumns {
        public static final String TABLA = "tipo";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }

    public static class Municipio implements BaseColumns {
        public static final String TABLA = "municipio";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_LATITUD = "latitud";
        public static final String COLUMNA_LONGITUD = "longitud";
    }

    public static class Patrimonio implements BaseColumns {
        public static final String TABLA = "patrimonio";
        public static final String COLUMNA_DENOMINACION = "denominacion";
        public static final String COLUMNA_OTRASDENOMINACIONES = "otras_denominaciones";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_BIBLIOGRAFIA = "bibliografia";
        public static final String COLUMNA_DATOSHISTORICOS = "datos_historicos";
        public static final String COLUMNA_CODTIPO = "cod_tipo";
        public static final String COLUMNA_CODMUNICIPIO = "cod_municipio";
        public static final String COLUMNA_CODPADRE = "cod_padre";
    }

    public static class TipologiaPatrimonio implements BaseColumns {
        public static final String TABLA = "tipologia_patrimonio";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_CRONOLOGIA = "cronologia";
        public static final String COLUMNA_CODPATRIMONIO = "cod_patrimonio";
        public static final String COLUMNA_CODPERIODOHISTORICO = "cod_periodo_historico";
        public static final String COLUMNA_CODACTIVIDAD = "cod_actividad";
    }
}
