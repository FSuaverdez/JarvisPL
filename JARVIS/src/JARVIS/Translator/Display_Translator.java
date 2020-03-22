/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;

/**
 *
 * @author asus
 */
public class Display_Translator extends Translator
{

	private Display_Block block;
	
	public Display_Translator(Block block)
	{
		super(block);
				
		this.block = (Display_Block)block;
	}

	public String translate()
	{

		String str = "System.out.println(" + block.getDisp() + "); ";
		return str;
	}
}
