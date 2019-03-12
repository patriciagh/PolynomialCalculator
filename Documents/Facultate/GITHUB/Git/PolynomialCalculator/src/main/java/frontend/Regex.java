package frontend;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.*;

public class Regex {	

	public  static Polinom citirePolinom(String s)
	{
		ArrayList<Monom> monoame=new ArrayList<Monom>();
		Polinom p=new Polinom(0,monoame);
		Pattern pattern=Pattern.compile("([+-]?)([0-9]*?)[xX](\\^([0-9]+[1-9]*))?|([+-]?)([0-9]+[1-9]*)");
		Matcher m=pattern.matcher(s);	
		int count=0;
		while(m.find())
		{
			count++;
			System.out.println("Match number "+count); 
			System.out.println("0 : "+m.group(0));// tot numarul
			System.out.println("1 : "+m.group(1));// semn coeficient
			System.out.println("2 : "+m.group(2));// coeficient fara semn
			System.out.println("3 : "+m.group(3));//putere cu ^
			System.out.println("4 : "+m.group(4));// putere simpla
			System.out.println("5 : "+m.group(5));//semnul termenului liber
			System.out.println("6 : "+m.group(6));//termenul liber simplu
			System.out.println();
			String semn , coeficient ;
			semn=m.group(1);
			coeficient=m.group(2);
			//System.out.println("Semn : AA"+semn+"AA Coef : AA"+coeficient+"AA");
			// caz pentru termen liber
			if(semn==null && coeficient==null && m.group(5)!=null && m.group(6)!=null)
			{
				coeficient=m.group(6);
				semn=m.group(5);
			}
			// DACA NU E TERMEN LIBER (are x) , adica m5 si m6 sunt nule
			else
			{
				if((semn==null && coeficient==null) || (semn.equals("") && coeficient.equals("")))
				{
					// pentru x^2  , la inceput de rand
					coeficient="1";
					semn="+";
				}
				else
				{
					// are semn dar nu are coeficient : -x^3
					if((semn.equals("")==false) && (coeficient.equals("")==true))
					{
						coeficient="1";
					}
					// are semn si coeficient
					else
					{
						semn=m.group(1);
						coeficient=m.group(2);
					}
				}
			}
			
			// => semn si coeficient sunt stringurile care contin semnele si coeficientii monoamelor
			// Prelucrare putere
			String putere=new String("0");
			if(m.group(4)!=null)
			{
				putere=m.group(4);
			}
			else
			{
				if(m.group(5)==null && m.group(6)==null)
			{
					putere="1";
				}
				else
				{
					putere="0";
				}
			}
			
			//System.out.println("Semn :AA"+semn+"AA");
			//System.out.println("Coeficient : AA"+coeficient+"AA");
			//System.out.println("Putere : AA"+putere+"AA");			
		
			Monom mon=new Monom(0,0,0);
			int coefInt = 0 , putereInt=0 ;
			if(semn.equals("-"))
			{
				coefInt=Integer.parseInt(coeficient);
				coefInt=coefInt*(-1);
			}
			else
			{
				coefInt=Integer.parseInt(coeficient);
			}
			putereInt=Integer.parseInt(putere);
			//System.out.println("COEF INT : "+coefInt);
			//System.out.println("putere INT : "+putereInt);
			mon.setCoeficient(coefInt);
			mon.setPutere(putereInt);
			// Adaugare in lista
			monoame.add(mon);			
		}
		// afisare grad
		Monom maxim=p.cautaMaxim(p);
		p.setGrad(maxim.getPutere());
		p.ordonarePolinom(p);		
		return p;
	}
	public static void main(String[] args) {
		
		//Polinom p1=REGEX.citirePolinom("-x^2+2x+1");
		Polinom p1=citirePolinom("-4455x^2+2x^2+1");
		p1.afisarePolinom();
		//System.out.println("Afisare polinom p1 : ");
		//p1.afisarePolinom();
//		Polinom p2=REGEX.citirePolinom("-3x-6");
//		System.out.println("Afisare polinom p2 : ");
//		p2.afisarePolinom();
//		Polinom suma=p1.adunare(p2);// STERGE COEF 0
//		System.out.print("SUMA : ");
//		suma.afisarePolinom();
//		Polinom dif=p1.scadere(p2);//BLANA
//		System.out.print("DIF : ");
//		dif.afisarePolinom();
//		Polinom p1p2=p1.inmultire(p2);// merge BLANA
//		System.out.print("INMULTIRE : ");
//		p1p2.afisarePolinom();
//		System.out.print("P1 derivat : ");
//		p1.derivare();
//		p1.afisarePolinom();
//		System.out.print("P2 derivat : ");
//		p2.derivare();
//		p2.afisarePolinom();
//		System.out.print("P1 integrat : ");
//		p1.integrare();
//		p1.afisarePolinom();
		

		
	}
}
