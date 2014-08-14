package com.easy.apps.freewificonnecter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private WifiManager wifi_mng;
	private WifiReceiver wifi_rec;
	private ArrayList<WifiSataus> arrayList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		arrayList = new ArrayList<WifiSataus>();
		 wifi_mng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		 wifi_rec = new WifiReceiver();
		   registerReceiver(wifi_rec, new IntentFilter(
		     WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		   wifi_mng.startScan();
	}
	
	class WifiReceiver extends BroadcastReceiver {
		
		@Override
		  public void onReceive(Context context, Intent intent) {
			List<ScanResult> result_list = wifi_mng.getScanResults();
			
			for(int i=0;i<result_list.size();i++)
			{
				WifiSataus ws = new WifiSataus();
				String ssid = result_list.get(i).SSID;
				int level = result_list.get(i).level;
				boolean isSecrity = MainActivity.getSecurity(result_list.get(i));
				
				ws.setSsid(ssid);
				ws.setSecurity(isSecrity);
				ws.setLevel(level);
				
				arrayList.add(ws);
			}
		}
	}
	
	static boolean getSecurity(ScanResult result) {
	    if (result.capabilities.contains("WEP")) {
	        return true;
	    } else if (result.capabilities.contains("PSK")) {
	        return true;
	    } else if (result.capabilities.contains("EAP")) {
	        return true;
	    }
	    return false;
	}
}
