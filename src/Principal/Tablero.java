/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Excepcions.RangoMatrizInvalidoException;
import Vista.Vista_Tablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumnos
 */
public class Tablero implements ActionListener{
    
  private int filas;
  private int columnas;
  private FileInputStream fis;
  private Vista_Tablero vistaTablero;
  private int[][][]matriz;
  private Random rnd;
  char personaje;
  private boolean hayRey=false;
  
  private BufferedWriter bw;
  
  public Tablero(Vista_Tablero vistaTablero){
      
      this.vistaTablero=vistaTablero;
      vistaTablero.btn_start.addActionListener(this);
         
  }
  
  
    
public void creaTablero() throws IOException{
    

        try {
           
            bw=new BufferedWriter(new FileWriter("batalla.txt", false));
            bw.write(filas+"/"+columnas);
            
            //bw.write("no escribo nadaaaaaa");
            bw.newLine();
            bw.flush();
          
             } catch (IOException ex) { 
          Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
        
        this.vistaTablero.btn_start.setEnabled(false);
        this.vistaTablero.setSize(600, 300);
        this.vistaTablero.txt_tablero.setVisible(true);
        this.cargaPersonajes();
        
        
    }

    @Override //carga nro de filas y columnas
    public void actionPerformed(ActionEvent e) {
       
    if(e.getSource()==this.vistaTablero.btn_start){
        
        filas=Integer.parseInt(vistaTablero.txt_filas.getText());
        columnas=Integer.parseInt(vistaTablero.txt_columnas.getText());
        
        if( ((filas < 5 || filas > 10) || columnas < 5) || columnas>10)
        {
            //AGREGAR EXCEPCION
            System.out.println("tamañno invalido");
            
        } else  {
            
            try {
                this.creaTablero();
                
            } catch (IOException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
                 }
                                                    }
                                                 }
    
    public void cargaPersonajes() throws IOException{
        
        matriz=new int[this.filas][this.columnas][4];
        rnd=new Random();
        int aleatorio;
        
        
     for(int f=0; f<filas;f++)
      {
        for(int c=0; c<columnas;c++)
            {
            for(int i=0;i<4;i++)
                {
                 
                   switch(i){
                       
                       case 0: aleatorio=rnd.nextInt(5);
                       
                                while((this.hayRey)&&(aleatorio==4)){
                                    aleatorio=rnd.nextInt(5);
                                }break;
                           
                       case 1: aleatorio=rnd.nextInt(4);break;
                       case 2: aleatorio=rnd.nextInt(101);break;                                               
                       default: if(matriz[f][c][0]==3)
                               //es caballero
                                   aleatorio=rnd.nextInt(10)+5;
                                      
                               else  if(matriz[f][c][0]==4)//es rey
                                            aleatorio=rnd.nextInt(100);
                                                
                                            
                                        else 
                                            aleatorio=0;          
                           
                   }
                    
                    //solo 1 rey
                   
                   if(matriz[f][c][0]== 4){
                       this.hayRey=true;
                   }
                    
                   
                    
                 
                 
                 matriz[f][c][i]= aleatorio;
                 bw.write(matriz[f][c][i]+"-");
                 
                 
                 
                 
                 
                 
                 System.out.println(aleatorio);
                  }this.imprimeTablero(matriz[f][c][0]);
            bw.write("/");
            
             }this.imprimeTablero(true);
        bw.newLine();
        } bw.flush();
        
        
                                }

    private void imprimeTablero(int matriz) {
   
    switch(matriz)
    {
        case 1: personaje='E'; break;
        case 2: personaje='S'; break;
        case 3: personaje='C'; break;
        case 4: personaje='R'; break;
        default : personaje='X'; break;
    }
    
        vistaTablero.txt_tablero.setText( (this.vistaTablero.txt_tablero.getText())+" "+personaje+" ");
    
    }
    
    private void imprimeTablero(boolean saltoLinea) 
    {
   
        vistaTablero.txt_tablero.setText( (this.vistaTablero.txt_tablero.getText())+"\n");
    
    }
    
    
    
    
    
    
    
    
    
    
}
