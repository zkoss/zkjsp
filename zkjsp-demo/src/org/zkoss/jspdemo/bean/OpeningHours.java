/**
 * 
 */
package org.zkoss.jspdemo.bean;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ian
 *
 */
public class OpeningHours 
{

	public static final int HOUR_OF_DAY = 0;
	public static final int DAY_OF_WEEK = 0;
	public static final int DAY_OF_MONTH = 0;
	
	private final Time[][] weekAvalable;
	
	public OpeningHours()
	{
		weekAvalable = new Time[7][];
		for(int i=0;i<7;i++)
			weekAvalable[i] = new Time[2];
	}
	
	/**
	 * see if the given interval is in this interval.
	 * @param enter can't be null.
	 * @return
	 */
	public boolean isAvalable(Date enter)
	{
		if(enter==null)
			throw new NullPointerException("enter can't be null!!!");
		Calendar cal = Calendar.getInstance();
		cal.setTime(enter);
		return isAvalable(cal);
	}
	/**
	 * see if the given interval is in this interval.
	 * @param enter can't be null.
	 * @return
	 */
	public boolean isAvalable(Calendar enter)
	{
		return isAvalable(
				enter.get(Calendar.DAY_OF_WEEK), 
				enter.get(Calendar.HOUR_OF_DAY), 
				enter.get(Calendar.MINUTE));
	}
	/**
	 * 
	 * @param weekDay please use {@link java.util.Calendar}'s static const.
	 * @param hr
	 * @return
	 */
	public boolean isAvalable(int weekDay, int time24hr, int minute)
	{
		Time in = new Time(time24hr, minute);
		Time[] daily = weekAvalable[ weekDay-1];
		if(daily==null||daily[0]==null)return false;
		if(daily[0].compareTo(in)>0)return false;
		if(in.compareTo(daily[1])>0)return false;
		return true;
	}
	/**
	 * 
	 * @param weekday
	 * @param start
	 * @param end
	 */
	public void setDailyInterval(int weekday, Time start, Time end)
	{
		weekAvalable[weekday-1][0] = start;
		weekAvalable[weekday-1][1] = end;
	}

	
	
	
}//end of class...
