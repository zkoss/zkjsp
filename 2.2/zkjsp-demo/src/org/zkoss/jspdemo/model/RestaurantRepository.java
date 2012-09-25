/**
 * 
 */
package org.zkoss.jspdemo.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.zkoss.jspdemo.bean.Restaurant;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;


/**
 * @author ian
 *
 */
public class RestaurantRepository 
{
	private static final String key =  RestaurantRepository.class.getName();
	
	private List<Restaurant> restaurants;
	/**
	 * 
	 *
	 */
	public RestaurantRepository()
	{
		HttpSession session = (HttpSession)Sessions.getCurrent().getNativeSession();
		restaurants = ( List<Restaurant>)
			Sessions.getCurrent().getAttribute(key);
		
		if(restaurants==null)
			Sessions.getCurrent().setAttribute(key, 
					restaurants=Dummy.getDummyRestaurants(session.getServletContext()));
	}
	/**
	 * 
	 * @param session
	 */
	public RestaurantRepository(HttpSession session)
	{
		restaurants = ( List<Restaurant>)
			session.getAttribute(key);
		
		if(restaurants==null)
			session.setAttribute(key, 
					restaurants=Dummy.getDummyRestaurants(session.getServletContext()));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Restaurant> getAll()
	{
		return restaurants;
	}
	
	public void addRestaurant(Restaurant res)
	{
		restaurants.add(res);
	}
	
	

}
