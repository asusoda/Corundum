package org.corundummc.transformers;

import java.util.ArrayList;
import java.util.List;

/**
 * Where we internally register transformers.
 * 
 * @author Niadel 
 */
public class TransformerRegistry
{
	public List<BaseTransformer> transformers = new ArrayList<>();
	
	void registerTransformer(BaseTransformer transformer)
	{
		transformers.add(transformer);
	}
}
