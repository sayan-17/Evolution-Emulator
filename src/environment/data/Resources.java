package environment.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Resources{
	
	private class Resource{
		private final int resourceIndex;
		private int availableAmount;
		
		public Resource(int availableAmount, int resourceIndex){
			this.availableAmount = availableAmount;
			this.resourceIndex = resourceIndex;
		}
		
		public void setAvailableAmount(int availableAmount) {
			this.availableAmount = availableAmount;
		}
		
		public int getAvailableAmount() {
			return availableAmount;
		}
		
		public int getResourceIndex(){
			return resourceIndex;
		}
		
		public void depleteAvailableAmount(int depleteBy){
			this.availableAmount -= depleteBy;
		}
	}
	
	private ArrayList<Resource> resources;
	private float replenishFactor;
	
	public Resources(){
		resources = new ArrayList<>();
	}
	
	public Resources(float replenishFactor, HashMap<Integer, Integer> resources){
		setResources(resources, replenishFactor);
		this.replenishFactor = replenishFactor;
	}
	
	public void setResources(HashMap<Integer, Integer> resources, float replenishFactor){
		for(Map.Entry<Integer, Integer> entry : resources.entrySet()){
			this.resources.add(new Resource(entry.getKey(), entry.getValue()));
		}
		this.replenishFactor = replenishFactor;
	}
	
	public void depleteResource(int ind, int depleteBy){
		resources.get(ind).depleteAvailableAmount(depleteBy);
	}
	
	public void setResource(int ind, int amount){
		resources.get(ind).setAvailableAmount(amount);
	}
	
	public void replenishResource(int ind){
		int available = resources.get(ind).getAvailableAmount();
		resources.get(ind).setAvailableAmount((int) (available + available * replenishFactor));
	}
	
	public void depleteAllResources(int depleteBy){
		for(Resource resource : resources){
			resource.depleteAvailableAmount(depleteBy);
		}
	}
	
	public void setAllResources(int amount){
		for(Resource resource : resources){
			resource.setAvailableAmount(amount);
		}
	}
	
	public void replenishAllResources(){
		for(Resource resource : resources){
			int available = resource.getAvailableAmount();
			resource.setAvailableAmount((int) (available + available * replenishFactor));
		}
	}
}
