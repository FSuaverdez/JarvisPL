/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.Display_Block;
import JARVIS.Other.BlockType;
import JARVIS.Other.Parameter;
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
        System.out.println("OOF1");
        String block = "";
        String toBeDisp = "";
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        if(first.getToken().equals("display"))
        {
            System.out.println("OOF2");
            block += first.getToken();
            Token next = tokenized.nextToken();
            if(next.getToken().equals("("))
            {
                System.out.println("OOF3");
                block += next.getToken();
                int chk = 1;
                int chk1 = 1;
                int startchk = 1;
                while(tokenized.hasNext())
                {
                    next = tokenized.nextToken();
                    if(next.getToken().equals(")") && chk == 1 && (startchk == 1 || chk1 == 0))
                    {
                        System.out.println("OOF5");
                        chk = 0;
                        block += next.getToken();
                        str = tokenized.getRemaining();
                        didParse = true;
                        return (lastBlock = new Display_Block(superBlock,block,BlockType.DISPLAY,toBeDisp));
                    }
                    else if(next.getToken().equals("+") && chk == 1 && chk1 == 0 && startchk == 0)
                    {
                        System.out.println("OOF6");
                        chk = 0;
                        chk1 = 1;
                        block += " " + next.getToken() + " ";
                        toBeDisp += " \"" + next.getToken() + "\" ";
                    }
                    else if(next.getType() == TokenType.STRING_LITERAL && chk1==1)
                    {
                        System.out.println("OOF7");
                        block += " \"" + next.getToken() + "\" ";
                        toBeDisp += " \"" + next.getToken() + "\" ";
                        chk = 1;
                        chk1 = 0;
                        startchk = 0;
                    }
                    else if(next.getType() == TokenType.IDENTIFIER && chk1 == 1)
                    {
                        System.out.println("OOF8");
                        block += next.getToken();
                        toBeDisp += next.getToken();
                        chk = 1;
                        chk1 = 0;
                        startchk = 0;
                    }
                    else
                    {
                        System.out.println("OOFed1");
                        return (lastBlock = new Display_Block(superBlock,null,BlockType.ERROR,null));
                    }
                }
            }
            else
            {
                System.out.println("OOFed2");
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
