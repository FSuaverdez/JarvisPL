/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.return_Block;

/**
 *
 * @author asus
 */
public class return_Translator extends Translator
{

	private return_Block block;
	
	public return_Translator(Block block)
	{
		super(block);
				
		this.block = (return_Block)block;
	}

	public String translate()
	{

		String str = block.getBlock() + "; ";
		return str;
	}
}