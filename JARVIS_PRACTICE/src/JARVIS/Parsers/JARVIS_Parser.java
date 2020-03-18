/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Other.BlockType;
import JARVIS.Parsers.Parser;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.TokenType;
import JARVIS.Tokenizer.Tokenizer;

/**
 *
 * @author JMDC
 */
public class JARVIS_Parser extends Parser{

    private String str;
    private Tokenizer tokenized;
    private JARVIS_Block lastBlock;
    private boolean didParse = false;
    
    public JARVIS_Parser(String str) {
        super(str);
        this.str = str;
    }

    @Override
    public JARVIS_Block Parse() 
    {
        String block;
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        if(first.getToken().equals("JARVIS"))
        {
            Token next = tokenized.nextToken();
            String name = next.getToken();
            if(next.getType() == TokenType.IDENTIFIER)
            {
                if(tokenized.hasNext())
                {
                    str = tokenized.getRemaining();
                    didParse = true;
                    block = "JARVIS " + name; 
                    return (lastBlock = new JARVIS_Block(null,name,block,BlockType.JARVIS));
                    
                }
                else
                {
                    didParse = true;
                    str = tokenized.getRemaining();
                    block = "JARVIS " + name; 
                    return (lastBlock = new JARVIS_Block(null,name,block,BlockType.JARVIS));
                }
            }
            else
            {
                didParse = false;
                return (lastBlock = new JARVIS_Block(null,null,null,BlockType.ERROR));
            }
        }
        else
        {
            didParse = false;
            return (lastBlock = new JARVIS_Block(null,null,null,BlockType.ERROR));
        }
    }
    
    @Override
    public String getRemaining()
    {
        return str;
    }
    
    @Override
    public boolean didParse()
    {
        return didParse;
    }
}
