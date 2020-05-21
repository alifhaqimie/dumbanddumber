package sample.Model;

public class Table {
    int idpatientstable;
    String fullname,Etatpatient,Regime,breakfast,lunch,dinner;

    public Table() {
    }

    public Table(int idpatientstable, String fullname, String etatpatient, String regime, String breakfast, String lunch, String dinner) {
        this.idpatientstable = idpatientstable;
        this.fullname = fullname;
        Etatpatient = etatpatient;
        Regime = regime;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
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

    public String getRegime() {
        return Regime;
    }

    public void setRegime(String regime) {
        Regime = regime;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public int getIdpatientstable() {
        return idpatientstable;
    }

    public void setIdpatientstable(int idpatientstable) {
        this.idpatientstable = idpatientstable;
    }



}
