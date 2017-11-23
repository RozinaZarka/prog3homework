package calendar;

import java.io.Serializable;
import java.util.*;
import java.text.*;

public class Event implements Serializable {

	private static final long serialVersionUID = -6770675826251024370L;

	// esemény neve
    private String name;
    // esemény ideje.
    private Date date;
    //esmemény prioritása
    boolean urgent;
    // esemény nevének lekérdezése. 
    public String getName() {
        return name;
    }  
    // esemény nevének beállítása.
    public void setName(String name) {
        this.name = name;
    } 
    // esemény idejének lekérdezése.
    public Date getDate() {
        return date;
    }
    // esemény idejének beállítása.
    public void setDate(String date) throws ParseException {
        this.date = Date(date);
    } 
    //esemény sürgősségének lekérdezése
    public boolean isUrgent() {
    	return urgent;
    }
    //esemény sürgősségének beállítása
    public void setUrgency(boolean urgency) {
    	this.urgent = urgency;
    }
    //string forámzása dátummmá
    public static Date Date(String stringdate) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm"); 
        return ft.parse(stringdate); 
     }
    // esemény létrehozása 
	public Event(String name, String stringDate, boolean urgent) throws ParseException {
        this.name = name;
        this.date = Date(stringDate) ;
        this.urgent = urgent;
        
    }
    
}


