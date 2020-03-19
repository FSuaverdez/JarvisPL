/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JARVIS.Other;

/**
 *
 * @author JMDC
 */
public class Variables {
    
    private String name;
    private DataType type;
    
    public Variables(DataType type, String name)
    {
        this.type = type;
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public DataType getType()
    {
        return type;
    }
}
