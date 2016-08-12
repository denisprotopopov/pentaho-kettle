package org.pentaho.di.core.row;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



import junit.framework.TestCase;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.row.value.ValueMetaDate;
import org.pentaho.di.core.row.value.ValueMetaBase;
import org.pentaho.di.core.row.ValueDataUtil;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.variables.Variables;
import org.junit.After;

public class ValueDateUtilTest extends TestCase {
	
	
	@After
	public void tearDown() {
	    System.clearProperty( Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION );
	}  
	
	public void testHourOfDay() {

	    ValueMetaInterface datValueMeta = new ValueMetaDate();
	    datValueMeta.setConversionMask( "yyyy-MM-dd'T'HH:mm:ss'.000'XXX" );
	    datValueMeta.setDateFormatTimeZone(TimeZone.getTimeZone("CET"));
	    Object hourofday;
	    System.setProperty(Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION, "Y");

	    try {
	      Calendar c = Calendar.getInstance();
	      c.set(Calendar.HOUR_OF_DAY, 10);
	      hourofday = ValueDataUtil.hourOfDay(datValueMeta,c.getTime());
	      assertEquals("Hours of day ", new Long(10).longValue(),  ((Long)hourofday).longValue());
	    } catch ( Exception ex ) {
	      fail( "Error " + ex.getMessage());
	    }
	    System.setProperty(Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION, "N");
	    try {
	      Calendar c = Calendar.getInstance();
	      c.set(Calendar.HOUR_OF_DAY, 10);
	      hourofday = ValueDataUtil.hourOfDay(datValueMeta,c.getTime());
	      assertTrue("Hours of day expected " + new Long(10).longValue() + " != " + ((Long)hourofday).longValue() 
	    		  									,new Long(10).longValue()!=((Long)hourofday).longValue());
	    } catch ( Exception ex ) {
	      fail( "Error " + ex.getMessage());
	    }

	}

}
