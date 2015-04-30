package org.corundummc.transformers;

/**
 * The base for all transformers.
 */
public abstract class BaseTransformer
{
	/**
	 * Where you do the actual transforming of the class. Please avoid getting the bytes of any class other than the
	 * one specified by className unless it is ABSOLUTELY necessary. 
	 * @param className The fully qualified name of the class that classBytes is the bytes of.
	 * @param classBytes The bytes of the class className describes.
	 * @return The transformed bytes.
	 */
	abstract byte[] transform(String className, byte[] classBytes);
	
	/**
	 * Avoid using where possible.
	 * @param className The class you want the bytes of.
	 * @return The bytes of the class you requested.
	 */
	final byte[] getBytesOfClass(String className)
	{
		return TransformerRegistry.getBytesOfClass(className);
	}
}
