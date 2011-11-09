/**
 * 
 */
package org.zkoss.jspdemo.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;

import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.jspdemo.bean.*;



/**
 * @author ian
 *
 */
/*package*/ class Dummy {
	
	private static final int count = 10;
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Restaurant> getDummyRestaurants(ServletContext context) 
	{
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>(count);
		Object[][]  datas = new Object[][]{
			new Object[]{
					"Papa Giovanni",
					"/res/res1.html",
					"The best Italian food is here!",
					"/img/res1.gif",
					"itallian,spaghetti",
					new Benchmark(65,75,85,80,95),
					new Location(121.55297871035938D,25.042063183495276D,""),
					modify(new OpeningHours()),
					new Comment("It's too expansive!!",85,"Ian Tsai",Calendar.getInstance())},
			new Object[]{
					"La Pettis",
					"/res/res2.html",
					"The best French food is here!",
					"/img/res2.jpg",
					"french,wine",
					new Benchmark(96,90,75,85,40),
					new Location(121.55296359167963D,25.040813214480153D,""),
					modify(new OpeningHours()),
					new Comment("good French food",85,"Ian Tsai",Calendar.getInstance())},
			new Object[]{
					"四川 火鍋專賣",
					"/res/res3.html",
					"天府之國 麻辣火鍋",
					"/img/res3.jpg",
					"itallian,spaghetti",
					new Benchmark(75,70,70,65,75),
					new Location(121.55179955830789D,25.039900290735062D,""),
					modify(new OpeningHours()),
					new Comment("never visit again, too spicey~",65,"Ian Tsai",Calendar.getInstance())},
							
			new Object[]{
					"本格江戸前寿司",
					"/res/res4.html",
					"「魚がし日本一」",
					"/img/res4.jpg",
					"japen,sushi",
					new Benchmark(80,85,90,90,50),
					new Location(121.55299171478245D,25.042996275309047D,"東京都港区赤坂3-9-4 赤坂扇也ﾋﾞﾙ1F"),
					modify(new OpeningHours()),
					new Comment("価格、味、サービス、雰囲気すべての面で満足していただけるお店",85,"Ian Tsai",Calendar.getInstance())},
		};
		return initValue(restaurants, datas, context);
	}
	
	
	private static final ArrayList<Restaurant> 
	initValue(ArrayList<Restaurant> resList, Object[][] objs, ServletContext context)
	{
		Restaurant rest ;
		String imgUri;
		for(int i=0;i<objs.length;i++)
		{
			rest = new Restaurant();
			rest.setName((String)objs[i][0]);
			//rest.setContent(getContent((String)objs[i][1]));
			rest.setDescription((String)objs[i][2]);
			rest.setImage(loadFrom((String)objs[i][3], context));
			rest.addTag((String)objs[i][4]);
			rest.setBenchmark((Benchmark)objs[i][5]);
			rest.setLocation((Location)objs[i][6]);
			rest.setOpeningHours((OpeningHours)objs[i][7]);
			rest.addComment((Comment)objs[i][8]);
			resList.add(rest);
		}
		return resList;
	}
	
	public static final  AImage loadFrom(String url, ServletContext context)
	{
		InputStream in = context.getResourceAsStream(url);
		try {
			if(in!=null)
				return new AImage(url, Files.readAll(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String getContent(String url, ServletContext context)
	{
		InputStream in = context.getResourceAsStream(url);
		try {
			if(in!=null)
				return new String(Files.readAll(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final OpeningHours modify(OpeningHours oh)
	{
		oh.setDailyInterval(Calendar.MONDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.TUESDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.WEDNESDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.THURSDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.FRIDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.SATURDAY, new Time(8,30), new Time(23,0));
		oh.setDailyInterval(Calendar.SUNDAY, new Time(8,30), new Time(23,0));
		return oh;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
