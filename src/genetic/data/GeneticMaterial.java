package genetic.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneticMaterial {
	protected ArrayList<Chromosome> chromosomes;
	protected int noOfChromosomes;
	
	public GeneticMaterial(){
		chromosomes = new ArrayList<>();
	}
	
	public GeneticMaterial(Chromosome[] chromosomes){
		this.chromosomes = new ArrayList<>();
		this.chromosomes.addAll(Arrays.asList(chromosomes));
		this.noOfChromosomes = chromosomes.length;
	}
	
	public GeneticMaterial(ArrayList<Chromosome> chromosomes){
		this.chromosomes = new ArrayList<>();
		this.chromosomes.addAll(chromosomes);
		this.noOfChromosomes = chromosomes.size();
	}
	
	public GeneticMaterial(GeneticMaterial geneticMaterial){
		this.chromosomes = geneticMaterial.chromosomes;
		this.noOfChromosomes = geneticMaterial.chromosomes.size();
	}
	
	public void setCharacteristics(Chromosome[] chromosomes){
		this.chromosomes.addAll(Arrays.asList(chromosomes));
		this.noOfChromosomes = chromosomes.length;
	}
	
	public void makeRandomMutation(int noOfChanges){
		Random random = new Random();
		int i, j, x = random.nextInt(3);
		switch (x){
			case 0 :i = random.nextInt(chromosomes.size());
					j = random.nextInt(chromosomes.size());
					chromosomes.get(i).changeRandomBase(UniversalConstantParameters.bases[j], noOfChanges);
					break;
			case 1 :i = random.nextInt(chromosomes.size());
					chromosomes.get(i).removeRandomBase();
					break;
			case 2 :i = random.nextInt(chromosomes.size());
					j = random.nextInt(chromosomes.size());
					chromosomes.get(i).addRandomBase(UniversalConstantParameters.bases[j]);
					break;
		}
	}
	
	public static double calculateGeneticChange(){
		//TODO
		return 1.0;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Chromosomes :");
		for(Chromosome chromosome : chromosomes){
			stringBuilder.append("\n" + chromosome.toString());
		}
		return stringBuilder.toString();
	}
}
