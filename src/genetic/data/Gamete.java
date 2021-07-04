package genetic.data;

import java.util.ArrayList;

public class Gamete extends GeneticMaterial{
	
	private String[] gameteStrand;
	
	public Gamete(Chromosome[] chromosomes){
		super(chromosomes);
		gameteStrand = new String[super.noOfChromosomes];
		makeGametes();
	}
	
	public Gamete(GeneticMaterial geneticMaterial){
		super(geneticMaterial);
		gameteStrand = new String[super.noOfChromosomes];
		makeGametes();
	}
	
	public static GeneticMaterial makeNewGeneticMaterial(Gamete g1, Gamete g2){
		ArrayList<Chromosome> chromosomes = new ArrayList<>();
		for(int i = 0; i < g1.gameteStrand.length && i < g2.gameteStrand.length; ++i){
			chromosomes.add(new Chromosome(g1.gameteStrand[i],g2.gameteStrand[i]));
		}
		GeneticMaterial geneticMaterial = new GeneticMaterial(chromosomes);
		geneticMaterial.makeRandomMutation((int) (UniversalConstantParameters.probabilityOfMutation*10) );
		return geneticMaterial;
	}
	
	private void makeGametes(){
		for(int i = 0; i < chromosomes.size(); ++i){
			gameteStrand[i] = chromosomes.get(i).getOneRandomChromosomeStrand();
		}
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder("genetic.data.Gamete : \n");
		for(String strand : gameteStrand){
			stringBuilder.append("\t" + strand);
		}
		return stringBuilder.toString();
	}
}
