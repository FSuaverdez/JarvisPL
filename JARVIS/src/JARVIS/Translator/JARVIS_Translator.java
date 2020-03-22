/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Translator;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.JARVIS_Block;

/**
 *
 * @author asus
 */
public class JARVIS_Translator extends Translator
{

    private JARVIS_Block block;

    public JARVIS_Translator(Block block)
    {
        super(block);
        this.block = (JARVIS_Block) block;
    }

    public String translate()
    {
        String str = "public class " + block.getName() + " {";
        return str;
    }
}
