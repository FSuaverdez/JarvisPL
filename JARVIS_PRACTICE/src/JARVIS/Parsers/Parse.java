/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Parsers.JARVIS_Parser;
import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Other.BlockType;

/**
 *
 * @author JMDC
 */
public class Parse{
    
    private Block superBlock;
    private String str;
    private Block lastBlock;
    
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
        
        Parser parsed = new JARVIS_Parser(str);
        Block block = parsed.Parse();
        if(parsed.didParse() && superBlock == null)
        {
            str = parsed.getRemaining();
            lastBlock = (JARVIS_Block)block;
            return (superBlock = (JARVIS_Block)block);
        }
        
        parsed = new Method_Parser(str,superBlock);
        block = parsed.Parse();
        if(parsed.didParse() && superBlock.getType() == BlockType.JARVIS)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            lastBlock = (Method_Block)block;
            return (superBlock = (Method_Block)block);
        }
        
        parsed = new Display_Parser(str,superBlock);
        block = parsed.Parse();
        if(parsed.didParse() && superBlock.getType() == BlockType.METHOD)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            lastBlock = (Display_Block)block;
            return (superBlock = (Display_Block)block);
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
