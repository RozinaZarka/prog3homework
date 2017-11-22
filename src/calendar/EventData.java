package calendar;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import java.text.ParseException;
import java.util.*;

public class EventData extends AbstractTableModel{

	private static final long serialVersionUID = -6448084477176516391L;
	
	List<Event> events  = new ArrayList<Event>();

	@Override
	public int getRowCount() {
		return events.size();
	}

	@Override
	public int getColumnCount() {
		return 2; //merthogy 2 oszlopunk van
	}

	@Override
	public Object getValueAt(int i1, int i2) {
		Event event = events.get(i1);
		if (i2 == 0) return event.getName();
		else  return event.getDate();
			
		}
	
	public void removeRow(int row) {
		    events.remove(row);
		
    }
	

	@Override
	public void addTableModelListener(TableModelListener tl) {
		super.addTableModelListener(tl);
	}
	
	@Override
	public String getColumnName(int i)
	{
		if( i == 0) return "Név";
		else return "Dátum";
		
		
	}
    
	@Override
	public Class<?> getColumnClass(int i)
	{
		if (i ==1) return Date.class;
		else return String.class;
		
	}
	
	@Override
	public void setValueAt(Object o, int i, int i1)
	{
		Event s=events.get(i);
		if(i1==0) s.setName((String)o);
		else
			try {
				s.setDate((String)o);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		events.set(i, s);
		this.fireTableRowsUpdated(i, i);
		
	}

	public void addEvent(String name, String stringDate) throws ParseException
	{
		events.add(new Event(name, stringDate));
		this.fireTableDataChanged();
	}
}
