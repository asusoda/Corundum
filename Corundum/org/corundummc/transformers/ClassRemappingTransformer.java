package org.corundummc.transformers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Remaps references to Minecraft classes to a different class that extends the original class. Remaps decided by the
 * file in the jar with the path /corundum_resources/transformers/remappings.txt under the format "from_class:to_class"
 * EG. com.class.OneClass:org.template.OtherClass* 
 */
public class ClassRemappingTransformer extends BaseTransformer
{
	public Map<String, String> remaps = new HashMap<>();
	
	public ClassRemappingTransformer()
	{
		//Read the mappings
		InputStream remapsFile = ClassLoader.getSystemResourceAsStream("/corundum_resources/transformers/remappings.txt");
		
		Scanner remapScanner = new Scanner(remapsFile);
		
		while (remapScanner.hasNext())
		{
			String currentLine = remapScanner.nextLine();
			String[] segments = currentLine.split(":");
			remaps.put(segments[0], segments[1]);
		}
	}
	
	@Override
	byte[] transform(String className, byte[] classBytes)
	{
		//TODO: Implement the actual remapping behaviours.
		return new byte[0];
	}
}
