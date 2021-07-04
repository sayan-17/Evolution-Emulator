package genetic.data;

public class Individual {
	
	private GeneticMaterial geneticMaterial;
	private int speciesCode;
	private double percentageOfChange;
	
	public Individual(GeneticMaterial geneticMaterial){
		this.geneticMaterial = geneticMaterial;
//		calculateChange();
	}
	
	private void calculateChange(){
		//TODO
	}
	
	public static Individual reproduce(Individual i1, Individual i2){
		Gamete g1 = new Gamete(i1.geneticMaterial), g2 = new Gamete(i2.geneticMaterial);
		return new Individual(Gamete.makeNewGeneticMaterial(g1, g2));
	}
	
	@Override
	public String toString(){
		return geneticMaterial.toString();
	}
}
