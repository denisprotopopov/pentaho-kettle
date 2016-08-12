/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2016 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.core.row;

import java.util.Calendar;
import java.util.TimeZone;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import org.pentaho.di.core.row.value.ValueMetaDate;
import org.pentaho.di.core.Const;


public class ValueDateUtilTest {

  @After
  public void tearDown() {
    System.clearProperty( Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION );
  }

  @Test
  public void testHourOfDay() {
    ValueMetaInterface datValueMeta = new ValueMetaDate();
    datValueMeta.setConversionMask( "yyyy-MM-dd'T'HH:mm:ss'.000'XXX" );
    datValueMeta.setDateFormatTimeZone( TimeZone.getTimeZone( "CET" ) );
    Object hourofday;
    System.setProperty( Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION, "true" );

    try {
      Calendar c = Calendar.getInstance();
      c.set( Calendar.HOUR_OF_DAY, 10 );
      hourofday = ValueDataUtil.hourOfDay( datValueMeta, c.getTime() );
      Assert.assertEquals( "Hours of day ", new Long( 10 ).longValue(), ( (Long) hourofday ).longValue() );
    } catch ( Exception ex ) {
      Assert.fail( "Error " + ex.getMessage() );
    }
    System.setProperty( Const.KETTLE_OLD_DATE_CALCULATION_TIMEZONE_DECOMPOSITION, "false" );

    try {
      Calendar c = Calendar.getInstance();
      c.set( Calendar.HOUR_OF_DAY, 10 );
      hourofday = ValueDataUtil.hourOfDay( datValueMeta, c.getTime() );
      Assert.assertTrue( "Hours of day expected " + new Long( 10 ).longValue() + " != " + ( (Long) hourofday ).longValue(), new Long( 10 ).longValue() != ( (Long) hourofday ).longValue() );
    } catch ( Exception ex ) {
      Assert.fail( "Error " + ex.getMessage() );
    }
  }
}
