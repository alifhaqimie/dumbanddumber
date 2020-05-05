package sample.Model;

public class Patient {
    private String fullname;
    private String Etatpatient;
    private String Menu;
    private String Regime;

    public Patient() {
    }

    public Patient(String fullname, String etatpatient, String menu, String regime) {
        this.fullname = fullname;
        this.Etatpatient=etatpatient;
        this.Menu=menu;
        this.Regime=regime;
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
