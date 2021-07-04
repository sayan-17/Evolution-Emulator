package genetic.data;

import java.util.ArrayList;

public class Gene {
	
	ArrayList<String> genes;
	
	public Gene(){
		genes = new ArrayList<>();
	}
	
	public Gene(String chromosomeStrand){
		genes = new ArrayList<>();
		storeGenes(chromosomeStrand);
	}
	
	public void setGenes(String chromosomeStrand){
		storeGenes(chromosomeStrand);
	}
	
	private int findStartCodon (int startIndex, String chromosomeStrand){
		return chromosomeStrand.indexOf(UniversalConstantParameters.startCodon, startIndex);
	}
	
	private int findStopCodon (int startIndex, String stopCodon, String chromosomeStrand){
		if (chromosomeStrand.isEmpty())
			return -1;
		int len, endIndex, cursor = startIndex + 3;
		Boolean checkFlag = true;
		len = chromosomeStrand.length();
		cursor = startIndex;
		while (len - cursor >= (6-1)){
			endIndex = chromosomeStrand.indexOf(stopCodon, cursor + UniversalConstantParameters.codonLength);
			if (endIndex == -1){
				checkFlag = false;
				break;
			}
			if ((endIndex - startIndex) % UniversalConstantParameters.codonLength == 0)
				return endIndex;
			cursor = endIndex + UniversalConstantParameters.codonLength;
		}
		if (checkFlag)
			return cursor;
		return -1;
	}
	
	private int[] getAllStopCodonIndices(int startIndex, String chromosomeStrand){
		int[] indices = new int[UniversalConstantParameters.stopCodon.length];
		for(String stopCodon : UniversalConstantParameters.stopCodon){
			findStopCodon(startIndex, stopCodon, chromosomeStrand);
		}
		return indices;
	}
	
	private String findGene (int cursor, String chromosomeStrand){
		if (chromosomeStrand.isEmpty())
			return "";
		int startIndex, minimum;
		startIndex = findStartCodon (cursor, chromosomeStrand);
		if (startIndex == -1)
			return "";
		int[] endIndices = getAllStopCodonIndices(startIndex, chromosomeStrand);
		minimum = endIndices[0];
		for(int index : endIndices){
			if(index == -1)
				continue;
			else if(minimum == -1 && index != -1){
				minimum = index;
			}
			else if(index < minimum){
				minimum = index;
			}
		}
		if(minimum != -1){
			return chromosomeStrand.substring (startIndex, minimum + 3);}
		else
			return "";
	}
	
	private void storeGenes(String chromosomeStrand){
		int cursor = 0;
		while (true){
			String currentGene = findGene (cursor, chromosomeStrand);
			if (currentGene.isEmpty()){
				break;
			}
			cursor = chromosomeStrand.indexOf(currentGene, cursor) + currentGene.length();
			genes.add(currentGene);
		}
	}
}
