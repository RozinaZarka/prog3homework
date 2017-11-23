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
//import javax.swing.RowSorter;
import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;

public class EventFrame extends JFrame {
	
		private static final long serialVersionUID = -7514761915166101108L;
		private EventData data;
		private JTextField newName;
	    private JTextField newDate;
	    /*
	     * Itt hozzuk l√©tre √©s adjuk hozz√° az ablakunkhoz a k√ºl√∂nb√∂z√µ komponenseket
	     * (t√°bl√°zat, beviteli mez√µ, gomb).
	     */
	    private void initComponents() {
	        this.setLayout(new BorderLayout());
			//t√°bl√°zat
			JTable jt=new JTable(data);
			jt.setFillsViewportHeight(rootPaneCheckingEnabled);
			jt.setRowSorter(new TableRowSorter<EventData>(data));
			
			//sz√≠nez√©s
			jt.setDefaultRenderer(String.class, new EventTableCellRenderer(jt.getDefaultRenderer(String.class)));
			jt.setDefaultRenderer(Date.class, new EventTableCellRenderer(jt.getDefaultRenderer(Date.class)));
			jt.setDefaultRenderer(Boolean.class, new EventTableCellRenderer(jt.getDefaultRenderer(Boolean.class)));
			//-------------------------------------------------------------------------------------------------------
			
			this.add(new JScrollPane(jt),BorderLayout.CENTER);
			
			
			JPanel mypanel=new JPanel(new FlowLayout());
			mypanel.add(new JLabel("EsemÈny:"));
			newName = (JTextField)mypanel.add(new JTextField(15)); 
			
			mypanel.add(new JLabel("D·tum:"));
			newDate = (JTextField)mypanel.add(new JTextField(20));

			//hozz√°ad√≥ gomb
			JButton adderButton = (JButton) mypanel.add(new JButton("Felvesz"));
			adderButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						try {
							data.addEvent(newName.getText(), newDate.getText(),false);
						} catch (ParseException e) {
							//  Auto-generated catch block
							e.printStackTrace();
						}
						jt.updateUI();
					}
				});
			
	     jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	    //tˆrlÈs gomb
	    JButton deleteButton = (JButton) mypanel.add(new JButton("Tˆrˆl"));
	    deleteButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent arg0) { 
	            data.removeRow(jt.getSelectedRow());
	            jt.updateUI();
	        } 
	    });
	    
	    this.add(mypanel,BorderLayout.SOUTH);
	   }
	    
	   


	    
	    @SuppressWarnings("unchecked")
	    public EventFrame() {
	        super("Napt·r");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        
	        // Indul·skor betˆltj¸k az adatokat
	        try {
	            data = new EventData();
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Events.dat"));
	            data.events = (List<Event>)ois.readObject();
	            ois.close();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        // Bez·r·skorskor mentj¸k az adatokat
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

	        // Fel√©p√≠tj√ºk az ablakot
	        setMinimumSize(new Dimension(700, 500));
	        initComponents();
	    }

	    
	    public static void main(String[] args) {
	        // Megjelen√≠tj√ºk az ablakot
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
				// meg√°llap√≠tjuk, hogy elm√∫lt-e az esm√©ny vagy nem,
				if(actualEvent.getDate().before(new Date()))
					bg=Color.RED;
				else 
				{ 
					if(actualEvent.isUrgent()) {
						bg = Color.BLUE;
					} else bg=Color.GREEN;
				
				}
					
				// ez alapj·n ·lÌtjuk a h·ttÈrszÌnt:
				component.setBackground(bg);
				return component;
			}
			
		}

	
}
