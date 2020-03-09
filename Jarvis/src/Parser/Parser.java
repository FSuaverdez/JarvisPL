/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Tokenizer.Token;
import Tokenizer.TokenType;
import Tokenizer.Tokenizer;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public final class Parser {
    
    private String str;
    private StringTokenizer st;
    
    private Block lastBlock;
    private ArrayList<Parameter> params;
    
    public Parser(String str)
    {
        this.str = str;
        tokenize();
    }
    
    public void tokenize()
    {
        st = new StringTokenizer(str,"\\n");
    }
    
    public Block nextBlock()
    {
        str = str.trim();
        if(str.isEmpty())
        {
            System.out.println("wew");
            return (lastBlock = new Block(null,null,str,BlockType.EMPTY,null,null));
            
        }
 
        
        if(st.hasMoreTokens())
        {
            String block = st.nextToken();
            System.out.println("test1");
            block = block.trim();
            
            Tokenizer tokenized = new Tokenizer(block);
            Token first = tokenized.nextToken();
            if(first.getToken().equals("JARVIS"))
            {
                System.out.println("test2");
                Token next = tokenized.nextToken();
                if(next.getType() == TokenType.IDENTIFIER)
                {
                    System.out.println("test3");
                    if(tokenized.hasNext())
                    {
                        System.out.println("test");
                        next = tokenized.nextToken();
                        if(next.getToken().equals("{"))
                        {
                            
                            return (lastBlock = new Block(null,null,block,BlockType.JARVIS,null,null));
                        }
                        else
                        {
                            return (lastBlock = new Block(null,null,block,BlockType.ERROR,null,null));
                        }
                    }
                }
                else
                {
                    return (lastBlock = new Block(null,null,block,BlockType.ERROR,null,null));
                }
            }
            else if(first.getToken().equals("method"))
            {
                params = new ArrayList<Parameter>();
                Token next = tokenized.nextToken();
                if(next.getType() == TokenType.DATA_TYPES)
                {
                    next = tokenized.nextToken();
                    if(next.getToken().equals("\\("))
                    {
                        while(tokenized.hasNext())
                        {
                            next = tokenized.nextToken();
                            if(next.getToken().equals("\\)"))
                            {
                                break;
                            }
                            else if(next.getType() == TokenType.DATA_TYPES)
                            {
                                String paramType = next.getToken();
                                DataType type = null;
                                if(paramType.equals("void"))
                                {
                                    type = DataType.VOID;
                                }
                                else if(paramType.equals("int"))
                                {
                                    type = DataType.INT;
                                }
                                else if(paramType.equals("float"))
                                {
                                    type = DataType.FLOAT;
                                }
                                else if(paramType.equals("double"))
                                {
                                    type = DataType.DOUBLE;
                                }
                                else if(paramType.equals("char"))
                                {
                                    type = DataType.CHAR;
                                }
                                else if(paramType.equals("string"))
                                {
                                    type = DataType.STRING;
                                }
                                
                                next = tokenized.nextToken();
                                if(next.getType() == TokenType.IDENTIFIER)
                                {
                                    params.add(new Parameter(next.getToken(),type));
                                }
                            }
                        }
                    }
                }
            }
            else if(first.getToken().equals("for"))
            {
                
            }
            else if(first.getToken().equals("while"))
            {
                
            }
            else if(first.getToken().equals("do"))
            {
                
            }
            else if(first.getType() == TokenType.CONDITIONAL_STATEMENTS)
            {
                
            }
            
        }
        
        throw new IllegalStateException( "Invalid syntax: " + str);
    }
    
    public boolean hasNext()
    {
        return (!st.hasMoreTokens());
    }
    
    
    
}
