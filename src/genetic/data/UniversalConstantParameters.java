package genetic.data;

import java.util.ArrayList;

public class UniversalConstantParameters {
	
	public static final String[] UNIVERSAL_CONSTANTS_LIST = {"bases", "start-codon", "stop-codon",
			"codon-length", "probability-of-mutation", "threshold-for-new-species"};
	
	public static int codonLength;
	public static char[] bases;
	public static double thresholdForNewSpecies, probabilityOfMutation;
	public static String startCodon;
	public static String[] stopCodon;
	
	public UniversalConstantParameters(String bases, String startCodon, String stopCodon, String codonLength,
	                                   String probabilityOfMutation, String thresholdForNewSpecies){
		UniversalConstantParameters.bases = bases.toCharArray();
		UniversalConstantParameters.startCodon = startCodon;
		UniversalConstantParameters.stopCodon = parseStringWithCommas(stopCodon);
		UniversalConstantParameters.codonLength = Integer.parseInt(codonLength);
		UniversalConstantParameters.probabilityOfMutation = probabilityOfMutation == null ?
															0.0 : Double.parseDouble(probabilityOfMutation);
		UniversalConstantParameters.thresholdForNewSpecies = thresholdForNewSpecies == null ?
																0 : Double.parseDouble(thresholdForNewSpecies);
	}
	
	private String[] parseStringWithCommas(String string){
		ArrayList<String> parts = new ArrayList<>();
		int current = 0;
		while(true){
			int indexOfComma = string.indexOf(",", current);
			if(indexOfComma == -1){
				parts.add(string.substring(current));
				break;
			}
			parts.add(string.substring(current, indexOfComma));
			current = indexOfComma + 1;
		}
		String[] stringArray = new String[parts.size()];
		for(int i = 0; i < parts.size(); ++i){
			stringArray[i] = parts.get(i);
		}
		return stringArray;
	}
}
