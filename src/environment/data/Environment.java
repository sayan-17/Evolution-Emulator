package environment.data;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
	
	private int rows, cols;
	private ArrayList<Resources>[][] environment;
	
	public Environment(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		environment = new ArrayList[rows][cols];
	}
}
