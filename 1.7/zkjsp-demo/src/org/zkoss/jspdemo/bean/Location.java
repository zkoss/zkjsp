/**
 * 
 */
package org.zkoss.jspdemo.bean;

/**
 * @author ian
 *
 */
public class Location 
{
	private double longitude;
	private double latitude;
	private String address;
	
	
	public Location(double longitude, double latitude, String address) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
