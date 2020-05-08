package sample.Model;

public class Table {
    String fullname,Etatpatient,Menu,Regime;
    int idpatientstable;

    public int getIdpatientstable() {
        return idpatientstable;
    }

    public void setIdpatientstable(int idpatientstable) {
        this.idpatientstable = idpatientstable;
    }

    public Table(int idpatientstable, String fullname, String etatpatient, String menu, String regime) {
        this.idpatientstable=idpatientstable;
        this.fullname = fullname;
        this.Etatpatient = etatpatient;
        this.Menu = menu;
       this.Regime = regime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEtatpatient() {
        return Etatpatient;
    }

    public void setEtatpatient(String etatpatient) {
        Etatpatient = etatpatient;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        Menu = menu;
    }

    public String getRegime() {
        return Regime;
    }

    public void setRegime(String regime) {
        Regime = regime;
    }

}
