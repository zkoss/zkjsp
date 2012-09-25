package org.zkoss.jspdemo.bean;


public 	class Time implements Comparable
{
	
	private final int hour;
	private final int minut;
	/**
	 * 
	 * @param hour
	 * @param minut
	 */
	public Time(int hour, int minut) {
		super();
		this.hour = hour;
		this.minut = minut;
	}
	/**
	 * 
	 */
	public int compareTo(Object o) 
	{
		if(o==null)
			throw new NullPointerException("input object can't be null!!!");
		Time in = (Time) o;
		if(hour == in.hour&& minut==in.minut)return 0;
		if(hour > in.hour)return 1;
		if(hour == in.hour&&minut > in.minut)return 1;
		return -1;
	}
	/**
	 * 
	 * @return
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * 
	 * @return
	 */
	public int getMinut() {
		return minut;
	}
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + hour;
		result = PRIME * result + minut;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (minut != other.minut)
			return false;
		return true;
	}


}//end of class...
