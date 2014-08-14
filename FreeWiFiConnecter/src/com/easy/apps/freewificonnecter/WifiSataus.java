package com.easy.apps.freewificonnecter;

public class WifiSataus {

	private  String ssid;
	private  int level;
	private  boolean isSecurity;
	public  String getSsid() {
		return ssid;
	}
	public  void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public  int getLevel() {
		return level;
	}
	public  void setLevel(int level) {
		this.level = level;
	}
	public  boolean isSecurity() {
		return isSecurity;
	}
	public  void setSecurity(boolean isSecurity) {
		this.isSecurity = isSecurity;
	}
}
