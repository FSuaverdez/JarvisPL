/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Parsers.JARVIS_Parser;
import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;
import JARVIS.Blocks.ERROR_Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Blocks.endJARVIS_Block;
import JARVIS.Blocks.endMethod_Block;
import JARVIS.Blocks.return_Block;
import JARVIS.Other.BlockType;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class Parse {
    
    private Block superBlock;
    private String str;
    private Block lastBlock;
    private boolean didParse = true;
    
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
        didParse = parsed.didParse();
        if(didParse && superBlock == null)
        {
            str = parsed.getRemaining();
            superBlock = (JARVIS_Block)block;
            return (lastBlock = (JARVIS_Block)block);
        }
        
        parsed = new Method_Parser(str,superBlock);
        block = parsed.Parse();
        didParse = parsed.didParse();
        if(didParse && superBlock.getType() == BlockType.JARVIS)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            superBlock = (Method_Block)block;
            return (lastBlock = (Method_Block)block);
        }
        
        parsed = new Display_Parser(str,superBlock);
        block = parsed.Parse();
        didParse = parsed.didParse();
        if(didParse && superBlock.getType() == BlockType.METHOD)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            lastBlock = (Display_Block)block;
            return (lastBlock = (Display_Block)block);
        }
        
        parsed = new endMethod_Parser(str,superBlock);
        block = parsed.Parse();
        didParse = parsed.didParse();
        if(didParse && superBlock.getType() == BlockType.METHOD)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            superBlock = superBlock.getSuper();
            return (lastBlock = (endMethod_Block)block);
        }
        
        parsed = new endJARVIS_Parser(str,superBlock);
        block = parsed.Parse();
        didParse = parsed.didParse();
        if(didParse && superBlock.getType() == BlockType.JARVIS)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            superBlock = superBlock.getSuper();
            return (lastBlock = (endJARVIS_Block)block);
        }
        
        parsed = new return_Parser(str,superBlock);
        block = parsed.Parse();
        didParse = parsed.didParse();
        if(didParse && superBlock.getType() == BlockType.METHOD)
        {
            str = parsed.getRemaining();
            superBlock.setSub(block);
            Method_Block temp = (Method_Block)superBlock;
            temp.setRet(block);
            superBlock = (Block)temp;
            return (lastBlock = (return_Block)block);
        }
        didParse = false;
        return (lastBlock = new ERROR_Block(superBlock,str,BlockType.ERROR));
    }
    
    
    public boolean hasMoreBlocks()
    {
        str = str.trim();
        if(didParse == true)
            return (!str.isEmpty());
        return didParse;
    }
    
    public BlockType getType()
    {
        return lastBlock.getType();
    }
}
