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
public class VarDeclaration_Parser extends Parser
{

    private Block superBlock;
    private String str;
    private String block = "";
    
    private Tokenizer tokenized;
    private VarDeclaration_Block lastBlock;
    private boolean didParse = false;
    
    public VarDeclaration_Parser(String str, Block superBlock) {
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
        String name;
        DataType varType = null;
        
        tokenized = new Tokenizer(str);
        Token first = tokenized.nextToken();
        
        if(first.getType() == TokenType.DATA_TYPES)
        {
            block += first.getToken();
            switch (first.getToken()) {
                case "void":
                    varType = DataType.VOID;
                    break;
                case "int":
                    varType = DataType.INT;
                    break;
                case "float":
                    varType = DataType.FLOAT;
                    break;
                case "double":
                    varType = DataType.DOUBLE;
                    break;
                case "char":
                    varType = DataType.CHAR;
                    break;
                case "string":
                    varType = DataType.STRING;
                    break;
                case "boolean":
                    varType = DataType.BOOLEAN;
                    break;
                default:
                    break;
            }
            Token next = tokenized.nextToken();
            if(next.getType() == TokenType.IDENTIFIER)
            {
                name = next.getToken();
                int chk = 0;
                Block hold = superBlock;
                while(superBlock != null)
                {
                    if(superBlock.getType() == BlockType.METHOD)
                    {
                        Method_Block temp = (Method_Block)superBlock;
                        ArrayList<Variables> var = temp.getVar();
                        for(Variables a: var)
                        {
                            if(a.getName().equals(name))
                            {
                                chk = 1;
                            }
                        }
                    }
                    else if(superBlock.getType() == BlockType.JARVIS)
                    {
                        JARVIS_Block temp = (JARVIS_Block)superBlock;
                        ArrayList<Variables> var = temp.getVar();
                        for(Variables a: var)
                        {
                            if(a.getName().equals(name))
                            {
                                chk = 1;
                            }
                        }
                    }
                    
                    superBlock = superBlock.getSuper();
                }
                
                superBlock = hold;
                if(chk == 0)
                {
                    block += " " + name;
                    next = tokenized.nextToken();
                    if(next.getToken().equals("=") && varType!=DataType.VOID)
                    {
                        block += " =";
                        next = tokenized.nextToken();
                        chk = 0;
                        hold = superBlock;
                        if(next.getType() == TokenType.IDENTIFIER)
                        {
                            while(superBlock != null)
                            {
                                if(superBlock.getType() == BlockType.METHOD)
                                {
                                    Method_Block temp = (Method_Block)superBlock;
                                    ArrayList<Variables> var = temp.getVar();
                                    for(Variables a: var)
                                    {
                                        if(a.getName().equals(next.getToken()))
                                        {
                                            if((varType == DataType.DOUBLE || varType == DataType.FLOAT) && (a.getType() == DataType.DOUBLE || a.getType() == DataType.FLOAT || a.getType() == DataType.INT))
                                                chk = 1;
                                            else if(varType == DataType.STRING && (a.getType() == DataType.STRING || a.getType() == DataType.CHAR))
                                                chk = 1;
                                            else if(varType == a.getType())
                                                chk = 1;
                                            break;
                                        }
                                    }
                                }
                                else if(superBlock.getType() == BlockType.JARVIS)
                                {
                                    JARVIS_Block temp = (JARVIS_Block)superBlock;
                                    ArrayList<Variables> var = temp.getVar();
                                    for(Variables a: var)
                                    {
                                        if(a.getName().equals(next.getToken()))
                                        {
                                            if((varType == DataType.DOUBLE || varType == DataType.FLOAT) && (a.getType() == DataType.DOUBLE || a.getType() == DataType.FLOAT || a.getType() == DataType.INT))
                                            chk = 1;
                                            else if(varType == DataType.STRING && (a.getType() == DataType.STRING || a.getType() == DataType.CHAR))
                                                chk = 1;
                                            else if(varType == a.getType())
                                                chk = 1;
                                            break;
                                        }
                                    }
                                }
                                superBlock = superBlock.getSuper();
                            }
                            
                            superBlock = hold;
                            if(chk==1)
                            {
                                System.out.println("OOF1");
                                block += " " + next.getToken();
                                str = tokenized.getRemaining();
                                didParse = true;
                                return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                            }
                            else
                            {
                                return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.ERROR,null,null));
                            }
                        }
                        else if(next.getType()==TokenType.INTEGER_LITERAL && (varType == DataType.INT || varType == DataType.FLOAT || varType == DataType.DOUBLE))
                        {
                            System.out.println("OOF2");
                            block += " " + next.getToken();
                            str = tokenized.getRemaining();
                            didParse = true;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                        }
                        else if(next.getType()==TokenType.DECIMAL_LITERAL && (varType == DataType.DOUBLE || varType == DataType.FLOAT))
                        {
                            System.out.println("OOF3");
                            block += " " + next.getToken();
                            str = tokenized.getRemaining();
                            didParse = true;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                        }
                        else if(next.getType()==TokenType.STRING_LITERAL && varType == DataType.STRING)
                        {
                            System.out.println("OOF4");
                            block += " \"" + next.getToken() + "\" ";
                            str = tokenized.getRemaining();
                            didParse = true;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                        }
                        else if(next.getType()==TokenType.CHAR_LITERAL && (varType == DataType.CHAR || varType == DataType.STRING))
                        {
                            System.out.println("OOF5");
                            block += " \'" + next.getToken() + "\' ";
                            str = tokenized.getRemaining();
                            didParse = true;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                        }
                        else if(next.getType()==TokenType.BOOLEAN_LITERAL && varType == DataType.BOOLEAN)
                        {
                            System.out.println("OOF6");
                            block += " " + next.getToken();
                            str = tokenized.getRemaining();
                            didParse = true;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                        }
                        else
                        {
                            didParse = false;
                            return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.ERROR,null,null));
                        }
                    }
                    else
                    {
                        System.out.println("OOF7");
                        str = tokenized.getRemaining();
                        didParse = true;
                        return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.VAR_DECLARATION,varType,name));
                    }
                }
                else
                {
                    didParse = false;
                    return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.ERROR,null,null));
                }
            }
            else
            {
                didParse = false;
                return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.ERROR,null,null));
            }
        }
        
        didParse = false;
        return (lastBlock = new VarDeclaration_Block(superBlock,block,BlockType.ERROR,null,null));
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
