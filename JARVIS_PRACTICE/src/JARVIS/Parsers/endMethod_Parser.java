/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.endMethod_Block;
import JARVIS.Other.BlockType;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.Tokenizer;

/**
 *
 * @author JMDC
 */
public class endMethod_Parser extends Parser{

    private Block superBlock;
    private String str;
    private String block = "";
    
    private Tokenizer tokenized;
    private endMethod_Block lastBlock;
    private boolean didParse = false;
    
    public endMethod_Parser(String str, Block superBlock) {
        super(str);
        this.str = str;
        this.superBlock = superBlock;
    }

    @Override
    public Block Parse() {
        str = str.trim();
        if(str.isEmpty())
        {
            didParse = false;
            return null;
        }
        
        String block = "";
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        
        if(first.getToken().equals("endMethod"))
        {
            str = tokenized.getRemaining();
            block += first.getToken();
            didParse = true;
            return (lastBlock = new endMethod_Block(superBlock,block,BlockType.ENDMETHOD));
        }
        
        didParse = false;
        return (lastBlock = new endMethod_Block(superBlock,block,BlockType.ERROR));
    }

    @Override
    public String getRemaining() {
        if(str.isEmpty())
            return "";
        return str;
    }

    @Override
    public boolean didParse() {
        return didParse;
    }
    
    
    
}
