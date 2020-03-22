/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.endJARVIS_Block;

/**
 *
 * @author asus
 */
public class endJARVIS_Translator extends Translator
{

	private endJARVIS_Block block;
	
	public endJARVIS_Translator(Block block)
	{
		super(block);
				
		this.block = (endJARVIS_Block)block;
	}

	public String translate()
	{

		String str = "} ";
		return str;
	}
}