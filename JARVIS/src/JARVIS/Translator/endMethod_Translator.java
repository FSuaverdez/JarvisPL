/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.endMethod_Block;

/**
 *
 * @author asus
 */
public class endMethod_Translator extends Translator
{

	private endMethod_Block block;
	
	public endMethod_Translator(Block block)
	{
		super(block);
				
		this.block = (endMethod_Block)block;
	}

	public String translate()
	{

		String str = "} ";
		return str;
	}
}
