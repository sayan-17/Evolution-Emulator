import genetic.data.*;
import java.util.Arrays;

public class Test {
	
	public static void main(String[] args){
//		testChromosome();
		testReader();
		testIndividual();
	}
	
	private static void testChromosome(){
		Chromosome c1 = new Chromosome("ATGATAAAGUAHAGATGA","ATGAUAGTAG"), c2 = new Chromosome();
		c2.setChromosome("AAABBAABAA", "AAAABAAABA");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println("--------------------------------------------");
		c1.changeRandomBase('C',0);
		c2.changeRandomBase('C', 2);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println("---------------------------------------------");
		System.out.println("Random strand - c1 : " + c1.getOneRandomChromosomeStrand() +
				"\t c2 : " + c2.getOneRandomChromosomeStrand());
	}
	
	private static void testReader(){
		Reader reader = new Reader();
		try{
			reader.readData("C:\\Users\\sayan\\Desktop\\SpeciesTest.txt");
		}catch (Exception e){
			System.out.println(e);
		}
		System.out.println( "Codon Length : " + UniversalConstantParameters.codonLength +
		"\nBases : " + Arrays.toString(UniversalConstantParameters.bases) +
		"\nThreshold For New genetic.data.Species : " + UniversalConstantParameters.thresholdForNewSpecies +
		"\nProbability Of Mutation : " + UniversalConstantParameters.probabilityOfMutation +
		"\nStart Codon : " + UniversalConstantParameters.startCodon +
		"\nStop Codon : " + Arrays.toString(UniversalConstantParameters.stopCodon));
	}
	
	private static void testIndividual(){
		Chromosome[] chromosomes1 = {
				new Chromosome("ATGTGA","ATGAUAGTTAG"),
				new Chromosome("ATGUATTGA","ATGTAG")
		};
		Chromosome[] chromosomes2 = {
				new Chromosome("ATGATUAUCTGA","ATGATTAG"),
				new Chromosome("ATGUATUATUTGA","ATGUATTAG")
		};
		Individual individual1 = new Individual(new GeneticMaterial(chromosomes1));
		Individual individual2 = new Individual(new GeneticMaterial(chromosomes2));
		Individual individual3 = Individual.reproduce(individual1, individual2);
		System.out.println("genetic.data.Individual-1 :\n" + individual1.toString() + "\n");
		System.out.println("genetic.data.Individual-2 :\n" + individual2.toString() + "\n");
		System.out.println("Offspring of genetic.data.Individual-1 & Indiviual-2 :\n" + individual3.toString() + "\n");
	}
}