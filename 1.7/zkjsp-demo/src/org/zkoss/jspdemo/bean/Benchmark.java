/**
 * 
 */
package org.zkoss.jspdemo.bean;

/**
 * @author ian
 *
 */
public class Benchmark {

	private int service;
	private int cleanness;
	private int environment;
	private int delicious;
	private int baseprice;
	
	
	public Benchmark(int service, int cleanness, int environment, int delicious, int basprice) {
		super();
		this.service = service;
		this.cleanness = cleanness;
		this.environment = environment;
		this.delicious = delicious;
		this.baseprice = basprice;
	}
	public int getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(int basprice) {
		this.baseprice = basprice;
	}
	public int getCleanness() {
		return cleanness;
	}
	public void setCleanness(int cleanness) {
		this.cleanness = cleanness;
	}
	public int getDelicious() {
		return delicious;
	}
	public void setDelicious(int delicious) {
		this.delicious = delicious;
	}
	public int getEnvironment() {
		return environment;
	}
	public void setEnvironment(int environment) {
		this.environment = environment;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}

}
