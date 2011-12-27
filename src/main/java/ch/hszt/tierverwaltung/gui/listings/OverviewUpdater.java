package ch.hszt.tierverwaltung.gui.listings;

import java.util.ArrayList;
import java.util.List;

public class OverviewUpdater implements Runnable {
	private List<Overview> oList;
	
	public OverviewUpdater(){
		oList = new ArrayList<Overview>();
	}
	
	@Override
	public void run() {
		while(true){
			synchronized(this){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(Overview o : oList){
					o.updateTableValues();
				}
			}
		}
	}
	
	public void registerOverview(Overview o){
		oList.add(o);
	}

}
