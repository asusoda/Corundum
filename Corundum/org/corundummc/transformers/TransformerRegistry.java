package org.corundummc.transformers;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Where we internally register transformers.
 * 
 * @author Niadel 
 */
public final class TransformerRegistry
{
	/**
	 * Boolean used to make sure the transformers have not been accidentally been run twice. 
	 */
	private static boolean transformersRun = false;
	
	/** 
	 * The list of transformer that have been registered. 
	 */
	static List<BaseTransformer> transformers = new ArrayList<>();
	
	/**
	 * Javassist ClassPool object used to get the bytes of classes. 
	 */
	private static final ClassPool classPool = ClassPool.getDefault();
	
	/**
	 * Used to pass transformers the bytes of classes. TODO: Is this needed?
	 */
	private ClassLoader corundumLoader;
	
	/**
	 * The method used to register transformers. 
	 * @param transformer The transformer being registered.
	 */
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
				//TODO: transformer.transform()
			}
			
			transformersRun = true;
		}
	}
	
	/**
	 * Method that gets the bytes of a certain class. 
	 * @param className The class that you want the bytes of.
	 * @return The bytes of the class you requested.
	 */
	static byte[] getBytesOfClass(String className)
	{
		try
		{
			return classPool.get(className).toBytecode();
		}
		catch (NotFoundException | IOException | CannotCompileException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	static ClassPool getClassPool()
	{
		return classPool;
	}
}
