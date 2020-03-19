/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;
import JARVIS.Blocks.ERROR_Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Blocks.VarDeclaration_Block;
import JARVIS.Blocks.endJARVIS_Block;
import JARVIS.Blocks.endMethod_Block;
import JARVIS.Blocks.return_Block;
import JARVIS.Other.BlockType;
import JARVIS.Other.Variables;
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
        Parser parsed;
        Block block;
        if(superBlock == null)
        {
            parsed = new JARVIS_Parser(str);
            block = parsed.Parse();
            didParse = parsed.didParse();
            System.out.println("@@JARVIS " + block.getBlock() + " " +didParse);
            if(didParse && superBlock == null)
            {
                str = parsed.getRemaining();
                superBlock = (JARVIS_Block)block;
                return (lastBlock = (JARVIS_Block)block);
            }
        }
        else
        {
            parsed = new Method_Parser(str,superBlock);
            block = parsed.Parse();
            didParse = parsed.didParse();
            System.out.println("@@method " + block.getBlock() + " " +didParse);
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
            System.out.println("@@disp " + block.getBlock() + " " +didParse);
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
            System.out.println("@@endm " + block.getBlock() + " " +didParse);
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
            System.out.println("@@endj " + block.getBlock() + " " +didParse);
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
            System.out.println("@@ret " + block.getBlock() + " " +didParse);
            if(didParse && superBlock.getType() == BlockType.METHOD)
            {
                str = parsed.getRemaining();
                superBlock.setSub(block);
                Method_Block temp = (Method_Block)superBlock;
                temp.setRet(block);
                superBlock = (Block)temp;
                return (lastBlock = (return_Block)block);
            }

            parsed = new VarDeclaration_Parser(str,superBlock);
            block = parsed.Parse();
            didParse = parsed.didParse();
            System.out.println("@@dec " + block.getBlock() + " " + didParse);
            if(didParse && (superBlock.getType() == BlockType.METHOD || superBlock.getType() == BlockType.JARVIS))
            {
                str = parsed.getRemaining();
                superBlock.setSub(block);
                JARVIS_Block tempJ;
                Method_Block tempM;
                VarDeclaration_Block tempV = (VarDeclaration_Block)block;
                if(superBlock.getType() == BlockType.JARVIS)
                {
                    tempJ = (JARVIS_Block)superBlock;
                    tempJ.addVar(new Variables(tempV.getVarType(),tempV.getName()));
                }
                else
                {
                    tempM = (Method_Block)superBlock;
                    tempM.addVar(new Variables(tempV.getVarType(),tempV.getName()));
                }

                return (lastBlock = (VarDeclaration_Block)block);
            }
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
