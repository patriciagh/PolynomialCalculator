package backend;
import java.util.*;
public class Polinom {
	private int grad;	
	private ArrayList<Monom> monoame=new ArrayList<Monom>();
	public Polinom(int g,ArrayList<Monom> m)
	{
		this.grad=g;
		this.monoame=m;
	}
	public void setGrad(int g)
	{
		this.grad=g;
	}
	public int getGrad()
	{
		return this.grad;
	}
	
	public void setMonoame(ArrayList<Monom> m)
	{
		this.monoame=m;
	}
	public ArrayList<Monom> getMonoame()
	{
		return this.monoame;
	}
	public int verificareNull()
	{
		int ok=1;
		for(Monom m : this.monoame)		
			if(m.getCoeficient()!=0)			
				ok=0;					
		return ok;
		// daca se gaseste un coeficient diferit de 0 , atunci polinomul nu e null si ok=0;
		
	}
	public void adaugareMonom(Monom m)
	{
		this.monoame.add(m);
	}
	public void afisarePolinom()
	{
		this.aranjarePolinom(this);
		this.ordonarePolinom(this);
		for( Monom m : monoame)// FOREACH
			if(m.equals(monoame.get(0)))
				System.out.print(m.getMonom());
			else
				System.out.print(" + "+m.getMonom());			
		System.out.println();
	}
	public Monom cautaMaxim(Polinom p)
	{
		Monom maxim=new Monom(0,0,0);
		for(Monom m : p.getMonoame())
		{
			if(m.getPutere() > maxim.getPutere()) 
			{
				maxim.setPutere(m.getPutere());
				maxim.setCoeficient(m.getCoeficient());
			}
					}
		return maxim;
	}
	
	public Polinom adunare (Polinom p2)
	{
		Polinom p1=this;
		
		if(p1.grad>=p2.grad)
		{
			Polinom suma=new Polinom(p1.grad,p1.monoame);
			ArrayList<Monom> vector= new ArrayList<Monom>(p1.grad);			
			for(Monom m1: p1.monoame )			
				for(Monom m2: p2.monoame)				
					if(m1.getPutere()==m2.getPutere())
					{	vector.add(m1.adunare(m1, m2));
						m1.setMarcaj(1);
						m2.setMarcaj(1);
					}
			for(int i =0;i<vector.size();i++)						// Stergere coeficienti cu 0
			{
				Monom m=(Monom)vector.get(i);
				if(m.getCoeficient()==0)
					vector.remove(i);
			}
			for(Monom m : p1.monoame)			
				if(m.getMarcaj()==0)
					vector.add(m);			
			for(Monom m : p2.monoame)			
				if(m.getMarcaj()==0)
					vector.add(m);			
			suma.setMonoame(vector);
			suma.aranjarePolinom(suma);
			suma.ordonarePolinom(suma);
			return suma;
		}
		else
		{
			Polinom suma=new Polinom(p2.grad,p2.monoame);
			ArrayList<Monom> vector= new ArrayList<Monom>(p2.grad);			
			for(Monom m2: p2.monoame )			
				for(Monom m1: p1.monoame)				
					if(m1.getPutere()==m2.getPutere())
					{	vector.add(m2.adunare(m1, m2));
						m1.setMarcaj(1);
						m2.setMarcaj(1);
					}
			for(int i =0;i<vector.size();i++)						// Stergere coeficienti cu 0
			{
				Monom m=(Monom)vector.get(i);
				if(m.getCoeficient()==0)
					vector.remove(i);
			}
			for(Monom m : p1.monoame)
				if(m.getMarcaj()==0)
					vector.add(m);
			for(Monom m : p2.monoame)
				if(m.getMarcaj()==0)
					vector.add(m);
			suma.setMonoame(vector);
			suma.aranjarePolinom(suma);
			suma.ordonarePolinom(suma);
			return suma;
		}
	}
	
