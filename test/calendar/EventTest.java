package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

	Event testevent;
	SimpleDateFormat ft;
	@Before
	public void testSetup() throws ParseException {
		 testevent = new Event("testevent","2011.11.11 11:11",true);
		 ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm");
	}
	
	@Test
	public void testingGetName () throws ParseException {
		Assert.assertEquals(testevent.getName(),"testevent");
	}
	
	@Test
	public void testingSetName () throws ParseException {
		testevent.setName("testevent2");
		Assert.assertEquals(testevent.getName(),"testevent2");
	}

	
	@Test
	public void testingGetDate () throws ParseException {
		Assert.assertEquals(testevent.getDate(),ft.parse("2011.11.11 11:11"));
	}
	
	
	@Test
	public void testingSetDate () throws ParseException {
		testevent.setDate("2011.11.11 11:22");
		Assert.assertEquals(testevent.getDate(),ft.parse("2011.11.11 11:22"));
	}
}
