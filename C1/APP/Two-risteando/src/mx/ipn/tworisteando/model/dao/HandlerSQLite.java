package mx.ipn.tworisteando.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HandlerSQLite extends SQLiteOpenHelper
{

	private static final String DATABASE_NAME="Tworisteando.db";
	private static final int DATABASE_VERSION=1;
	
	public static final String TABLE_MTRANSPORTE = "MedioTransporte";
	public static final String MTRANSPORTE_ID = "nombre";
    public static final String MTRANSPORTE_PRECIO = "precio";
    
    public static final String CREATE_MTRANSPORTE = "CREATE TABLE " + TABLE_MTRANSPORTE +
    		" (" + MTRANSPORTE_ID + " TEXT PRIMARY KEY, " +
    		MTRANSPORTE_PRECIO + " REAL); ";
    
    public static final String TABLE_LINEA = "Linea";
    public static final String LINEA_ID = "numero";
    public static final String LINEA_COLOR = "color";
    public static final String LINEA_FKT = "nombreTransorte";
    
    public static final String CREATE_LINEA = "CREATE TABLE " + TABLE_LINEA + " (" + LINEA_ID +
    		"TEXT NOT NULL," + LINEA_COLOR + " TEXT, " + LINEA_FKT + " TEXT NOT NULL, " + 
    		" PRIMARY KEY ("+LINEA_ID+", "+ LINEA_FKT +"), FOREIGN KEY (" +LINEA_FKT+ ") REFERENCES "+ TABLE_MTRANSPORTE+"("+MTRANSPORTE_ID+")); ";

    public static final String TABLE_ESTACIONES = "Estaciones";
    public static final String ESTACIONES_NOMBRE = "nombre";
    public static final String ESTACIONES_LONGITUD = "longitud";
    public static final String ESTACIONES_LATITUD = "latitud";
    public static final String ESTACIONES_ESIGUIENTE = "estacionSiguiente";
    public static final String ESTACIONES_NUMERO = "numeroLinea";
    public static final String ESTACIONES_TRANSPNOMBRE = "nombreTransporte";
    public static final String ESTACIONES_TIPO = "tipo";//0=TERMINAL, 1=DE PASO, 2= TRANSBORDE, 3= TRANSBORDE Y TERMINAL
    
    public static final String CREATE_ESTACIONES = "CREATE TABLE " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + 
    		" TEXT NOT NULL, " + ESTACIONES_LONGITUD + " REAL, " + ESTACIONES_LATITUD + " REAL, " + 
    		ESTACIONES_ESIGUIENTE + " TEXT, " + ESTACIONES_NUMERO + " TEXT NOT NULL," + ESTACIONES_TRANSPNOMBRE + " TEXT NOT NULL, " + ESTACIONES_TIPO + " INTEGER, "+
    		"PRIMARY KEY ("+ESTACIONES_NOMBRE+", "+ESTACIONES_NUMERO+", "+ESTACIONES_TRANSPNOMBRE+"), FOREIGN KEY ("+ ESTACIONES_TRANSPNOMBRE+ ", "+ ESTACIONES_NUMERO + 
    		") REFERENCES"+ TABLE_LINEA+"("+LINEA_FKT+ ", "+LINEA_ID+ "));";
    

    
	public HandlerSQLite(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL(CREATE_MTRANSPORTE);
		db.execSQL(CREATE_LINEA);
		db.execSQL(CREATE_ESTACIONES);
		
		String query = "INSERT INTO " + TABLE_MTRANSPORTE + " (" + MTRANSPORTE_ID + ", " + MTRANSPORTE_PRECIO + ") VALUES ('Metro','5.0'), ('Metro Bus','6.0')";
		db.execSQL(query);
		
		query = "INSERT INTO " + TABLE_LINEA + " ("+ LINEA_ID + ", " + LINEA_COLOR + ", " +LINEA_FKT + ") VALUES ('1','Rosa','Metro'), ('2','Azul','Metro'), "+
		"('3','Verde','Metro'), ('4','Azul Claro','Metro'), ('5','Amarillo','Metro'), ('6','Rojo','Metro'), ('7','Naranja','Metro'), ('8','Verde Agua','Metro'), ('9','Cafe','Metro'),"+
		"('A','Morado','Metro'), ('B','Verde con Gris','Metro'), ('12','Dorado','Metro'), ('1','Rojo','Metro Bus'), ('2','Morado','Metro Bus'), ('3','Verde','Metro Bus'), "+
		"('4','Naranja','Metro Bus'), ('5','Azul','Metro Bus')";
		db.execSQL(query);
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO +") VALUES ('Observatorio','-99.200343','19.398091','Tacubaya','1','Metro','0'), ('Tacubaya','-99.188868','19.402083','Juanacatlan','1','Metro','2'), " +
		"('Juanacatlan','-99.182212','19.413032','Chapultepec','1','Metro','1'), ('Chapultepec','-99.176269','19.420742','Sevilla','1','Metro','1'), ('Sevilla','-99.170669','19.421491','Insurgentes','1','Metro','1'), " +
		"('Insurgentes','-99.162773','19.423596','Cuauhtemoc','1','Metro','1'), ('Cuauhtemoc','-99.154704','19.425781','Balderas','1','Metro','1'), ('Balderas','-99.149147','19.42744','Salto del agua','1','Metro','2'), "+
		"('Salto del agua','-99.141672','19.428391','Isabel la catolica','1','Metro','2'), ('Isabel la catolica','-99.137495','19.42653','Pino suarez','1','Metro','1'), ('Pino suarez','-99.132903','19.425822','Merced','1','Metro','2'), "+
		"('Merced','-99.124621','19.425437','Candelaria','1','Metro','1'), ('Candelaria','-99.119385','19.428978','San lazaro','1','Metro','2'), ('San lazaro','-99.114772','19.430233','Moctezuma','1','Metro','2'), "+
		"('Moctezuma','-99.110351','19.427177','Balbuena','1','Metro','1'), ('Balbuena','-99.10224','19.423211','Boulevard puerto aereo','1','Metro','1'), ('Boulevard puerto aereo','-99.095996','19.419751','Gomez farias','1','Metro','1'), "+
		"('Gomez farias','-99.090289','19.416472','Zaragoza','1','Metro','1'), ('Zaragoza','-99.082401','19.412293','Pantitlan','1','Metro','1'), ('Pantitlan','-99.072157','19.415339','Pantitlan','1','Metro','3')";
		db.execSQL(query);
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO +") VALUES ('Cuatro Caminos','-99.215601','19.459734','Panteones','2','Metro','0'), ('Panteones','-99.203199','19.458844','Tacuba','2','Metro','1'), ('Tacuba','-99.18923','19.459329','Cuitlahuac','2','Metro','2'), "+
		"('Cuitlahuac','-99.182127','19.457468','Popotla','2','Metro','1'), ('Popotla','-99.174724','19.452126','Colegio militar','2','Metro','1'), ('Colegio militar','-99.171999','19.44897','Normal','2','Metro','1'), "+
		"('Normal','-99.167493','19.44466','San cosme','2','Metro','1'), ('San cosme','-99.161099','19.441908','Revolucion','2','Metro','1'), ('Revolucion','-99.155563','19.439683','Hidalgo','2','Metro','1'), "+
		"('Hidalgo','-99.14713','19.438104','Bellas artes','2','Metro','2'), ('Bellas artes','-99.141957','19.436192','Allende','2','Metro','2'), ('Allende','-99.137452','19.435474','Zocalo','2','Metro','1'), "+
		"('Zocalo','-99.13256','19.433005','Pino suarez','2','Metro','1'), ('Pino Suarez','-99.132903','19.425822','San antonio abad','2','Metro','2'), ('San antonio abad','-99.134985','19.416391','Chabacano','2','Metro','1'), "+
		"('Chabacano','-99.135757','19.408519','Viaducto','2','Metro','2'), ('Viaducto','-99.137002','19.400808','Xola','2','Metro','1'), ('Xola','-99.137689','19.395282','Villa de cortes','2','Metro','1'), "+
		"('Villa de cortes','-99.138869','19.387631','Nativitas','2','Metro','1'), ('Nativitas','-99.140199','19.379535','Portales','2','Metro','1'), ('Portales','-99.141487','19.370041','Ermita','2','Metro','1'), "+
		"('Ermita','-99.142903','19.361883','General anaya','2','Metro','2'), ('General anaya','-99.145113','19.35336','Tasqueña','2','Metro','1'), ('Tasqueña','-99.142667','19.344128','Tasqueña','2','Metro','0')";
		db.execSQL(query);
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO +") VALUES ('Indios verdes','-99.1196','19.495318','Deportivo 18 de marzo','3','Metro','0'), ('Deportivo 18 de marzo','-99.126638','19.483767','Potrero','3','Metro','2'), ('Potrero','-99.132281','19.47695','La raza','3','Metro','1'), "+
		"('La raza','-99.137002','19.470254','Tlatelolco','3','Metro','2'), ('Tlatelolco','-99.142645','19.45506','Guerrero','3','Metro','1'), ('Guerrero','-99.145413','19.445105','Hidalgo','3','Metro','2'), "+
		"('Hidalgo','-99.14713','19.438104','Juarez','3','Metro','2'), ('Juarez','-99.147945','19.433369','Balderas','3','Metro','1'), ('Balderas','-99.149147','19.42744','Niños heroes','3','Metro','2'), "+
		"('Niños heroes','-99.150542','19.420155','Hospital general','3','Metro','1'), ('Hospital general','-99.153932','19.413619','Centro medico','3','Metro','1'), ('Centro medico','-99.155241','19.406677','Etiopia','3','Metro','2'), "+
		"('Etiopia','-99.156314','19.395525','Eugenia','3','Metro','1'), ('Eugenia','-99.157558','19.385506','Division del norte','3','Metro','1'), ('Division del norte','-99.159125','19.379879','Zapata','3','Metro','1'), "+
		"('Zapata','-99.165047','19.370729','Coyoacan','3','Metro','2'), ('Coyoacan','-99.170562','19.361701','Viveros','3','Metro','1'), ('Viveros','-99.176291','19.353603','M.A. de quevedo','3','Metro','1'), "+
		"('M.A. de quevedo','-99.180969','19.346537','Copilco','3','Metro','1'), ('Copilco','-99.176613','19.335887','Universidad','3','Metro','1'), ('Universidad','-99.173866','19.324427','Universidad','3','Metro','0')";
		db.execSQL(query);

 		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Martin carrera','-99.104408','19.484981','Talisman','4','Metro','3'), ('Talisman','-99.108334','19.473956','Bondojito','4','Metro','1'), ('Bondojito','-99.111875','19.464731','Consulado','4','Metro','1'), "+
		"('Consulado','-99.114407','19.458054','Canal del norte','4','Metro','2'), ('Canal del norte','-99.116186','19.449213','Morelos','4','Metro','1'), ('Morelos','-99.118377','19.439035','Candelaria','4','Metro','2'), "+
		"('Candelaria','-99.119385','19.428978','Fray servando','4','Metro','2'), ('Fray servando','-99.120317','19.4214','Jamaica','4','Metro','1'), ('Jamaica','-99.121853','19.409207','Santa anita','4','Metro','2'),"+
		"('Santa anita','-99.121776','19.402822','Santa anita','4','Metro','3')";
		db.execSQL(query);
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Pantitlan','-99.072157','19.415339','Hangares','5','Metro','3'), ('Hangares','-99.087435','19.424344','Terminal aerea','5','Metro','1'), ('Terminal aerea','-99.087478','19.433592','Oceania','5','Metro','1'), "+
		"('Oceania','-99.087423','19.445631','Aragon','5','Metro','2'), ('Aragon','-99.09652','19.450973','Eduardo molina','5','Metro','1'), ('Eduardo molina','-99.105436','19.451064','Consulado','5','Metro','1'), "+
		"('Consulado','-99.114407','19.458054','Valle gomez','5','Metro','2'), ('Valle gomez','-99.119364','19.458823','Misterios','5','Metro','1'), ('Misterios','-99.130736','19.463335','La raza','5','Metro','1'), "+
		"('La raza','-99.137002','19.470254','Autobuses del norte','5','Metro','2'), ('Autobuses del norte','-99.140757','19.479014','Instituto del petroleo','5','Metro','1'), ('Instituto del petroleo','-99.14492','19.489634','Politecnico','5','Metro','2'), "+
		"('Politecnico','-99.137002','19.470254','Politecnico','5','Metro','0')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('El rosario','-99.200238','19.504137','Tezozomoc','6','Metro','3'), ('Tezozomoc','-99.196311','19.495136','Azcapotzalco','6','Metro','1'), ('Azcapotzalco','-99.186505','19.491009','Ferreria','6','Metro','3'), "+
		"('Ferreria','-99.174016','19.490989','Norte 45','6','Metro','1'), ('Norte 45','-99.16273','19.488724','Vallejo','6','Metro','1'), ('Vallejo','-99.155692','19.490524','Instituto del petroleo','6','Metro','3'), "+
		"('Instituto del petroleo','-99.14492','19.489634','Lindavista','6','Metro','2'), ('Norte 45','-99.16273','19.488724','Vallejo','6','Metro','1'), ('Vallejo','-99.155692','19.490524','Instituto del petroleo','6','Metro','3'), "+
		"('Lindavista','-99.134706','19.487712','Deportivo 18 de marzo','6','Metro','2'), ('Deportivo 18 de marzo','-99.126638','19.483767','La villa-basilica','6','Metro','2'), ('La villa-basilica','-99.117969','19.481522','Martin carrera','6','Metro','1'), "+
		"('Martin carrera','-99.104408','19.484981','Martin carrera','6','Metro','3')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('El rosario','-99.200238','19.504137','Aquiles serdan','7','Metro','3'), ('Aquiles serdan','-99.19483','19.490483','Camarones','7','Metro','1'), ('Camarones','-99.190217','19.479216','Refineria','7','Metro','1'), "+
		"('Refineria','-99.190753','19.469971','Tacuba','7','Metro','1'), ('Tacuba','-99.18923','19.459329','San joaquin','7','Metro','2'), ('San joaquin','-99.191397','19.444923','Polanco','7','Metro','1'), "+
		"('Polanco','-99.191186','19.433572','Auditorio','7','Metro','1'), ('Auditorio','-99.192254','19.425538','Constituyentes','7','Metro','1'), ('Constituyentes','-99.191353','19.411929','Tacubaya','7','Metro','1'), "+
		"('Tacubaya','-99.188868','19.402083','San pedro de los pinos','7','Metro','2'), ('San pedro de los pinos','-99.186053','19.391244','San antonio','7','Metro','1'), ('San antonio','-99.186364','19.384788','Mixcoac','7','Metro','1'), "+
		"('Mixcoac','-99.187834','19.375912','Barranca del muerto','7','Metro','2'), ('Barranca del muerto','-99.189647','19.360739','Barranca del muerto','7','Metro','0')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Garibaldi','-99.139277','19.442738','Bellas artes','8','Metro','3'), ('Bellas artes','-99.141957','19.436192','San juan de letran','8','Metro','2'), ('San juan de letran','-99.141517','19.431224','Salto del agua','8','Metro','1'), "+
		"('Salto del agua','-99.141672','19.428391','Doctores','8','Metro','2'), ('Doctores','-99.143341','19.421633','Obrera','8','Metro','1'), ('Obrera','-99.144264','19.413447','Chabacano','8','Metro','1'), "+
		"('Chabacano','-99.135757','19.408519','La viga','8','Metro','2'), ('La viga','-99.126347','19.406495','Santa anita','8','Metro','1'), ('Santa anita','-99.121776','19.402822','Coyuya','8','Metro','2'), "+
		"('Coyuya','-99.113536','19.398511','Iztacalco','8','Metro','1'), ('Iztacalco','-99.112378','19.388492','Apatlaco','8','Metro','1'), ('Apatlaco','-99.109223','19.379302','Aculco','8','Metro','1'), "+
		"('Aculco','-99.107775','19.373796','Escuadron 201','8','Metro','1'), ('Escuadron 201','-99.109245','19.365203','Atlalilco','8','Metro','1'), ('Atlalilco','-99.101315','19.356113','Iztapalapa','8','Metro','2'), "+
		"('Iztapalapa','-99.093464','19.357935','Cerro de la estrella','8','Metro','1'), ('Cerro de la estrella','-99.085525','19.356073','UAM-I','8','Metro','1'), ('UAM-I','-99.074753','19.350667','Constitucion de 1917','8','Metro','1'), "+
		"('Constitucion de 1917','-99.063883','19.34597','Constitucion de 1917','8','Metro','1')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Tacubaya','-99.188868','19.402083','Patriotismo','9','Metro','3'), ('Patriotismo','-99.178841','19.406212','Chilpancingo','9','Metro','1'), ('Chilpancingo','-99.168502','19.406212','Centro medico','9','Metro','1'), "+ 
		"('Centro medico','-99.155241','19.406677','Lazaro cardenas','9','Metro','2'), ('Lazaro cardenas','-99.144898','19.407001','Chabacano','9','Metro','1'), ('Chabacano','-99.135757','19.408519','Jamaica','9','Metro','1'), "+ 
		"('Jamaica','-99.121853','19.409207','Mixiuhca','9','Metro','2'), ('Mixiuhca','-99.112669','19.408741','Velodromo','9','Metro','1'), ('Velodromo','-99.103237','19.408731','Ciudad deportiva','9','Metro','1'), "+ 
		"('Ciudad deportiva','-99.091168','19.407992','Puebla','9','Metro','1'), ('Puebla','-99.082478','19.407244','Pantitlan','9','Metro','1'), ('Pantitlan','-99.072157','19.415339','Pamtitlan','9','Metro','3')"; 
		db.execSQL(query);
		

 		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Pantitlan','-99.072157','19.415339','Agricola oriental','A','Metro','3'), ('Agricola oriental','-99.069968','19.404896','Canal de san juan','A','Metro','1'), ('Canal de san juan','-99.059389','19.398642','Tepalcates','A','Metro','1'), "+ 
		"('Tepalcates','-99.0464','19.39126','Guelatao','A','Metro','1'), ('Guelatao','-99.035366','19.384869','Peñon viejo','A','Metro','1'), ('Peñon viejo','-99.017053','19.373361','Acatitla','A','Metro','3'), "+ 
		"('Acatitla','-99.006003','19.364373','Santa marta','A','Metro','1'), ('Santa marta','-98.995617','19.360203','Los reyes','A','Metro','1'), ('Los reyes','-98.976928','19.359049','La paz','A','Metro','1'), "+ 
		"('La paz','-98.960886','19.350799','La paz','A','Metro','0')";
 		db.execSQL(query);

 		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Ciudad azteca','-99.02755','19.534554','Plaza aragon','B','Metro','0'), ('Plaza aragon','-99.030172','19.528482','Olimpica','B','Metro','1'), ('Olimpica','-99.033833','19.521713','Ecatepec','B','Metro','1'), "+ 
		"('Ecatepec','-99.036044','19.515281','Muzquiz','B','Metro','1'), ('Muzquiz','-99.041987','19.501649','Rio de los remedios','B','Metro','1'), ('Rio de los remedios','-99.046663','19.490767','Impulsora','B','Metro','1'), "+ 
		"('Impulsora','-99.048852','19.486013','Nezahualcoyotl','B','Metro','1'), ('Nezahualcoyotl','-99.05454','19.473066','Villa de aragon','B','Metro','1'), ('Villa de aragon','-99.061342','19.461696','Bosque de aragon','B','Metro','1'), "+ 
		"('Bosque de aragon','-99.069303','19.458095','Deportivo oceania','B','Metro','1'), ('Deportivo oceania','-99.05454','19.473066','Oceania','B','Metro','1'), ('Oceania','-99.087047','19.445621','Romero rubio','B','Metro','2'), "+ 
		"('Romero rubio','-99.094332','19.440775','R. flores magon','B','Metro','1'), ('R. flores magon','-99.103788','19.436708','San lazaro','B','Metro','1'), ('San lazaro','-99.114772','19.430233','Morelos','B','Metro','2'), "+ 
		"('Morelos','-99.118377','19.439035','Tepito','B','Metro','2'), ('Tepito','-99.12331','19.442515','Lagunilla','B','Metro','1'), ('Lagunilla','-99.131573','19.443568','Garibaldi','B','Metro','2'), "+ 
		"('Garibaldi','-99.139277','19.442738','Guerrero','B','Metro','2'), ('Guerrero','-99.145413','19.445105','Buenavista','B','Metro','2'), ('Buenavista','-99.152988','19.446785','Buenavista','B','Metro','0')"; 
		db.execSQL(query);
		
		
		 query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Mixcoac','-99.187834','19.375912','Insurgentes sur','12','Metro','3'), ('Insurgentes sur','-99.162782','19.42394','Hospital 20 de noviembre','12','Metro','1'), ('Hospital 20 de noviembre','-99.17093','19.371964','Zapata','12','Metro','1'), "+ 
		"('Zapata','-99.165047','19.370729','Parque de los venados','12','Metro','2'), ('Parque de los venados','-99.158738','19.371337','Eje central','12','Metro','1'), ('Eje central','-99.151443','19.361276','Ermita','12','Metro','1'), "+ 
		"('Ermita','-99.142903','19.361883','Mexicaltzingo','12','Metro','2'), ('Mexicaltzingo','-99.122098','19.357713','Atlalilco','12','Metro','1'), ('Atlalilco','-99.101315','19.356113','Culhuacan','12','Metro','2'), "+ 
		"('Culhuacan','-99.109343','19.337264','San andres tomatlan','12','Metro','1'), ('San andres tomatlan','-99.104386','19.328801','Lomas estrella','12','Metro','1'), ('Lomas estrella','-99.101315','19.356113','Calle 11','12','Metro','1'), "+ 
		"('Calle 11','-99.085804','19.320458','Periferico oriente','12','Metro','1'), ('Periferico oriente','-99.07441','19.317542','Tezonco','12','Metro','1'), ('Tezonco','-99.065355','19.306283','Olivos','12','Metro','1'), "+ 
		"('Olivos','-99.059261','19.303853','Nopalera','12','Metro','1'), ('Nopalera','-99.04585','19.300005','Zapotitlan','12','Metro','1'), ('Zapotitlan','-99.034305','19.296988','Tlaltenco','12','Metro','1'), "+ 
		"('Tlaltenco','-99.024049','19.294416','Tlahuac','12','Metro','1'), ('Tlahuac','-99.014672','19.286031','Tlahuac','12','Metro','0')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Indios verdes','-99.119759','19.496622','Deportivo 18 de marzo','1','Metro Bus','0'), ('Deportivo 18 de marzo','-99.124308','19.486498','Euzkaro','1','Metro Bus','1'), ('Euzkaro','-99.127784','19.482574','Potrero','1','Metro Bus','1'), "+ 
		"('Potrero','-99.132451','19.476748','La raza','1','Metro Bus','1'), ('La raza','-99.138803','19.46899','Circuito','1','Metro Bus','1'), ('Circuito','-99.144028','19.462586','San simon','1','Metro Bus','1'), "+ 
		"('San simon','-99.146527','19.459764','Manuel gonzalez','1','Metro Bus','1'), ('Manuel gonzalez','-99.149188','19.457013','Buenavista','1','Metro Bus','1'), ('Buenavista','-99.153051','19.447088','El chopo','1','Metro Bus','2'), "+ 
		"('El chopo','-99.15555','19.440563','Revolucion','1','Metro Bus','1'), ('Revolucion','-99.155529','19.440533','Plaza de la republica','1','Metro Bus','1'), ('Plaza de la republica','-99.157256','19.436314','Reforma','1','Metro Bus','1'), "+ 
		"('Reforma','-99.158812','19.433228','Hamburgo','1','Metro Bus','1'), ('Hamburgo','-99.161076','19.427906','Glorieta insurgentes','1','Metro Bus','1'), ('Glorieta insurgentes','-99.163275','19.424253','Durango','1','Metro Bus','1'), "+ 
		"('Durango','-99.164037','19.420004','Alvaro obregon','1','Metro Bus','1'), ('Alvaro obregon','-99.164842','19.417008','Sonora','1','Metro Bus','1'), ('Sonora','-99.16629','19.413275','Campeche','1','Metro Bus','1'), "+ 
		"('Campeche','-99.167352','19.409814','Chilpancingo','1','Metro Bus','1'), ('Chilpancingo','-99.168522','19.406586','Nuevo leon','1','Metro Bus','1'), ('Nuevo leon','-99.169991','19.401992','La piedad','1','Metro Bus','2'), "+ 
		"('La piedad','-99.17247','19.393785','Poliforum','1','Metro Bus','1'), ('Poliforum','-99.172416','19.393744','Napoles','1','Metro Bus','1'), ('Napoles','-99.173671','19.389959','Col. del valle','1','Metro Bus','1'), "+ 
		"('Col. del valle','-99.175334','19.385688','Cd. de los deportes','1','Metro Bus','1'), ('Cd. de los deportes','-99.176064','19.382723','Parque hundido','1','Metro Bus','1'), ('Parque hundido','-99.177266','19.379454','Felix cuevas','1','Metro Bus','1'), "+ 
		"('Feliz cuevas','-99.178757','19.374393','Rio churubusco','1','Metro Bus','1'), ('Rio churubusco','-99.180484','19.369221','Teatro insurgentes','1','Metro Bus','1'), ('Teatro insurgentes','-99.181761','19.365112','Jose maria velasco','1','Metro Bus','1'), "+ 
		"('Jose maria velasco','-99.182727','19.374393','Francia','1','Metro Bus','1'), ('Francia','-99.184014','19.358452','Olivo','1','Metro Bus','1'), ('Olivo','-99.185259','19.354848','Altavista','1','Metro Bus','1'), "+ 
		"('Altavista','-99.186289','19.351386','La bombilla','1','Metro Bus','1'), ('La bombilla','-99.187791','19.346709','Doctor galvez','1','Metro Bus','1'), ('Doctor galvez','-99.189904','19.340646','Ciudad universitaria','1','Metro Bus','1'), "+ 
		"('Ciudad universitaria','-99.186289','19.351386','Centro cultural universitario','1','Metro Bus','1'), ('Centro cultural universitario','-99.187437','19.314738','Perisur','1','Metro Bus','1'), ('Perisur','-99.189904','19.340646','Villa olimpica','1','Metro Bus','1'), "+ 
		"('Villa olimpica','-99.185505','19.29955','Corregidora','1','Metro Bus','1'), ('Corregidora','-99.187437','19.314738','Ayuntamiento','1','Metro Bus','1'), ('Ayuntamiento','-99.177394','19.292613','Fuentes brotantes','1','Metro Bus','1'), "+ 
		"('Fuentes brotantes','-99.174433','19.28839','Santa ursula','1','Metro Bus','1'), ('Santa ursula','-99.175549','19.283752','La joya','1','Metro Bus','1'), ('La joya','-99.17012','19.280461','El caminero','1','Metro Bus','1'), "+ 
		"('El caminero','-99.169208','19.279398','El caminero','1','Metro Bus','0')";
		db.execSQL(query);
		
		
		query = "INSERT INTO " + TABLE_ESTACIONES + " (" + ESTACIONES_NOMBRE + ", " + ESTACIONES_LONGITUD + ", " + ESTACIONES_LATITUD + ", " + ESTACIONES_ESIGUIENTE + ", "+ ESTACIONES_NUMERO + 
				", "+ ESTACIONES_TRANSPNOMBRE+", "+ESTACIONES_TIPO + ") VALUES ('Tacubaya','-99.186804','19.401739','Antonio maceo','2','Metro Bus','0'), ('Antonio maceo','-99.18584','19.405058','De la salle','2','Metro Bus','1'), ('De la salle','-99.183823','19.407871','Patriotismo','2','Metro Bus','1'), "+ 
		"('Patriotismo','-99.186804','19.405746','Escandon','2','Metro Bus','1'), ('Escandon','-99.173866','19.404633','Nuevo leon','2','Metro Bus','1'), ('Nuevo leon','-99.169991','19.401992','Viaducto','2','Metro Bus','2'), "+ 
		"('Viaducto','-99.168137','19.401658','Amores','2','Metro Bus','1'), ('Amores','-99.164167','19.397124','Etiopia','2','Metro Bus','1'), ('Etiopia','-99.155799','19.396031','Doctor vertiz','2','Metro Bus','2'), "+ 
		"('Doctor vertiz','-99.1514','19.395748','Centro SCOP','2','Metro Bus','1'), ('Centro SCOP','-99.146465','19.395464','Alamos','2','Metro Bus','1'), ('Alamos','-99.142624','19.394797','Xola','2','Metro Bus','2'), "+ 
		"('Xola','-99.140027','19.394452','Las americas','2','Metro Bus','1'), ('Las americas','-99.134813','19.393663','Andres molina enriquez','2','Metro Bus','1'), ('Andres molina enriquez','-99.129127','19.398075','La viga','2','Metro Bus','1'), "+ 
		"('La viga','-99.124063','19.398258','Coyuya','2','Metro Bus','1'), ('Coyuya','-99.117111','19.39846','Canela','2','Metro Bus','1'), ('Canela','-99.109472','19.398217','Tlacotal','2','Metro Bus','1'), "+ 
		"('Tlacotal','-99.103592','19.397468','Goma','2','Metro Bus','1'), ('Goma','-99.099472','19.397144','Iztacalco','2','Metro Bus','1'), ('Iztacalco','-99.096082','19.396821','UPIICSA','2','Metro Bus','1'), "+ 
		"('UPIICSA','-99.090503','19.394129','El rodeo','2','Metro Bus','1'), ('El rodeo','-99.087478','19.392145','Rio tecolutla','2','Metro Bus','1'), ('Rio tecolutla','-99.0831','19.38921','Rio mayo','2','Metro Bus','1'), "+ 
		"('Rio mayo','-99.080268','19.387308','Rojo gomez','2','Metro Bus','1'), ('Rojo gomez','-99.076212','19.384454','Del moral','2','Metro Bus','1'), ('Del moral','-99.070934','19.384393','Leyes de reforma','2','Metro Bus','1'), "+ 
		"('Leyes de reforma','-99.065891','19.383664','CCH oriente','2','Metro Bus','1'), ('CCH oriente','-99.060763','19.383077','Const. de apatzingan','2','Metro Bus','1'), ('Const. de apatzingan','-99.059926','19.389514','Canal de san juan','2','Metro Bus','1'), "+ 
		"('Canal de san juan','-99.056085','19.396659','Nicolas bravo','2','Metro Bus','1'), ('Nicolas bravo','-99.051321','19.394007','Tepalcates','2','Metro Bus','1'), ('Tepalcates','-99.047373','19.391012','Tepalcates','2','Metro Bus','0')";

		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		String DROP = " DROP TABLE IF EXISTS ";
		String dropTables =DROP + TABLE_ESTACIONES + DROP + TABLE_LINEA + DROP + TABLE_MTRANSPORTE;
		db.execSQL(dropTables);
        onCreate(db);

	}
	
	public SQLiteDatabase conectar()
	{
		SQLiteDatabase db= this.getWritableDatabase();;
	    db.execSQL("PRAGMA foreign_keys=ON;");
	
		return db;
	}
	
	public void desconectar()
	{
		this.close();
	}
	
	public Cursor leerDatos(String query)
	{
	
		return this.getReadableDatabase().rawQuery(query,null);
	}
	
}
