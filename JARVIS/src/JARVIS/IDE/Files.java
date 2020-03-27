package JARVIS.IDE;


import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public class Files
{
    private String filename;
    private String fileContent;
    private String fileDirectory;
    private boolean saved;
    
    public Files(String filename, String fileContent,String fileDirectory){
        saved = false;
        this.filename = filename;
        this.fileContent = fileContent;
        this.fileDirectory = fileDirectory;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getFileContent()
    {
        return fileContent;
    }

    public void setFileContent(String fileContent)
    {
        this.fileContent = fileContent;
    }

    public String getFileDirectory()
    {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory)
    {
        this.fileDirectory = fileDirectory;
    }
    
    public boolean isSaved(){
        return saved;
    }
    
    public void Saved(){
        saved = true;
    }
    
    public void setSaved(boolean isSaved){
        saved = isSaved;
    }
    
    
    

    
    
    
}
