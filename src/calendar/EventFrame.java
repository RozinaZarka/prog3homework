package calendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;
public class EventFrame extends JFrame {
	
		private static final long serialVersionUID = -7514761915166101108L;
		private EventData data;
		private JTextField newName;
	    private JTextField newDate;
	    /*
	     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
	     * (táblázat, beviteli mezõ, gomb).
	     */
	    private void initComponents() {
	        this.setLayout(new BorderLayout());
			//táblázat
			JTable jt=new JTable(data);
			jt.setFillsViewportHeight(rootPaneCheckingEnabled);
			jt.setRowSorter(new TableRowSorter<EventData>(data));
			
			//színezés
			jt.setDefaultRenderer(String.class, new EventTableCellRenderer(jt.getDefaultRenderer(String.class)));
			jt.setDefaultRenderer(Date.class, new EventTableCellRenderer(jt.getDefaultRenderer(Date.class)));
			//-------------------------------------------------------------------------------------------------------
			
			this.add(new JScrollPane(jt),BorderLayout.CENTER);
			
			//hozzáadó sor
			JPanel adderPanel=new JPanel(new FlowLayout());
			adderPanel.add(new JLabel("Esemény:"));
			newName = (JTextField)adderPanel.add(new JTextField(15)); 
			
			adderPanel.add(new JLabel("Dátum:"));
			newDate = (JTextField)adderPanel.add(new JTextField(6));
			//JButton deleteButton = (JButton) adderPanel.add(new JButton("Töröl"));
			
			JButton adderButton = (JButton) adderPanel.add(new JButton("Felvesz"));
			adderButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						try {
							data.addEvent(newName.getText(), newDate.getText());
						} catch (ParseException e) {
							//  Auto-generated catch block
							e.printStackTrace();
						}
						jt.updateUI();
					}
				});
			this.add(adderPanel,BorderLayout.SOUTH);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	/*		JButton adderButton = (JButton) adderPanel.add(new JButton("Felvesz"));
			adderButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						try {
							data.addEvent(newName.getText(), newDate.getText());
						} catch (ParseException e) {
							//  Auto-generated catch block
							e.printStackTrace();
						}
						jt.updateUI();
					});
				
			this.add(adderPanel,BorderLayout.SOUTH);
	    }
	    
	   JButton deleteButton = (JButton) adderPanel.add(new Jbutton("Töröl"));
	   deleteButton.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent ae) 
				{
				Component component = renderer.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				data.removeRow(data.events.get(table.getRowSorter().convertRowIndexToModel(row)));
					
					jt.updateUI();
				});
			
		this.add(adderPanel,BorderLayout.SOUTH);
    }
    */

	    
	    @SuppressWarnings("unchecked")
	    public EventFrame() {
	        super("Naptár");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        
	        // Induláskor betöltjük az adatokat
	        try {
	            data = new EventData();
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Events.dat"));
	            data.events = (List<Event>)ois.readObject();
	            ois.close();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        // Bezáráskor mentjük az adatokat
	        addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						try {
							ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Events.dat"));
							oos.writeObject(data.events);
							oos.close();
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
				});

	        // Felépítjük az ablakot
	        setMinimumSize(new Dimension(500, 200));
	        initComponents();
	    }

	    
	    public static void main(String[] args) {
	        // Megjelenítjük az ablakot
	        EventFrame sf = new EventFrame();
	        sf.setVisible(true);
	    }

		private class EventTableCellRenderer implements TableCellRenderer {
			private final TableCellRenderer renderer;
			public EventTableCellRenderer(TableCellRenderer defRenderer) 
			{
				this.renderer = defRenderer;
			}
			
			@Override
			public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) 
			{
				Component component = renderer.getTableCellRendererComponent(
							table, value, isSelected, hasFocus, row, column);
				Event actualEvent = data.events.get(table.getRowSorter().convertRowIndexToModel(row));
				Color bg;
				// megállapítjuk, hogy elmúlt-e az esmény vagy nem,
				if(actualEvent.getDate().before(new Date()))
					bg=Color.RED;
				else
					bg=Color.GREEN;
				// és ez alapján átállítjuk a komponens háttérszínét:
				component.setBackground(bg);
				return component;
			}
			
		}

	
}
