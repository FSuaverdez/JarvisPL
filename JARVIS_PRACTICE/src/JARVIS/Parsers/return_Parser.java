/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.endMethod_Block;
import JARVIS.Blocks.return_Block;
import JARVIS.Other.BlockType;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.TokenType;
import JARVIS.Tokenizer.Tokenizer;

/**
 *
 * @author JMDC
 */
public class return_Parser extends Parser
{

    private Block superBlock;
    private String str;
    private String block = "";
    
    private Tokenizer tokenized;
    private return_Block lastBlock;
    private boolean didParse = false;
    
    public return_Parser(String str,Block superBlock) {
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
        String returnValue = "";
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        
        if(first.getToken().equals("return"))
        {
            block += first.getToken();
            Token next = tokenized.nextToken();
            if(next.getType() == TokenType.IDENTIFIER || next.getType() == TokenType.CHAR_LITERAL || next.getType() == TokenType.STRING_LITERAL || next.getType() == TokenType.INTEGER_LITERAL || next.getType() == TokenType.DECIMAL_LITERAL || next.getType() == TokenType.BOOLEAN_LITERAL)
            {
                if(next.getType() == TokenType.STRING_LITERAL){
                    returnValue = "\"" + next.getToken() + "\"";
                    block += " \"" + next.getToken() + "\" ";
                }
                else if(next.getType() == TokenType.CHAR_LITERAL){
                    returnValue = "'" + next.getToken() + "'";
                    block += " '" + next.getToken() + "' ";
                }
                else{
                    returnValue = next.getToken();
                    block += " " + next.getToken();
                }
                str = tokenized.getRemaining();
                didParse = true;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.RETURN));
            }
            else
            {
                didParse = false;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.ERROR));
            }
        }
            didParse = false;
            return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.ERROR));
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
