/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Parsers.JARVIS_Parser;
import JARVIS.Blocks.Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Other.BlockType;

/**
 *
 * @author JMDC
 */
public class Parse{
    
    private Block lastBlock;
    private String str;
    
    public Parse(String str)
    {
        this.str = str.trim();
    }
    
    public Block parse()
    {
        str = str.trim();
        if(str.isEmpty())
        {
            return null;
        }
        
        System.out.println("OOFED" + str);
        Parser parsed = new JARVIS_Parser(str);
        Block block = parsed.Parse();
        if(parsed.didParse())
        {
            str = parsed.getRemaining();
            System.out.println("OOF1" + str);
            return (lastBlock = (JARVIS_Block)block);
        }
        
        parsed = new Method_Parser(str,lastBlock);
        block = parsed.Parse();
        if(parsed.didParse() && lastBlock.getType() == BlockType.JARVIS)
        {
            str = parsed.getRemaining();
            System.out.println("OOF2" + str);
            return (lastBlock = (Method_Block)block);
        }
        
        throw new IllegalStateException("OH NO!" + str);
    }
    
    public boolean hasMoreBlocks()
    {
        str = str.trim();
        return (!str.isEmpty());
    }
    
    public BlockType getType()
    {
        return lastBlock.getType();
    }
}
