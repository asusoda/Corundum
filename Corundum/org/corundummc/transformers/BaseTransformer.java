package org.corundummc.transformers;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * The base for all transformers.
 */
public abstract class BaseTransformer
{
	abstract byte[] transform(String className, byte[] classBytes);
	
	final byte[] getBytesOfClass(String className)
	{
		byte[] bytes = null;
		
		try
		{
			bytes = TransformerRegistry.getClassPool().get(className).toBytecode();
		}
		catch (NotFoundException | IOException | CannotCompileException e)
		{
			e.printStackTrace();
		}
		
		return bytes;
	}
}
