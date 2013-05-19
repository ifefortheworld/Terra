package com;

import java.util.HashMap;
import java.util.Map;

public class ComFactory {
	private static ComFactory factory;
	private Map<String, Object> comMap;

	public static ComFactory getComFactory() {
		if (factory == null)
			factory = new ComFactory();
		return factory;

	}

	protected Map<String, Object> getComMap() {
		return comMap;
	}

	protected void setComMap(Map<String, Object> comtMap) {
		this.comMap = comtMap;
	}

	protected ComFactory() {
		this.setComMap(new HashMap<String, Object>());
	}

	public void insertCom(String key, java.lang.Object obj) {
		if (this.getComMap().get(key) != null) {
			this.removeCom(key);
		}
		this.getComMap().put(key, obj);
	}

	public void updateCom(String key, java.lang.Object obj) {
		this.insertCom(key, obj);

	}

	public void removeCom(String key) {
		if (this.getCom(key) != null)
			this.getComMap().remove(key);
	}

	public void removeAllCom() {
		this.getComMap().clear();
	}

	public java.lang.Object getCom(String key) {
		return this.getComMap().get(key);
	}

}
