/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Blocks;

import JARVIS.Other.BlockType;
import JARVIS.Other.DataType;
import JARVIS.Other.Parameter;
import JARVIS.Other.Variables;
import java.util.ArrayList;

/**
 *
 * @author JMDC
 */
public class Method_Block extends Block{
    
    private String name;
    private String block;
    private DataType returnType;
    private BlockType type;
    private ArrayList<Parameter> params;
    private Block returnBlock;
    private ArrayList<Variables> var;
    private boolean returned = false;
    private String code = "0001";
    
    public Method_Block(Block superBlock, String name, DataType returnType, ArrayList<Parameter> parameters, String block, BlockType type) 
    {
        super(superBlock, block, type);
        
        this.block = block;
        this.type = type;
        this.name = name;
        this.returnType =  returnType;
        this.params = parameters;
        this.var = new ArrayList<>();
        
        if(returnType == DataType.VOID)
            this.returned = true;
        
        if(parameters!=null)
        {
            for(Parameter a: parameters)
            {
                var.add(new Variables(a.getType(),a.getName(),false));
            }
        }
    }
    
    public String getCode(){
        return code;
    }
    
    public void setSubBlock(Block subBlock)
    {
        super.setSub(subBlock);
    }

    @Override
    public BlockType getType() 
    {
        return type;
    }

    @Override
    public String getBlock() 
    {
        return block;
    }
    
    public Block getRet()
    {
        return returnBlock;
    }
    
    public void setRet(Block returnBlock)
    {
        this.returned = true;
        this.returnBlock = returnBlock;
    }
    
    public void addVar(Variables var)
    {
        this.var.add(var);
    }
    
    public ArrayList<Variables> getVar()
    {
        return var;
    }
    
    public DataType getRetType()
    {
        return returnType;
    }
    
    public boolean returned()
    {
        return returned;
    }
    
    public String getName()
    {
        return name;
    }
    
    public ArrayList<Parameter> getParams()
    {
        return params;
    }
    
}
