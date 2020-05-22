package sample.Model;

import java.sql.Date;
import java.time.LocalDate;

public class OrderTable {
	int idordertable;
	String Commande, Quantity;
	LocalDate cdate;
	String orderNumber;
	//LocalDate cdate;
	LocalDate rdate;

	public OrderTable() {

	}

	public OrderTable(LocalDate chefOrderDa, LocalDate receptionDa, String commande, String quantity) {
			this.cdate = chefOrderDa;
			this.rdate = receptionDa;
			Commande = commande;
			Quantity = quantity;
		}

	public OrderTable(int idOrder, String orderNumber, LocalDate cdate, String commande, String quantity)
		{
			this.idordertable = idOrder;
			this.orderNumber = orderNumber;
			this.cdate = cdate;
			Commande = commande;
			Quantity = quantity;

		}


		public String getOrderNumber () {
			return orderNumber;
		}

		public void setOrderNumber (String orderNumber)
		{
			this.orderNumber = orderNumber;
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

	public OrderTable(Date idordertable, Date cdate, String commande, String quantity)
		{
		}

		public int getIdordertable ()
		{
			return idordertable;
		}

		public void setIdordertable ( int idordertable)
		{
			this.idordertable = idordertable;
		}

		public String getCommande ()
		{
			return Commande;
		}

		public void setCommande (String commande)
		{
			Commande = commande;
		}

		public String getQuantity ()
		{
			return Quantity;
		}

		public void setQuantity (String quantity)
		{
			Quantity = quantity;
		}

		public LocalDate getCdate ()
		{
			return cdate;
		}

		public void setCdate (LocalDate cdate)
		{
			this.cdate = cdate;
		}

		public LocalDate getRdate ()
		{
			return rdate;
		}

		public void setRdate (LocalDate rdate)
		{
			this.rdate = rdate;
		}
	}
