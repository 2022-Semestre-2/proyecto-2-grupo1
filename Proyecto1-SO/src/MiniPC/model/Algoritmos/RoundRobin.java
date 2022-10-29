/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model.Algoritmos;

import MiniPC.controller.PCController;
import MiniPC.model.PCB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ricardosoto
 */
public class RoundRobin implements Algoritmos{
    private Queue<PCB> procesosEsperando;
    private PCB currentPCB;
    private int currentTime;
    //private int currentTimeAux;
    private boolean programFinished;
    private ArrayList<String> status;
    
    private ArrayList<PCB> procesos = new ArrayList<PCB>();
    private int quantum = 2;
    private int contProcess = 0;
    private int contQuantum = 0;
    private boolean processFinish = false;
    private boolean continu = false;
    
    
    public RoundRobin(int quantum){
        this.quantum = quantum;
        this.currentTime = 1;
        this.procesosEsperando = new LinkedList<PCB>();
        this.programFinished = false;
        //this.currentTimeAux= 1;
    }
    
    @Override
    public PCB executeInstruction(Queue<PCB> colaProcesosCPU, PCController cont) {
         //ESTO ES PARTE DEL MÉTODO A IMPLEMENTAR
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        
        Queue<PCB> col = colaProcesosCPU;
        this.programFinished = false;
        
        for(PCB proceso: col){
           //los procesos llegan en el instante actual
      
           if(proceso.getArrivalTime()<=this.currentTime && !this.procesos.contains(proceso)){   
              //System.out.println("PCB->"+proceso.getFileName()+" entra a espera");
              if(this.procesos.isEmpty()){
                   this.currentPCB = proceso;
                   this.currentPCB.setStatus("Exec");
                   
               }               
               procesos.add(proceso);
              
           }
        } 
        for(PCB proceso: procesos) {
            this.procesosEsperando.add(proceso);
            System.out.println("PBC: "+proceso.getFileName() +"RAFAGA:"+(proceso.getRafaga()));
        }
        if(this.processFinish == true){
           contQuantum =0;
           this.processFinish = false;
           this.currentPCB = this.procesos.get(contProcess);
           contQuantum++;
       }else{
            if(contQuantum < quantum){
                this.currentPCB = this.procesos.get(contProcess);
                contQuantum++;
            }else{
                if(contProcess == this.procesos.size()-1){
                    contProcess = 0;
                    contQuantum = 0;
                    this.currentPCB = this.procesos.get(contProcess);
                    contQuantum++;
               }else{
                    contQuantum = 0;
                    //contProcess++;
                    procesos.remove(currentPCB);
                    procesos.add(currentPCB);
                    this.currentPCB = this.procesos.get(contProcess);
                    contQuantum++;
               }
            }
        }

        //ESTO ES PARTE DEL MÉTODO A IMPLEMENTAR
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        //ESTO ES PARTE DEL METODO POR DEFECTO, PROCUREN RETORNAR EL PCB que según el algoritmo se debe ejecutar
       PCB result =  this.currentPCB;     
       
       //this.currentTimeAux += this.currentPCB.getPCBinstrucctionSize();
       
       if(result!=null){
           this.status = result.executeInstruction(cont);
           if(this.currentPCB.programFinished()){   
                this.programFinished = true;
                this.processFinish = true;
                this.procesos.remove(this.currentPCB);        
            }  
       }
       this.currentTime++;    
       return result;
    }

    @Override
    public boolean programIsFinished(){                
        return this.programFinished;
     }
    @Override 
    public ArrayList<String> getStatus(){
        return this.status;
    }        

    @Override
    public int getTime() {
        return this.currentTime;
    }
    
}
