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
        str = str.trim();
        
        st = new StringTokenizer(str,"\n");
    }
    
    public Block nextBlock()
    {
        if(str.isEmpty())
        {
            return (lastBlock = new Block(null,null,str,BlockType.EMPTY,null,null,null));
        }
        if(st.hasMoreTokens())
        {
            String block = st.nextToken();
            block = block.trim();
            
            Tokenizer tokenized = new Tokenizer(block);
            Token first = tokenized.nextToken();
            if(first.getToken().equals("JARVIS"))
            {
                Token next = tokenized.nextToken();
                String name = next.getToken();
                if(next.getType() == TokenType.IDENTIFIER)
                {
                    if(tokenized.hasNext())
                    {
                        next = tokenized.nextToken();
                        if(next.getToken().equals("{"))
                        {
                            
                            return (lastBlock = new Block(null,null,block,BlockType.JARVIS,null,null,name));
                        }
                        else
                        {
                            return (lastBlock = new Block(null,null,block,BlockType.ERROR,null,null,null));
                        }
                    }
                    else
                    {
                        return (lastBlock = new Block(null,null,block,BlockType.JARVIS,null,null,name));
                    }
                }
                else
                {
                    return (lastBlock = new Block(null,null,block,BlockType.ERROR,null,null,null));
                }
            }
            else if(first.getToken().equals("method"))
            {
                System.out.println("flag1");
                params = new ArrayList<Parameter>();
                Token next = tokenized.nextToken();
                String returnToken = next.getToken();
                DataType returnType = null;
                
                if(returnToken.equals("void"))
                {
                    returnType = DataType.VOID;
                }
                else if(returnToken.equals("int"))
                {
                    returnType = DataType.INT;
                }
                else if(returnToken.equals("float"))
                {
                    returnType = DataType.FLOAT;
                }
                else if(returnToken.equals("double"))
                {
                    returnType = DataType.DOUBLE;
                }
                else if(returnToken.equals("char"))
                {
                    returnType = DataType.CHAR;
                }
                else if(returnToken.equals("string"))
                {
                    returnType = DataType.STRING;
                }
                
                if(next.getType() == TokenType.DATA_TYPES)
                {
                    System.out.println("flag2");
                    next = tokenized.nextToken();
                    String name = next.getToken();
                    next = tokenized.nextToken();
                    System.out.println("THIS: " + next.getToken());
                    if(next.getToken().equals("("))
                    {
                        System.out.println("flag3");
                        int checkP = 1;
                        int checkC = 0;
                        while(tokenized.hasNext())
                        {
                            next = tokenized.nextToken();
                            if(next.getToken().equals(")") && checkP == 1)
                            {
                                System.out.println("flagE");
                                return (lastBlock = new Block(lastBlock,null,block,BlockType.METHOD,params,returnType,name));
                            }
                            else if(next.getToken().equals(",") && checkC == 1)
                            {
                                checkP = 0;
                                checkC = 0;
                                continue;
                            }
                            else if((next.getType() == TokenType.DATA_TYPES))
                            {
                                checkP = 1;
                                checkC = 1;
                                System.out.println("flag4");
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
                                    System.out.println("flag5");
                                    params.add(new Parameter(next.getToken(),type));
                                }
                                else
                                {
                                    return (lastBlock = new Block(lastBlock,null,block,BlockType.ERROR,null,null,null));
                                }
                            }
                            else
                                return (lastBlock = new Block(lastBlock,null,block,BlockType.ERROR,null,null,null));
                        }
                    }
                    else
                    {
                        return (lastBlock = new Block(lastBlock,null,block,BlockType.ERROR,null,null,null));
                    }
                }
                else
                    return (lastBlock = new Block(lastBlock,null,block,BlockType.ERROR,null,null,null));
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
        if(str == null){
            System.out.println("Flag4");
            return(false);
        }
        System.out.println("Flag5: " + st.hasMoreTokens());
        return (st.hasMoreTokens());
    }
    
    public String getType()
    {
        if(lastBlock != null)
        {
            switch(lastBlock.getType())
            {
                case JARVIS:
                    return "JARVIS";
    
                case METHOD:
                    return "METHOD";
    
                case FOR:
                    return "FOR LOOP";
    
                case WHILE:
                    return "WHILE LOOP";
    
                case DO:
                    return "DO WHILE LOOP";
    
                case IF:
                    return "IF/IF ELSE";
    
                case ELSE:
                    return "ELSE";
    
                case READ:
                    return "READ Fucntion";
    
                case DISPLAY:
                    return "DISPLAY FUNCTION";
    
                case ERROR:
                    return "SYNTAX ERROR";
    
                case EMPTY:
                    return "EMPTY CODE";
            }
        }
        return null;
    }
    
    
}
