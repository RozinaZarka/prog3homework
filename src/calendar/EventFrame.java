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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class EventFrame extends JFrame {
	
		private static final long serialVersionUID = -7514761915166101108L;
		private EventData data;
		private JTextField newName;
	    private JTextField newDate;
	    /*
	     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
	     * (táblázat, beviteli mezõ, gombok).
	     */
	    private void initComponents() {
	        this.setLayout(new BorderLayout());
			//táblázat
			JTable jt=new JTable(data);
			jt.setFillsViewportHeight(rootPaneCheckingEnabled);
			jt.setRowSorter(new TableRowSorter<EventData>(data));
			JMenuBar menubar = new JMenuBar();
			JMenu modify = new JMenu("Módosítás");
	        modify.setMnemonic(KeyEvent.VK_F);
	        // új esemény felvétele
	      
	        // megjeleníti alul a jpanelt hogy bevihessük az adatokat
	        	JPanel mypanel=new JPanel(new FlowLayout());
				mypanel.add(new JLabel("Esemény:"));
				newName = (JTextField)mypanel.add(new JTextField(15)); 
				
				mypanel.add(new JLabel("Dátum:"));
				newDate = (JTextField)mypanel.add(new JTextField(20));

				//hozzáadó gomb
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
				this.add(mypanel,BorderLayout.SOUTH);
	      
	        
	        //esemény törlése
	        JMenuItem delete = new JMenuItem("Törlés");
	        delete.setMnemonic(KeyEvent.VK_E);
	        delete.addActionListener((ActionEvent event) -> {
	        	// törli a kiválasztott sort
	        	data.removeRow(jt.getSelectedRow());
	            jt.updateUI();
	        });
	        modify.add(delete);
	    

	        menubar.add(modify);

	        setJMenuBar(menubar);
			//sziínezés
			jt.setDefaultRenderer(String.class, new EventTableCellRenderer(jt.getDefaultRenderer(String.class)));
			jt.setDefaultRenderer(Date.class, new EventTableCellRenderer(jt.getDefaultRenderer(Date.class)));
			jt.setDefaultRenderer(Boolean.class, new EventTableCellRenderer(jt.getDefaultRenderer(Boolean.class)));
			//-------------------------------------------------------------------------------------------------------
			
			this.add(new JScrollPane(jt),BorderLayout.CENTER);
		
	     jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	    
	   
	   }
	    
	    
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
	        
	        // Bezáráskorskor mentjük az adatokat
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
	        setMinimumSize(new Dimension(700, 500));
	        initComponents();
	    }

	    
	    public static void main(String[] args) {
	        // Megjelelenítjük az ablakot
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
				// megállapítjuk, hogy elmúlt-e az esmény vagy nem
				if(actualEvent.getDate().before(new Date()))
					bg=Color.RED;
				else 
				{ 
					//megállapítjuk, hogy fontos-e az esemény vagy sem
					if(actualEvent.isImportant()) {
						bg = Color.BLUE;
					} else bg=Color.GREEN;
				
				}
					
				// ez alapján álítjuk a háttérszínt:
				component.setBackground(bg);
				return component;
			}
			
		}

	
}
