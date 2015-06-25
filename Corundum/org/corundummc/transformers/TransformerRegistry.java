package org.corundummc.transformers;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.corundummc.hub.CorundumJarLoader;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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
	private CorundumJarLoader corundumLoader;
	
	/**
	 * Loaded classes from MC. There for efficiency so we don't have to call classPool.get() every time. 
	 */
	private static Map<String, byte[]> locatedMcClasses = new HashMap<>();
	
	public TransformerRegistry(CorundumJarLoader classLoader)
	{
		this.corundumLoader = classLoader;
	}
	
	/**
	 * The method used to register transformers. 
	 * @param transformer The transformer being registered.
	 */
	void registerTransformer(BaseTransformer transformer)
	{
		transformers.add(transformer);
	}
	
	private void executeTransformer(String className, byte[] classBytes)
	{
		for (BaseTransformer transformer: transformers)
		{
			transformer.transform(className, classBytes);
		}
	}
	
	/**
	 * Should only be run by Corundum itself. 
	 */
	public void executeRegisteredTransformers()
	{
		try
		{
			if (!transformersRun)
			{
				ZipFile serverJar = new ZipFile("minecraft_server.jar");
				//Because this is the only saneish way to read zip files recursively .-. Why couldn't someone have a 
				//.toList() method for enumerations? It's a better method than what was originally going to be used,
				//though. That involved ClassLoader shenanigans.
				Enumeration entries = serverJar.entries();
				
				while (entries.hasMoreElements())
				{
					ZipEntry entry = (ZipEntry) entries.nextElement();
					InputStream stream = serverJar.getInputStream(entry);
					//Yay, Minecraft has Apache IO as a library in the server.jar :D!
					byte[] bytes = IOUtils.toByteArray(stream);
					
					//Locate class name
					String name = entry.getName().replace(File.separator, ".");
					
					locatedMcClasses.put(name, bytes);
					
					this.corundumLoader.defineClass(name, bytes);
				}
				
				transformersRun = true;
			}
		}
		catch (IOException e)
		{
			System.err.println("INTERNAL ERROR: IOE occured during the loading of transformers. Read stacktrace for details");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that gets the bytes of a certain class. 
	 * @param className The class that you want the bytes of.
	 * @return The bytes of the class you requested.
	 */
	static byte[] getBytesOfClass(String className)
	{
		//Security things :P
		if (!className.startsWith("org.corundummc"))
		{
			if (!locatedMcClasses.keySet().contains(className))
			{
				try
				{
					return classPool.get(className).toBytecode();
				}
				catch (NotFoundException | IOException | CannotCompileException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				return locatedMcClasses.get(className);
			}
		}
		
		return null;
	}
	
	static ClassPool getClassPool()
	{
		return classPool;
	}
}
