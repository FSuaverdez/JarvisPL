/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Blocks.VarDeclaration_Block;
import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;
import JARVIS.Other.Parameter;
import JARVIS.Other.Variables;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.TokenType;
import JARVIS.Tokenizer.Tokenizer;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class Display_Parser extends Parser{

    private Block superBlock;
    private String str;
    private String block = "";
    
    private Tokenizer tokenized;
    private Display_Block lastBlock;
    private boolean didParse = false;
    
    public Display_Parser(String str, Block superBlock) 
    {
        super(str);
        this.str = str;
        this.superBlock = superBlock;
    }

    @Override
    public Block Parse() 
    {
        str = str.trim();
        if(str.isEmpty())
        {
            didParse = false;
            return null;
        }
        
        String block = "";
        String toBeDisp = "";
        
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        
        if(first.getToken().equals("display"))
        {
            block += first.getToken();
            Token next = tokenized.nextToken();
            
            if(next.getToken().equals("("))
            {
                block += next.getToken();
                int chk = 1;
                int chk1 = 1;
                int startchk = 1;
                while(tokenized.hasNext())
                {
                    next = tokenized.nextToken();
                    if(next.getToken().equals(")") && chk == 1 && (startchk == 1 || chk1 == 0))
                    {
                        chk = 0;
                        block += next.getToken();
                        str = tokenized.getRemaining();
                        didParse = true;
                        return (lastBlock = new Display_Block(superBlock,block,BlockType.DISPLAY,toBeDisp));
                    }
                    else if(next.getToken().equals("+") && chk == 1 && chk1 == 0 && startchk == 0)
                    {
                        chk = 0;
                        chk1 = 1;
                        block += " " + next.getToken() + " ";
                        toBeDisp += " " + next.getToken() + " ";
                    }
                    else if(next.getType() == TokenType.STRING_LITERAL && chk1==1)
                    {
                        block += " \"" + next.getToken() + "\" ";
                        toBeDisp += " \"" + next.getToken() + "\" ";
                        chk = 1;
                        chk1 = 0;
                        startchk = 0;
                    }
                    else if(next.getType() == TokenType.IDENTIFIER && chk1 == 1)
                    {
                        block += next.getToken();
                        toBeDisp += next.getToken();
                        chk = 0;
                        Block hold = superBlock;
                        String name;
                        boolean isMain = false;
                        boolean init = false;
                        while(superBlock != null)
                        {
                            if(superBlock.getType() == BlockType.METHOD)
                            {
                                Method_Block temp = (Method_Block)superBlock;
                                if(temp.getName().equals("main"))
                                    isMain = true;
                                ArrayList<Variables> var = temp.getVar();
                                for(Variables a: var)
                                {
                                    if(a.getName().equals(next.getToken()))
                                    {
                                        chk = 1;
                                        init = a.getInit();
                                        break;
                                    }
                                }
                            }
                            else if(superBlock.getType() == BlockType.JARVIS && !isMain)
                            {
                                JARVIS_Block temp = (JARVIS_Block)superBlock;
                                ArrayList<Variables> var = temp.getVar();
                                for(Variables a: var)
                                {
                                    if(a.getName().equals(next.getToken()))
                                    {
                                        chk = 1;
                                        init = a.getInit();
                                        break;
                                    }
                                }
                            }
                            superBlock = superBlock.getSuper();
                        }
                        superBlock = hold;
                        
                        if(chk == 1 && init)
                        {
                            chk1 = 0;
                            startchk = 0;
                        }
                        else
                        {
                            return (lastBlock = new Display_Block(superBlock,null,BlockType.ERROR,null));
                        }
                    }
                    else
                    {
                        return (lastBlock = new Display_Block(superBlock,null,BlockType.ERROR,null));
                    }
                }
            }
            else
            {
                return (lastBlock = new Display_Block(superBlock,null,BlockType.ERROR,null));
            }
        }
        
        didParse = false;
        return (lastBlock = new Display_Block(superBlock,null,BlockType.ERROR,null));
    }

    @Override
    public String getRemaining() 
    {
        if(str.isEmpty())
            return "";
        return str;
    }

    @Override
    public boolean didParse() 
    {
        return didParse;
    }
    
    
    
}
