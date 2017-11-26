package calendar;

import java.io.Serializable;
import java.util.*;
import java.text.*;

public class Event implements Serializable {

	private static final long serialVersionUID = -6770675826251024370L;

	// esem�ny neve
    private String name;
    // esem�ny ideje.
    private Date date;
    //esmem�ny priorit�sa
    boolean important;
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
    //esem�ny fontoss�g�nak lek�rdez�se
    public boolean isImportant() {
    	return important;
    }
    //esem�ny fontoss�g�nak be�ll�t�sa
    public void setImportancy(boolean importancy) {
    	this.important = importancy;
    }
    //string konvert�l�sa d�tum form�tumba
    public static Date Date(String stringdate) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm"); 
        return ft.parse(stringdate); 
     }
    // esem�ny l�trehoz�sa
	public Event(String name, String stringDate, boolean important) throws ParseException {
        this.name = name;
        this.date = Date(stringDate) ;
        this.important = important;
        
    }
    
}


