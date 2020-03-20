/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Parsers;

import JARVIS.Blocks.Block;
import JARVIS.Blocks.JARVIS_Block;
import JARVIS.Blocks.Method_Block;
import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;
import JARVIS.Other.Parameter;
import JARVIS.Tokenizer.Token;
import JARVIS.Tokenizer.TokenType;
import JARVIS.Tokenizer.Tokenizer;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class Method_Parser extends Parser{

    private Block superBlock;
    private String str;
    private String block = "";
    
    private Tokenizer tokenized;
    private Method_Block lastBlock;
    private boolean didParse = false;
    private ArrayList<Parameter> params;
    
    public Method_Parser(String str, Block superBlock) {
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
        
        String block;
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        if(first.getToken().equals("method"))
        {
            params = new ArrayList<Parameter>();
            Token next = tokenized.nextToken();
            String returnToken = next.getToken();
            DataType returnType = null;

            switch (returnToken) {
                case "void":
                    returnType = DataType.VOID;
                    break;
                case "int":
                    returnType = DataType.INT;
                    break;
                case "float":
                    returnType = DataType.FLOAT;
                    break;
                case "double":
                    returnType = DataType.DOUBLE;
                    break;
                case "char":
                    returnType = DataType.CHAR;
                    break;
                case "string":
                    returnType = DataType.STRING;
                    break;
                case "boolean":
                    returnType = DataType.BOOLEAN;
                    break;
                default:
                    break;
            }

            if(next.getType() == TokenType.DATA_TYPES)
            {
                next = tokenized.nextToken();
                if(next.getType() == TokenType.IDENTIFIER)
                {
                    String name = next.getToken();
                    int chk = 0;
                    if(superBlock.getType() == BlockType.JARVIS)
                    {
                        JARVIS_Block temp = (JARVIS_Block)superBlock;
                        ArrayList<Block> sub = temp.getSub();
                        for(Block b: sub)
                        {
                            if(b.getType() == BlockType.METHOD)
                            {
                                Method_Block tempM = (Method_Block)b;
                                if(tempM.getName().equals(name))
                                    chk = 1;
                                System.out.println("OOF!! " + chk);
                            }
                        }
                    }
                    
                    if(chk == 0)
                    {
                        next = tokenized.nextToken();
                        if(next.getToken().equals("("))
                        {
                            int checkP = 1;
                            int checkC = 0;
                            block = "method " + returnToken + " " + name + "(";
                            while(tokenized.hasNext())
                            {
                                next = tokenized.nextToken();
                                if(next.getToken().equals(")") && checkP == 1)
                                {
                                    didParse = true;
                                    block += ")"; 
                                    str = tokenized.getRemaining();
                                    System.out.println("###### " + str);
                                    return (lastBlock = new Method_Block(superBlock,name,returnType,params,block,BlockType.METHOD));
                                }
                                else if(next.getToken().equals(",") && checkC == 1)
                                {
                                    block += ", ";
                                    checkP = 0;
                                    checkC = 0;
                                }
                                else if((next.getType() == TokenType.DATA_TYPES))
                                {
                                    checkP = 1;
                                    checkC = 1;
                                    block += next.getToken();
                                    String paramType = next.getToken();
                                    DataType type = null;
                                    switch (paramType) {
                                        case "void":
                                            type = DataType.VOID;
                                            break;
                                        case "int":
                                            type = DataType.INT;
                                            break;
                                        case "float":
                                            type = DataType.FLOAT;
                                            break;
                                        case "double":
                                            type = DataType.DOUBLE;
                                            break;
                                        case "char":
                                            type = DataType.CHAR;
                                            break;
                                        case "string":
                                            type = DataType.STRING;
                                            break;
                                        case "boolean":
                                            type = DataType.BOOLEAN;
                                            break;
                                        default:
                                            break;
                                    }

                                    next = tokenized.nextToken();
                                    if(next.getType() == TokenType.IDENTIFIER)
                                    {
                                        block += " " + next.getToken();
                                        params.add(new Parameter(next.getToken(),type));
                                    }
                                    else
                                    {
                                        return (lastBlock = new Method_Block(superBlock,name,returnType,null,block,BlockType.ERROR));
                                    }
                                }
                                else
                                {
                                    didParse = false;
                                    return (lastBlock = new Method_Block(superBlock,name,returnType,null,block,BlockType.ERROR));
                                }
                            }
                        }
                        else
                        {
                            didParse = false;
                            return (lastBlock = new Method_Block(superBlock,name,returnType,null,null,BlockType.ERROR));
                        }
                    }
                    else
                    {
                        didParse = false;
                        return (lastBlock = new Method_Block(superBlock,null,null,null,null,BlockType.ERROR));
                    }
                }
                else
                {
                    didParse = false;
                    return (lastBlock = new Method_Block(superBlock,null,null,null,null,BlockType.ERROR));
                }
            }
            else
            {
                didParse = false;
                return (lastBlock = new Method_Block(superBlock,null,null,null,null,BlockType.ERROR));
            }
        }
        
        didParse = false;
        return (lastBlock = new Method_Block(superBlock,null,null,null,null,BlockType.ERROR));
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
