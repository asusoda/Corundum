package org.corundummc.transformers;

import javassist.ClassPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Where we internally register transformers.
 * 
 * @author Niadel 
 */
public class TransformerRegistry
{
	private boolean transformersRun = false;
	public List<BaseTransformer> transformers = new ArrayList<>();
	private static final ClassPool classPool = ClassPool.getDefault();
	
	/**
	 * Used to pass transformers the bytes of classes.
	 */
	private ClassLoader corundumLoader;
	
	void registerTransformer(BaseTransformer transformer)
	{
		transformers.add(transformer);
	}
	
	/**
	 * Should only be run by Corundum itself. 
	 */
	public void executeTransformers()
	{
		if (!transformersRun)
		{
			for (BaseTransformer transformer: transformers)
			{
				
			}
		}
	}
	
	static ClassPool getClassPool()
	{
		return classPool;
	}
}
