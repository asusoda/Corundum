package org.corundummc.transformers;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Remaps references to Minecraft classes to a different class that extends the original class. Remaps decided by the
 * file in the jar with the path /corundum_resources/transformers/remappings.txt under the format "from_class:to_class"
 * EG. com.class.OneClass:org.template.OtherClass* 
 */
public class ClassRemappingTransformer extends BaseTransformer implements Opcodes
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
		ClassReader classReader = new ClassReader(classBytes);
		ClassNode classNode = new ClassNode();
		classReader.accept(classNode, 0);
		
		for (MethodNode methodNode : (List<MethodNode>) classNode.methods)
		{
			for (AbstractInsnNode instruction : methodNode.instructions.toArray())
			{
				//Variables
				if (instruction instanceof FieldInsnNode)
				{
					FieldInsnNode insn = (FieldInsnNode) instruction;
					
					redirectVariableReference(insn, classBytes, className);
				}
				//Variable load calls
				else if (instruction instanceof LdcInsnNode)
				{
					LdcInsnNode insn = (LdcInsnNode) instruction;
					
					redirectLdcNode(insn, classBytes, className);
				}
				//Method calls
				else if (instruction instanceof MethodInsnNode)
				{
					MethodInsnNode insn = (MethodInsnNode) instruction;
					
					redirectMethodNode(insn, classBytes, className);
				}
			}
		}
		
		return new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES).toByteArray();
	}
	
	public void redirectVariableReference(FieldInsnNode insn, byte[] bytes, String className)
	{
		
	}
	
	public void redirectLdcNode(LdcInsnNode insn, byte[] bytes, String className)
	{
		
		
	}
	
	public void redirectMethodNode(MethodInsnNode insn, byte[] bytes, String className)
	{
		
		
	}
}
