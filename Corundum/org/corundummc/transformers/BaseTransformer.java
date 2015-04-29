package org.corundummc.transformers;

/**
 * The base for all transformers.
 */
public abstract class BaseTransformer
{
	abstract byte[] transform(String className, byte[] classBytes);
	
	final byte[] getBytesOfClass(String className)
	{
		return TransformerRegistry.getBytesOfClass(className);
	}
}
