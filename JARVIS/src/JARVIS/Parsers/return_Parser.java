/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Blocks.VarDeclaration_Block;
import JARVIS.Blocks.return_Block;
import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;
import JARVIS.Other.Variables;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.TokenType;
import JARVIS.Tokenizer.Tokenizer;
import java.util.ArrayList;

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
            Method_Block tempM = (Method_Block)superBlock;
            if(next.getType() == TokenType.IDENTIFIER)
            {
                String name = next.getToken();
                Block hold = superBlock;
                int chk = 0;
                boolean isMain = false;
                boolean init = false;
                
                if(tempM.getName().equals("main"))
                    isMain = true;
                while(superBlock!=null)
                {
                    if(superBlock.getType() == BlockType.METHOD)
                    {
                        Method_Block temp = (Method_Block)superBlock;
                        
                        for(Block sub: superBlock.getSub())
                        {
                            if(sub.getType() == BlockType.VAR_DECLARATION)
                            {
                                VarDeclaration_Block b = (VarDeclaration_Block)sub;
                                if(b.getName().equals(next.getToken()))
                                {
                                    init = b.isInit();
                                    break;
                                }
                            }
                        }
                        
                        ArrayList<Variables> var = temp.getVar();
                        for(Variables a: var)
                        {
                            if(a.getName().equals(next.getToken()))
                            {
                                if((tempM.getRetType() == DataType.DOUBLE || tempM.getRetType() == DataType.FLOAT) && (a.getType() == DataType.DOUBLE || a.getType() == DataType.FLOAT || a.getType() == DataType.INT))
                                    chk = 1;
                                else if(tempM.getRetType() == DataType.STRING && (a.getType() == DataType.STRING || a.getType() == DataType.CHAR))
                                    chk = 1;
                                else if(tempM.getRetType() == a.getType())
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
                                if((tempM.getRetType() == DataType.DOUBLE || tempM.getRetType() == DataType.FLOAT) && (a.getType() == DataType.DOUBLE || a.getType() == DataType.FLOAT || a.getType() == DataType.INT))
                                    chk = 1;
                                else if(tempM.getRetType() == DataType.STRING && (a.getType() == DataType.STRING || a.getType() == DataType.CHAR))
                                    chk = 1;
                                else if(tempM.getRetType() == a.getType())
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
                    returnValue = next.getToken();
                    block += " " + next.getToken();

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
            else if(next.getType() == TokenType.CHAR_LITERAL && (tempM.getRetType() == DataType.CHAR || tempM.getRetType() == DataType.STRING))
            {
                returnValue = "'" + next.getToken() + "'";
                block += " '" + next.getToken() + "' ";
                str = tokenized.getRemaining();
                didParse = true;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.RETURN));
            }
            else if(next.getType() == TokenType.STRING_LITERAL && tempM.getRetType() == DataType.STRING)
            {
                returnValue = "\"" + next.getToken() + "\"";
                block += " \"" + next.getToken() + "\" ";
                str = tokenized.getRemaining();
                didParse = true;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.RETURN));
            }
            else if(next.getType() == TokenType.INTEGER_LITERAL && (tempM.getRetType() == DataType.INT || tempM.getRetType() == DataType.FLOAT || tempM.getRetType() == DataType.DOUBLE))                
            {
                returnValue = next.getToken();
                block += " " + next.getToken();
                str = tokenized.getRemaining();
                didParse = true;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.RETURN));
            }
            else if(next.getType() == TokenType.DECIMAL_LITERAL && (tempM.getRetType() == DataType.FLOAT || tempM.getRetType() == DataType.DOUBLE))
            {
                returnValue = next.getToken();
                block += " " + next.getToken();

                str = tokenized.getRemaining();
                didParse = true;
                return (lastBlock = new return_Block(superBlock, block, returnValue, BlockType.RETURN));
            }
            else if(next.getType() == TokenType.BOOLEAN_LITERAL && tempM.getRetType() == DataType.BOOLEAN)
            {
                returnValue = next.getToken();
                block += " " + next.getToken();

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
