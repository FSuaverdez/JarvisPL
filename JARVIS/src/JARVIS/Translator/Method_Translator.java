/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Other.DataType;
import JARVIS.Other.Parameter;

/**
 *
 * @author asus
 */
public class Method_Translator extends Translator
{

	private Method_Block block;
	
	public Method_Translator(Block block)
	{
		super(block);
				
		this.block = (Method_Block)block;
	}

	public String translate()
	{

		String str = "public ";
		
		DataType type = block.getRetType();
		String t = "";
		
		switch(type)
		{
			case STRING:
				t = "String ";
				break;
			case CHAR:
				t = "char ";
				break;
			case BOOLEAN:
				t = "boolean";
				break;
			case INT:
				t = "int ";
				break;
			case FLOAT:
				t = "float ";
				break;
			case DOUBLE:
				t = "double ";
				break;
			case VOID:
				t = "void ";
				break;
			default:
				break;
		}
		
		str += t + " ";
		String name = block.getName();
		if(name.equals("main"))
		{
			return (str = "public static " + t + " " + name + " (String[] args){");
		}

		str += name + " (";
		boolean first = true;
		
		for(Parameter params: block.getParams())
		{
			if(first)
				first = false;
			else
				str += ", ";

			type = params.getType();
			switch(type)
			{
				case STRING:
					t = "String ";
					break;
				case CHAR:
					t = "char ";
					break;
				case BOOLEAN:
					t = "boolean";
					break;
				case INT:
					t = "int ";
					break;
				case FLOAT:
					t = "float ";
					break;
				case DOUBLE:
					t = "double ";
					break;
				case VOID:
					t = "void ";
					break;
				default:
					break;
			}

			str += t + " " + params.getName();
		}

		return str += "){ ";
	}
}