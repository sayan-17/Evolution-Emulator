import genetic.data.UniversalConstantParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reader {
	
	public void readData(String fileName) throws Exception {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		ArrayList<String> dataCode = new ArrayList<>();
		Pattern pattern = Pattern.compile("^\".+\" . \".+\"$" ,Pattern.CASE_INSENSITIVE);
		while (scanner.hasNextLine()){
			String line = scanner.nextLine().trim();
			if (pattern.matcher(line).matches()){
				dataCode.add(line);
			} else {
				throw new Exception("Error in Data file");
			}
		}
		validateAndStoreData(dataCode);
	}
	
	private void validateAndStoreData(ArrayList<String> dataCode) throws Exception {
		HashMap<String,String> items = new HashMap<>();
		for(String dataCodeLine : dataCode){
			int indexOfSeparator = findSeparatorIndex(dataCodeLine);
			if(indexOfSeparator == -1){
				throw new Exception("Error in Data file");
			}
			String key = dataCodeLine.substring(dataCodeLine.indexOf("\"") + 1,
					dataCodeLine.lastIndexOf("\"", indexOfSeparator)).trim();
			String val = dataCodeLine.substring(dataCodeLine.indexOf("\"",indexOfSeparator) + 1,
					dataCodeLine.lastIndexOf("\"")).trim();
			items.put(key, val);
		}
		setAllParameters(items);
	}
	
	private void setAllParameters(HashMap<String, String> items){
		UniversalConstantParameters constantParameters = new UniversalConstantParameters(
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[0]),
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[1]),
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[2]),
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[3]),
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[4]),
				items.get(UniversalConstantParameters.UNIVERSAL_CONSTANTS_LIST[5])
		);
	}
	
	private int findSeparatorIndex(String dataCodeLine){
		int count = 0, startIndex = 0, endIndex = 0;
		boolean dummy = true;
		for(int i = 0; i < dataCodeLine.length(); ++i){
			if(dataCodeLine.charAt(i) == '\"')
				++count;
			if(count == 2 && dummy) {
				startIndex = i + 1;
				dummy = false;
			}
			else if(count == 3){
				endIndex = i;
				break;
			}
		}
		if(startIndex == 0 && endIndex == 0 && startIndex < dataCodeLine.length())
			return -1;
		return dataCodeLine.indexOf(dataCodeLine.substring(startIndex, endIndex).trim());
	}
}
