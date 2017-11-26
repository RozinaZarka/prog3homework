package calendar;

import java.text.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventDataTest {
	
	EventData testdata;
	
	@Before
    public void testSetUp() throws ParseException {
		testdata = new EventData();
		testdata.addEvent("testevent","2011.11.11 11:11",true);
	}
	@Test
	public void testGetValueAt() {
		Assert.assertEquals(testdata.getValueAt(0,0),"testevent");
	}
	@Test
	public void testIsCellEditable() {
		Assert.assertFalse(testdata.isCellEditable(0,0));
		Assert.assertFalse(testdata.isCellEditable(0,1));
		Assert.assertTrue(testdata.isCellEditable(0,2));

	}
	@Test
	public void testRemoveRowANDgetRowCount() {
		Assert.assertEquals(testdata.getRowCount(),1);
		testdata.removeRow(0);
		Assert.assertEquals(testdata.getRowCount(),0);
	}
	
}
