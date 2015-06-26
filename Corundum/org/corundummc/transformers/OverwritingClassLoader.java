package org.corundummc.transformers;

import org.corundummc.hub.CorundumJarLoader;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Used solely so classes transformed are actually transformed while not giving access to transforming behaviours to
 * plugins.
 */
public class OverwritingClassLoader extends CorundumJarLoader
{
	public OverwritingClassLoader(File file, ClassLoader parent) throws MalformedURLException
	{
		super(file, parent);
	}
	
	void defineClass(String name, byte[] bytes)
	{
		if (!name.startsWith("org.corundummc"))
		{
			super.defineClass(name, bytes, 0, bytes.length);
		}
	}
}
