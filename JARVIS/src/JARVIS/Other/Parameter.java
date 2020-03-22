/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Other;

import JARVIS.Other.DataType;

/**
 *
 * @author asus
 */
public class Parameter {
    
    private DataType type;
    private String name;
    
    public Parameter(String name, DataType type)
    {
        this.type = type;
        this.name = name;
    }
    
    public DataType getType()
    {
        return type;
    }
    
    public String getName()
    {
        return name;
    }
    
}
