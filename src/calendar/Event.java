package calendar;

import java.io.Serializable;
import java.util.*;
import java.text.*;

public class Event implements Serializable {

	private static final long serialVersionUID = -6770675826251024370L;

	// esemÈny neve
    private String name;
    // esemÈny ideje.
    private Date date;
    //esmemÈny priorit·sa
    boolean important;
    // esem√©ny nev√©nek lek√©rdez√©se. 
    public String getName() {
        return name;
    }  
    // esem√©ny nev√©nek be√°ll√≠t√°sa.
    public void setName(String name) {
        this.name = name;
    } 
    // esem√©ny idej√©nek lek√©rdez√©se.
    public Date getDate() {
        return date;
    }
    // esem√©ny idej√©nek be√°ll√≠t√°sa.
    public void setDate(String date) throws ParseException {
        this.date = Date(date);
    } 
    //esemÈny fontoss·g·nak lekÈrdezÈse
    public boolean isImportant() {
    	return important;
    }
    //esemÈny fontoss·g·nak be·llÌt·sa
    public void setImportancy(boolean importancy) {
    	this.important = importancy;
    }
    //string konvert·l·sa d·tum form·tumba
    public static Date Date(String stringdate) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm"); 
        return ft.parse(stringdate); 
     }
    // esemÈny lÈtrehoz·sa
	public Event(String name, String stringDate, boolean important) throws ParseException {
        this.name = name;
        this.date = Date(stringDate) ;
        this.important = important;
        
    }
    
}


