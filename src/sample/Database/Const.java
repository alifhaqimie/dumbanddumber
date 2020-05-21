package sample.Database;
public class Const
{
	public static final String	Users_Table="userstable";
	//Column names
	public static final String	Users_FIRSTNAME="firstname";
	public static final String	Users_LASTNAME="lastname";
	public static final String	Users_USERNAME="username";
	public static final String	Users_PASSWORD="password";
	public static final String	Users_TYPE="type";
	public static final String	Users_ID="userId";

	//public static final String	Patients_Table="patientstable";
	//Column names
	//public static final String	Patients_FULLNAME="fullname";
	//public static final String	Patients_ETAT="Etatpatient";
	//public static final String	Patients_MENU="Menu";
	//public static final String	Patients_Regime="Regime";
	//public static final String	Patients_usersId="userId";


	public static final String	Patients_Table="patientstable";
	//Column names
	public static final String	Patients_FULLNAME="fullname";
	public static final String	Patients_ETAT="Etatpatient";
	public static final String	Patients_BREAKFAST="breakfast";
	public static final String	Patients_LUNCH="lunch";
	public static final String	Patients_DINNER="dinner";
	public static final String	Patients_Regime="Regime";
	public static final String	Patients_usersId="userId";

	public static final String Order_Table="ordertable";
	//Column names

	public static final String Order_id="idordertable";
	public static final String Order_date="Cdate";
	public static final String Order_rdate="Rdate";
	public static final String Order_commande="Commande";
	public static final String Order_quantity="Quantity";
	public static final String Order_number="orderNumber";

	public static final String Storage_Table="storagetable";
	//Column names

	public static final String Element_id="idelement";
	public static final String Storage_element="element";
	public static final String Storage_zone="storagezone";
	public static final String Storage_type="storagetype";

	public static final String Quantity_Table="quantitytable";
	//Column names

	public static final String Quantity_Element="element";
	public static final String Quantity_Date="date";
	public static final String Quantity_initial="quantiteinitial";
	public static final String Quantity_consumed="quantiteconsome";
	public static final String Quantity_ordered="quantitecommande";
	public static final String Quantity_present="quantitepresente";

	public static final String Breakfast_Table="breakfast";
	//Column names

	public static final String Breakfast_fruit1="fruit1";
	public static final String Breakfast_fruit2="fruit2";
	public static final String Breakfast_fruit3="fruit3";
	public static final String Breakfast_lait1="lait1";
	public static final String Breakfast_lait2="lait2";
	public static final String Breakfast_boisson="boisson";
	public static final String Breakfast_cereal1="cereal1";
	public static final String Breakfast_cereal2="cereal2";
	public static final String Breakfast_name="name";

	public static final String Lunch_Table="lunch";
	//Column names

	public static final String Lunch_gras1="gras";

	public static final String Lunch_cereal="cereal";
	public static final String Lunch_boisson="boisson";
	public static final String Lunch_vvpolav="vvpolav";
	public static final String Lunch_legume1="legume1";
	public static final String Lunch_legume2="legume2";
	public static final String Lunch_legume3="legume3";
	public static final String Lunch_fruit1="fruit1";
	public static final String Lunch_fruit2="fruit2";
	public static final String Lunch_name="name";

	public static final String Dinner_Table="dinner";
	//Column names

	public static final String Dinner_gras1="gras";

	public static final String Dinner_cereal="cereal";
	public static final String Dinner_boisson="boisson";
	public static final String Dinner_vvpolav="vvpolav";
	public static final String Dinner_legume1="legume1";
	public static final String Dinner_legume2="legume2";
	public static final String Dinner_legume3="legume3";
	public static final String Dinner_fruit1="fruit1";
	public static final String Dinner_fruit2="fruit2";
	public static final String Dinner_name="name";

	public static final String Menu_Table="menutable";
	//Column names
	public static final String Menu_plat="plat";
	public static final String Menu_gras1="gras";
	public static final String Menu_gras2="gras1";
	public static final String Menu_fruit1="fruit1";
	public static final String Menu_fruit2="fruit2";
	public static final String Menu_fruit3="fruit3";
	public static final String Menu_cereal="cereal1";
	public static final String Menu_cereal1="cereal2";
	public static final String Menu_legume1="leg1";
	public static final String Menu_legume2="leg2";
	public static final String Menu_legume3="leg3";
	public static final String Menu_vvpolav="vvpolav";
	public static final String Menu_boisson="boi";
	public static final String Menu_name="name";


}
