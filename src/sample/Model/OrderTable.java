package sample.Model;

import java.time.LocalDate;

public class OrderTable
{
	int				idordertable;
	String		Commande, Quantity;
	LocalDate	cdate;
	LocalDate	rdate;

	public OrderTable(LocalDate chefOrderDa, LocalDate receptionDa, String commande, String quantity)
	{
		this.cdate = chefOrderDa;
		this.rdate = receptionDa;
		Commande = commande;
		Quantity = quantity;
	}

	public OrderTable(
		int idordertable,
		LocalDate cdate,
		LocalDate rdate,
		String commande,
		String quantity
	)
	{
		this.idordertable = idordertable;
		this.cdate = cdate;
		this.rdate = rdate;
		Commande = commande;
		Quantity = quantity;

	}

	public int getIdordertable()
	{
		return idordertable;
	}

	public void setIdordertable(int idordertable)
	{
		this.idordertable = idordertable;
	}

	public String getCommande()
	{
		return Commande;
	}

	public void setCommande(String commande)
	{
		Commande = commande;
	}

	public String getQuantity()
	{
		return Quantity;
	}

	public void setQuantity(String quantity)
	{
		Quantity = quantity;
	}

	public LocalDate getCdate()
	{
		return cdate;
	}

	public void setCdate(LocalDate cdate)
	{
		this.cdate = cdate;
	}

	public LocalDate getRdate()
	{
		return rdate;
	}

	public void setRdate(LocalDate rdate)
	{
		this.rdate = rdate;
	}
}
