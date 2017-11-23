package calendar;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import calendar.Event;

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
		return 3; //merthogy 2 oszlopunk van
	}

	@Override
	public Object getValueAt(int i, int i1) {
		Event event = events.get(i);
		switch(i1) {
			case 0: return event.getName();
			case 1: return event.getDate();
			default: return event.isUrgent();
		}
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
		switch(i)
		{
			case 0: return "N�v";
			case 1: return "D�tum";
			default: return "Fontos?";
		}
	}
    
	@Override
	public Class<?> getColumnClass(int i)
	{
		switch(i)
		{
			case 0: return String.class;
			case 1: return Date.class;
			default: return Boolean.class;
			
		}
	}
	
	@Override
	public boolean isCellEditable(int i, int i1)
	{
		boolean[] b={false,false,true};
		return (i1<=getColumnCount() && i1>=0)?b[i1]:false;
	}
	
	@Override
	public void setValueAt(Object o, int i, int i1)
	{
		Event s=events.get(i);
	if(i1>=2)
	{
			s.setUrgency((Boolean)o);
				
		}
		events.set(i, s);
		this.fireTableRowsUpdated(i, i);
	
		
	}

	public void addEvent(String name, String stringDate,boolean urgency) throws ParseException
	{
		events.add(new Event(name, stringDate,urgency));
		this.fireTableDataChanged();
	}
}
