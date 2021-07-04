package genetic.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Chromosome{
	private String chromosomeStrand1, chromosomeStrand2;
	private Gene genesOnStrand1, genesOnStrand2;
	private ArrayList<Character> bases;
	private HashMap<Character,Double> percentageOfBase;
	
	public Chromosome(){
		bases = new ArrayList<>();
		percentageOfBase = new HashMap<>();
		genesOnStrand1 = new Gene();
		genesOnStrand2 = new Gene();
	}
	
	public Chromosome(String strand1, String strand2){
		chromosomeStrand1 = strand1;
		chromosomeStrand2 = strand2;
		bases = new ArrayList<>();
		percentageOfBase = new HashMap<>();
		setBaseList();
		setPercentageOfBase();
		genesOnStrand1 = new Gene(strand1);
		genesOnStrand1 = new Gene(strand2);
	}
	
	public void setChromosome(String strand1, String strand2){
		chromosomeStrand1 = strand1;
		chromosomeStrand2 = strand2;
		bases = new ArrayList<>();
		setBaseList();
		setPercentageOfBase();
		genesOnStrand1 = new Gene(strand1);
		genesOnStrand1 = new Gene(strand2);
	}
	
	public void setBaseList(){
		for(char ch : chromosomeStrand1.toCharArray()){
			if(bases.isEmpty())
				bases.add(ch);
			else if(!bases.contains(ch))
				bases.add(ch);
		}
		for(char ch : chromosomeStrand2.toCharArray()){
			if(bases.isEmpty())
				bases.add(ch);
			else if(!bases.contains(ch))
				bases.add(ch);
		}
	}
	
	public void setPercentageOfBase(){
		for(char ch : bases){
			double percentage = (
								(noOfOccurrence(ch, chromosomeStrand1) + noOfOccurrence(ch, chromosomeStrand2))
								/ (2 * chromosomeStrand1.length()) ) * 100;
			percentageOfBase.put(ch, percentage);
		}
	}
	
	public String getOneRandomChromosomeStrand(){
		Random random = new Random();
		int index = random.nextInt(2);
		switch(index){
			case 0 : return chromosomeStrand1;
			case 1 : return chromosomeStrand2;
		}
		return "";
	}
	
	public void changeRandomBase(char base, int numberOfChangedBases){
		if(numberOfChangedBases == 0)
			return;
		Random random = new Random();
		int whichStrand = random.nextInt(3);
		int[][] indices = new int[numberOfChangedBases][2];
		for(int i = 0; i < indices.length; ++i){
			indices[i][0] = random.nextInt(chromosomeStrand1.length());
			indices[i][1] = random.nextInt(chromosomeStrand2.length());
		}
		switch (whichStrand){
			case 0 :chromosomeStrand1 = changeAtIndices(indices[0], chromosomeStrand1, base);
					break;
			case 1 :chromosomeStrand2 = changeAtIndices(indices[1], chromosomeStrand2, base);
					break;
			case 2 :chromosomeStrand1 = changeAtIndices(indices[0], chromosomeStrand1, base);
					chromosomeStrand2 = changeAtIndices(indices[1], chromosomeStrand2, base);
					break;
		}
		setBaseList();
		setPercentageOfBase();
	}
	
	public void removeRandomBase(){
		Random random = new Random();
		int index, whichStrand = random.nextInt(3);
		switch (whichStrand){
			case 0 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand1 = chromosomeStrand1.substring(0,index) + chromosomeStrand1.substring(index+1);
					break;
			case 1 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand2 = chromosomeStrand2.substring(0,index) + chromosomeStrand2.substring(index+1);
					break;
			case 2 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand1 = chromosomeStrand1.substring(0,index) + chromosomeStrand1.substring(index+1);
					index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand2 = chromosomeStrand2.substring(0,index) + chromosomeStrand2.substring(index+1);
					break;
		}
		setBaseList();
		setPercentageOfBase();
	}
	
	public void addRandomBase(char base){
		Random random = new Random();
		int index, whichStrand = random.nextInt(3);
		switch (whichStrand){
			case 0 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand1 = chromosomeStrand1.substring(0,index) + base + chromosomeStrand1.substring(index);
					break;
			case 1 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand2 = chromosomeStrand2.substring(0,index) + base + chromosomeStrand2.substring(index);
					break;
			case 2 :index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand1 = chromosomeStrand1.substring(0,index) + base + chromosomeStrand1.substring(index);
					index = random.nextInt(chromosomeStrand1.length());
					chromosomeStrand2 = chromosomeStrand2.substring(0,index) + base + chromosomeStrand2.substring(index);
					break;
		}
		setBaseList();
		setPercentageOfBase();
	}
	
	public static double calculateGeneticChange(){
		//TODO
		return 1.0;
	}
	
	private static double countDifference(){
		//TODO
		return 1.0;
	}
	
	private String changeAtIndices(int[] indices, String string, char ch){
		StringBuilder stringBuilder = new StringBuilder(string);
		for (int index : indices){
			stringBuilder.setCharAt(index, ch);
		}
		return stringBuilder.toString();
	}
	
	private double noOfOccurrence(char ch, String string){
		double count = 0;
		for(char c : string.toCharArray()){
			if(c == ch)
				++count;
		}
		return count;
	}
	
	@Override
	public String toString(){
		return "[" + chromosomeStrand1 + "],\t[" + chromosomeStrand2 + "]" + "\n"
				+ percentageOfBase.toString();
	}
}
