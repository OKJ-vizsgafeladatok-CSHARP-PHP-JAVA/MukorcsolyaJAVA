import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static List<Mukorcsolya> beolvas(String fajl) {
		List<Mukorcsolya> lista = new ArrayList<Mukorcsolya>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get(fajl));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split(";");
				Mukorcsolya o = new Mukorcsolya(split[0], split[1], Double.parseDouble(split[2]),
						Double.parseDouble(split[3]), Integer.parseInt(split[4]));
				lista.add(o);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Hiba a fájl beolvasásakor" + fajl);
		}
		return lista;
	}

	public static double osszPontszam(String versenyzo) {
		double ossz=0.0;
		List<Mukorcsolya> a=beolvas("rovidprogram.csv");
		List<Mukorcsolya> b=beolvas("donto.csv");
		for (Mukorcsolya mu : a) {
			if(mu.getNev().equals(versenyzo)) {
				ossz=mu.getTechnikai()+mu.getKomponens()-mu.getLevonas();
				//ossz-=mu.getLevonas();
			}
		}
		for (Mukorcsolya mu : b) {
			if(mu.getNev().equals(versenyzo)) {
				ossz+=mu.getTechnikai()+mu.getKomponens()-mu.getLevonas();
				//ossz-=mu.getLevonas();
			}
		}
		return ossz;
	}
	
	public static TreeMap<String, Double> rendez(Map<String,Double>lista){
		TreeMap<String,Double> rendezett=new TreeMap<String,Double>(new Comparator<String>() {
			public int compare(String o1,String o2) {
				int compare=lista.get(o2).compareTo(lista.get(o1));
				if(compare==0) {
					return 1;
				}
				else 
				{
					return compare;
				}
			}
		});
		rendezett.putAll(lista);
		return rendezett;
	}
	
	public static Scanner sc=new Scanner(System.in); 
	public static void main(String[] args) throws IOException {
		List<Mukorcsolya> a=beolvas("rovidprogram.csv");
		List<Mukorcsolya> b=beolvas("donto.csv");
		
		System.out.println("2. feladat");
		System.out.println("\tA rövidprogramban "+a.size()+" induló volt");
		System.out.println("3. feladat");
		String valasz="A magyar versenyző nem jutott be a kűrbe";
		boolean magyar=false;
		for (Mukorcsolya m : b) {
			if(m.getOrszag().equals("HUN")) {
				valasz="A magyar versenyző bejutott a kűrbe";
			}
		}
		System.out.println("\t"+valasz);
		System.out.println("5. feladat");
		System.out.print("\tKérem a versenyző nevét! ");
		String beker=sc.next();
		Mukorcsolya kivalasztott=null;
		boolean ez=false;
		for (Mukorcsolya m : a) {
			if(m.getNev().contains(beker)) {
				kivalasztott=m;
				ez=true;
			}
		}
		if(!ez) {
			System.out.println("\tIlyen nevű versenyző nem volt");
		}
		
		System.out.println("6. feladat: ");
		System.out.println("\tA versenyző összpontszáma: "+osszPontszam(kivalasztott.getNev()));
			
		//7. feladat: 
		HashMap<String,Integer> c=new HashMap<String, Integer>();
		for (Mukorcsolya m : b) {
			c.merge(m.getOrszag(), 1, Integer::sum);
		}
		for (Entry<String,Integer> m : c.entrySet()) {
			if(m.getValue()>1) 
			{ 
				System.out.println("\t"+m.getKey()+": "+m.getValue()+" versenyző");
			}
		}
		//8. feladat: 
		String fajlba="";
		TreeMap<String,Double>d=new TreeMap<String,Double>();
		double osszP=0.0;
		String nev="";
		for (int i = 0; i < a.size(); i++) {
			osszP+=osszPontszam(a.get(i).getNev());
			nev=a.get(i).getNev();
			d.put(nev, osszP);
			osszP=0.0;
		}

		d=rendez(d);
		String neve="";
		int p=0;
		for (Entry<String,Double> m : d.entrySet()) {
			p++;
			neve=m.getKey();
			for (Mukorcsolya mf : b) {
				if(mf.getNev().contains(neve)) {
					fajlba+=p+";"+mf.getNev()+";"+mf.getOrszag()+";"+new DecimalFormat("0.0").format(m.getValue())+"\n";
				}
			}
			neve="";
		}
		System.out.println(fajlba);
	
		Files.write(Paths.get("vegeredmeny.csv"),fajlba.getBytes());
		
	}//end of main
}