	public Polinom scadere( Polinom p2)
	{
		Polinom p1=this;
		ArrayList<Monom> coef=new ArrayList<Monom>();// coeficientii finali ai diferentei
		ArrayList<Monom> coefP1=new ArrayList<Monom>();
		ArrayList<Monom> coefP2=new ArrayList<Monom>();
		for(Monom m : p1.getMonoame())
		{
			coefP1.add(new Monom(m.getCoeficient(),m.getPutere(),m.getMarcaj()));
		}
		for(Monom m : p2.getMonoame())
		{
			coefP2.add(new Monom(m.getCoeficient(),m.getPutere(),m.getMarcaj()));
		}
		
		Polinom dif=new Polinom(0,coef);
		
		for(Monom m1 : coefP1)
		{
			for(Monom m2 : coefP2)
			{
				if(m1.getPutere()==m2.getPutere())
				{
					coef.add(m1.scadere12(m2));
					m1.setMarcaj(1);//a fost adaugat
					m2.setMarcaj(1);
				}
			}
		}
		// sterg elementul daca are coeficient 0
		for(int i =0;i<coef.size();i++)
		{
			Monom m=(Monom)coef.get(i);
			if(m.getCoeficient()==0)
				coef.remove(i);
		}
		// adaug cu + ce am in p1
		for(Monom m : coefP1)
			if(m.getMarcaj()==0)
				coef.add(m);
		for(Monom m : coefP2)// cu semn - ce am in p2
			if(m.getMarcaj()==0)
			{
				int coeficient=m.getCoeficient();
				coeficient=(-1)*coeficient;
				m.setCoeficient(coeficient);
				coef.add(m);
			}
		
		dif.setMonoame(coef);
		dif.aranjarePolinom(dif);
		dif.ordonarePolinom(dif);
		if(dif.verificareNull()==1)
		{
			dif.adaugareMonom(new Monom(0,0,0));
			
		}
	return dif;
	}
	
	public Polinom inmultire(Polinom p2)
	{
		Polinom p1=this;
		p1.aranjarePolinom(p1);
		p2.aranjarePolinom(p2);
		ArrayList <Monom> coef= new ArrayList<Monom>();
		for(Monom m1 : p1.monoame)
			for(Monom m2 : p2.monoame)
			{
				Monom p=new Monom(0,0,0);
				p=m1.inmultire(m1, m2);
				coef.add(p);
			}
		Polinom produs=new Polinom(p1.grad+p2.grad,coef);
		produs.aranjarePolinom(produs);
		return produs;
	}
	
	public Polinom derivare()
	{
		ArrayList <Monom> coef=new ArrayList<Monom>();
		Polinom derivat=new Polinom(0,coef);
		for(Monom m : this.monoame)
			m.derivare();		
		derivat.setMonoame(monoame);		
		for(int i=0;i<derivat.monoame.size();i++)
			if(derivat.monoame.get(i).getCoeficient()==0 || derivat.monoame.get(i).getPutere()<0)			
				derivat.monoame.remove(i);
		derivat.setGrad(this.grad-1);	
		derivat.aranjarePolinom(derivat);
		derivat.ordonarePolinom(derivat);
		if(derivat.verificareNull()==1)
		{
			derivat.adaugareMonom(new Monom(0,0,0));
		}
		return derivat;
	}
	public Polinom integrare()
	{
		ArrayList <Monom> coef=new ArrayList<Monom>();
		Polinom integrat=new Polinom(0,coef);
		for(Monom m : this.monoame)
			m.integrare();
		
		integrat.setMonoame(monoame);
		integrat.aranjarePolinom(integrat);
		integrat.ordonarePolinom(integrat);
		if(integrat.verificareNull()==1)
		{
			integrat.adaugareMonom(new Monom(0,0,0));
		}
		return integrat;
	}
	
	public void aranjarePolinom(Polinom p)
	{
		int nr=0;
		p.ordonarePolinom(p);
		for(int i=0;i<p.getMonoame().size();i++)		
			for(int j=i+1;j<p.getMonoame().size();j++)		
				if(p.getMonoame().get(i).getPutere()==p.getMonoame().get(j).getPutere())				
					nr++;	
		
		while(nr>0)
		{
			for(int i=0;i<p.getMonoame().size();i++) 			
				for(int j=i+1;j<p.getMonoame().size();j++)				
					if(p.getMonoame().get(i).getPutere()==p.getMonoame().get(j).getPutere())
					{
						int suma=p.getMonoame().get(i).getCoeficient()+p.getMonoame().get(j).getCoeficient();
						p.getMonoame().get(i).setCoeficient(suma);
						p.getMonoame().remove(j);
					}			
			nr--;
		}
	}
	public void ordonarePolinom(Polinom p)
	{
		Collections.sort(p.getMonoame());
	}
	public String getPolinom()
	{
		String polinomString=new String("");
		for(Monom m : this.monoame)		
			if(m.getCoeficient()>0 && m.equals(monoame.get(0))==false)
				polinomString=polinomString+" + "+m.getMonom();			
			else			
				polinomString=polinomString+" "+m.getMonom();			
		
		return polinomString;
	}
	
}
