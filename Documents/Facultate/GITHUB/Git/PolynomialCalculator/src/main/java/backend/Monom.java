package backend;
public class Monom implements Comparable<Object>{
	private int coef;
	private int putere;
	private int marcaj;// marcaj daca a fost adaugat in noul polinom suma/diferenta 
	public Monom(int c , int p, int m)
	{
		this.coef=c;
		this.putere=p;
		this.marcaj=m;
	}
	public void setCoeficient(int c)
	{
		this.coef=c;
	}
	public int getCoeficient()
	{
		return this.coef;
	}	
	public void setPutere(int p)
	{
		this.putere=p;
	}
	public int getPutere()
	{
		return this.putere;
	}
	public String getMonom(String coef)
	{
		return coef +"x^"+putere;
	}
	public String getMonom()
	{
		return coef +"x^"+putere;
	}
	public void setMarcaj(int m)
	{
		this.marcaj=m;
	}
	public int getMarcaj()
	{
		return this.marcaj;
	}
	public Monom adunare(Monom m1 , Monom  m2)
	{
		int cm1=m1.getCoeficient();
		int pm1=m1.getPutere();
		int cm2=m2.getCoeficient();
		int pm2=m2.getPutere();
		Monom suma=new Monom(0,0,0);
		if(pm1==pm2)
		{
			suma.setPutere(pm1);
			suma.setCoeficient(cm1+cm2);
		}
		return suma;
	}
	public Monom scadere12(Monom  m2)
	{
		Monom m1=this;
		int cm1=m1.getCoeficient();
		int pm1=m1.getPutere();
		int cm2=m2.getCoeficient();
		int pm2=m2.getPutere();
		Monom dif=new Monom(0,0,0);
		if(pm1==pm2)
		{
			dif.setPutere(pm1);
			dif.setCoeficient(cm1-cm2);
		}
		return dif;
	}
	public Monom inmultire(Monom m1 , Monom m2)
	{
		int cm1=m1.getCoeficient();
		int pm1=m1.getPutere();
		int cm2=m2.getCoeficient();
		int pm2=m2.getPutere();
		Monom produs=new Monom(0,0,0);
		produs.setCoeficient(cm1*cm2);
		produs.setPutere(pm1+pm2);
		return produs;
	}
	// modific monomul curent
	public Monom derivare()
	{
		Monom derivat=new Monom(0,0,0);
		int p=this.putere;
		int c=this.coef;
		this.setPutere(p-1);
		this.setCoeficient(c*p);
		return derivat;
	}
	// modific monomul curent
	public Monom integrare()
	{
		Monom integrat=new Monom(0,0,0);
		int p=this.putere;
		int c=this.coef;
		this.setPutere(p+1);
		this.setCoeficient(c/(p+1));
		return integrat;
	}
	public int compareTo(Object o) {
		int putereObiect = ((Monom)o).getPutere();// se face ordonarea dupa puterile monoamelor
		return putereObiect-this.putere;
	}
	
	
}
