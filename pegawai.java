import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
class pegawai{
	public static String nama, jabatan;
	public static int jam_lembur, tidak_hadir;
	public static boolean is_lembur = false;
	public static void main(String args[]){
		Scanner scanMe = new Scanner(System.in);
		banner(scanMe);
	}
	public static void banner(Scanner scan){
		l("Nama : ");
		nama = scan.nextLine();
		l("Jabatan : ");
		jabatan = scan.nextLine();
		l("Lembur ? ( Y / N ) : ");
		String status = scan.nextLine();
		if( status.equals("Y") || status.equals("y")){
			is_lembur = true;
			l("Berapa jam ? Contoh : 1 ,2 3, 4, 5 ) : ");
			jam_lembur = scan.nextInt();
			l("Tidak hadir : ");
			tidak_hadir = scan.nextInt();
		}else{
			is_lembur = false;
			jam_lembur = 0;
			l("Tidak hadir : ");
			tidak_hadir = scan.nextInt();
		}
		

		//Finally time to break !
		//Yahari kore wa ii mono desu ne

		kalkulasi kalk = new kalkulasi();
		try{
			final String totalGaji = kalk.totalGaji(jabatan, is_lembur, jam_lembur, tidak_hadir);
			output(kalk.getGajiPokok(), kalk.getGajiLembur(), totalGaji, kalk.getPotongan());
		}catch(ParseException e){
			e.printStackTrace();
		}
	}	
	static void ln(String value){
		System.out.println(value);
	}
	static void l(String value){
		System.out.print(value);
	}
	public static void output(String gaji_pokok, String gaji_lembur, String gaji_total, String potongan){
		ln("--------------");
		ln("Nama : "+nama);
		ln("Jabatan : "+jabatan);
		ln("Gaji pokok : "+ gaji_pokok);
		ln("Gaji lembur : "+ gaji_lembur);
		ln("Potogan : "+ potongan);
		ln("--------------");
		ln("Total gaji : "+gaji_total);
		ln("--------------");
	}
}

class Gajian{
	String[] jabatan;
	String[] uang;
	public Gajian(){
		this.jabatan = new String[]{"direktur","wakil direktur","sekertaris","karyawan","lembur"};
		this.uang = new String[]{"6.500.000","5.500.000","4.000.000","3.000.000","150.000"};
	}
	public int toInt(String value){
		return Integer.parseInt(value);
	}
	public String[] getJabatan(){
		return this.jabatan;
	}
	public String[] getUang(){
		return this.uang;
	}
}

class kalkulasi extends Gajian{
	@Override
	public String[] getJabatan(){
		return super.getJabatan();
	}
	@Override
	public String[] getUang(){
		return super.getUang();
	}
	public String potongan;
	public String gaji_pokok;
	public String gaji_lembur;
	public String totalGaji(String jabatan, boolean is_lembur, int jam_lembur, int tidak_hadir) throws ParseException{
		//Bismillah
		String gaji = "";
        NumberFormat format = NumberFormat.getInstance();
		for(int i = 0; i < getJabatan().length; i++){
			if( jabatan.equals(getJabatan()[i]) ){
        		Number parse = format.parse(getUang()[i]);
        		gaji_pokok = getUang()[i];
        		if ( is_lembur ){
        			Number lemb = format.parse( getUang()[getUang().length-1] );
        			String lembs = String.valueOf( lemb );

        			if( tidak_hadir != 0 ){
        				potongan = String.valueOf ( (250000 * tidak_hadir ) );
        				gaji_lembur = String.valueOf( Integer.parseInt( lembs ) * jam_lembur );
        				gaji += String.valueOf( Integer.parseInt( lembs ) + Integer.parseInt( String.valueOf( getUang()[i].replace(".","") ) ) * jam_lembur - (250000 * tidak_hadir) );
        			}else{
        				gaji_lembur = String.valueOf( Integer.parseInt( lembs ) * jam_lembur );
        				gaji += String.valueOf( Integer.parseInt( lembs ) + Integer.parseInt( String.valueOf( getUang()[i].replace(".","") ) ) * jam_lembur);
        			}
        			break;
        		}else{
        			gaji_lembur = "0";
        			if ( tidak_hadir != 0 ){
        				gaji += String.valueOf(  Integer.parseInt( String.valueOf( parse ) ) - ( 250000 * tidak_hadir ) );
        			}else{
        				gaji += String.valueOf( parse );
        			}
        			break;
        		}
			}
		}

		return gaji = !gaji.isEmpty() ? gaji : "Null";
	}	
	public String getPotongan(){
		return potongan = potongan != null ? potongan : "0";
	}
	public String getGajiLembur(){
		return gaji_lembur = gaji_lembur != null ? gaji_lembur : "0";
	}
	public String getGajiPokok(){
		return gaji_pokok = gaji_pokok != null ? gaji_pokok : "0";
	}
}