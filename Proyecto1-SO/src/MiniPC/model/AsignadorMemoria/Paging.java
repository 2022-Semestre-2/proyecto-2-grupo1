/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniPC.model.AsignadorMemoria;

import MiniPC.model.InformationRegister;
import MiniPC.model.Memory;
import MiniPC.model.MemoryRegister;
import MiniPC.model.PCB;
import MiniPC.model.Register;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author ricardosoto
 */
public class Paging implements AsignadorMemoria {
    private Memory memory;
    private HashMap<PCB, HashMap<Integer,Integer>> pageTable ;
    private int pageSize ;
    private int frameSize;
    private int pageIndex;
    private ArrayList<Frame> frames;
    private int cantidadFrames ;
    
    public Paging(Memory mem,int frame){
        this.memory = mem;
        this.pageTable = new HashMap<>();
        this.pageSize = 1;
        //El número de página no importa en este caso, lo que importa es el tamaño de los frames, es ajustable
        this.frameSize = frame;
        this.pageIndex = 0;
        this.frames = new ArrayList<>();
        int start=0;
        int end=0;
        //Cuantos frames me caben
        int framesFit = this.memory.getSize()/this.frameSize;
        this.cantidadFrames = framesFit;
        
        for(int i = 0; i < this.cantidadFrames ; i ++){            
             start= i* this.frameSize;
             end = (i+1)* this.frameSize;
             if(end > this.memory.getSize()){
                 break;
             }
                 
            this.frames.add(new Frame(start,end ));
            
        }
        
        
        
        
    }
        
    public boolean A(PCB pcb){
         if(pcb==null){
            return false;
        }
        
        HashMap<Integer,Integer> pcbPageTable  =new HashMap<>();
        
         ArrayList<Integer> pcbData = pcb.getPCBData();
        ArrayList<MemoryRegister> instructions = pcb.getLoader().getInstrucionSet();
        
        if(!fitsInFrames(pcb)){
            return false;
        }
        
        int now = 0;
        int indx=0;
        Frame currentFrame= null;
        while(indx< pcb.getPCBData().size()){
            for(Frame frame : this.frames){
            // Si no tiene registros    
            if(indx>= pcbData.size()) break;
                if(frame.registers.isEmpty()){
                    
                    for(int i = 0 ; i < this.frameSize && indx< pcbData.size(); i++){
                        Register infoRegister = new InformationRegister();
                          infoRegister.setPCB(pcb);               
                           infoRegister.setValue(pcbData.get(indx));
                         this.memory.getRegisters().set(now,Optional.of(infoRegister));
                         frame.registers.add(infoRegister);
                         frame.setPCB(pcb);
                         
                         currentFrame = frame;   
                         pcbPageTable.put(this.pageIndex,now);
                         now++;
                         indx++;
                            
                        if(indx>= this.pageSize){
                            this.pageIndex++;

                        }
                         
                         
                    }
                    
                
                } else {
                    now+=this.frameSize;
                }
                
            }
        }
        
        indx=0;
        
        
        pcb.setProgramCounter(now);
        boolean programCOunterSet = false;
        while(currentFrame.registers.size()<this.frameSize && indx<  pcb.getInstructions().size()){
             Register memoryRegister = new MemoryRegister();           
                memoryRegister = instructions.get(indx);
                memoryRegister.setPCB(pcb);

                this.memory.getRegisters().set(now,Optional.of(memoryRegister));
                programCOunterSet = true;
              
                 currentFrame.registers.add(memoryRegister);
                 
                pcbPageTable.put(this.pageIndex,now);
                         now++;
                         indx++;
                            
                        if(indx>= this.pageSize){
                            this.pageIndex++;

                        }
                 

            
        }
        
        
       
       
       now = 0;
       Integer pc = null;
        while(indx< pcb.getInstructions().size()){
            for(Frame frame  : this.frames){
            // Si no tiene registros
             
                if(frame.registers.isEmpty()){
                    for(int i = 0 ; i < this.frameSize && indx< pcb.getInstructions().size(); i++){
                       Register memoryRegister = new MemoryRegister();           
                        memoryRegister = instructions.get(indx);
                        
                        memoryRegister.setPCB(pcb);
                       if(pc==null && !programCOunterSet){
                            pc = now;
                            pcb.setProgramCounter(now);
                        }
                        
                        this.memory.getRegisters().set(now,Optional.of(memoryRegister));
                         
                         frame.registers.add(memoryRegister);
                         frame.setPCB(pcb);
                          pcbPageTable.put(this.pageIndex,now);
                         now++;
                         indx++;
                            
                        if(indx>= this.pageSize){
                            this.pageIndex++;

                        }
                    }
                    
                
                } else {
                    now+=this.frameSize;
                }
                
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("PROGRAM COUNTER::::"+ pcb.getProgramCounter());
        System.out.println("-------------------------------------------");
        pcb.setMemory(this.memory);    
         this.pageTable.put(pcb, pcbPageTable);
        this.memory.getPcbs().add(pcb);
        this.pageIndex = 0;
        
     
        return true;
    }
    public void imprimirFrames(){
        System.out.println("-------------------------------------------");
        for(int i = 0; i < this.cantidadFrames ; i ++){       
            
               System.out.println("Frame "+i +" inicia en :");
                System.out.println(this.frames.get(i).start);
                System.out.println("Frame "+i +"termina en :");
                System.out.println(this.frames.get(i).end);
                System.out.println();
                System.out.println();
            for(int j = 0 ;j < this.frames.get(i).registers.size(); j++){
                System.out.println("Registro: "+j+"del frame "+ i);
                System.out.println(this.frames.get(i).registers.get(j));
            }
                
        }
    }
    
    public boolean fitsInFrames(PCB pcb){
        int pcbsize = pcb.getPCBData().size()+pcb.getInstructions().size();
        int count = 0;
        for(Frame frame : this.frames){
            if(frame.registers.isEmpty()){
                count+=this.frameSize;
            }
        }
        return pcbsize<=count;
        
    }
    @Override    
    public boolean allocatePCB(PCB pcb) {
        return A(pcb);
       
        
        
    }

    @Override
    public void deallocatePCB(PCB pcb) {
        int index = 0;
    
        for(Frame frame: this.frames){
            if(frame.getPCB()!=null && frame.getPCB().equals(pcb)){
                for(Register reg: frame.registers){
                    this.memory.getRegisters().set(index, Optional.empty());
                    index++;
                }
                frame.registers.clear();
                frame.setPCB(null);
            }else {
                index+=this.frameSize;
            }
        }
        //imprimirFrames();
        this.pageTable.remove(pcb);
        this.memory.getPcbs().remove(pcb);

    }
    @Override
    public int getNextPC(PCB pcb) {
        
           
         
        System.out.println();
        
        if(this.pageTable.get(pcb).get(pcb.getCurrentInstruction()+pcb.getPCBData().size()+1)!= null){
        
        
        
        

        
        
            if(pcb.getProgramCounter()+1 != this.pageTable.get(pcb).get(pcb.getCurrentInstruction()+pcb.getPCBData().size()+1)){
                        System.out.println("REISED HERE");
            return this.pageTable.get(pcb).get(pcb.getCurrentInstruction()+pcb.getPCBData().size()+1);
            }
        }
         for(int i = 0 ; i < this.memory.getPcbs().size(); i ++){
           PCB p = this.memory.getPcbs().get(i);
           
           
               
           System.out.println("PCB" + i);
               
           for(int j = 0 ; j < p.getInstructions().size()+p.getPCBData().size(); j++){
               System.out.println(this.pageTable.get(p).get(j));
           }
           
           
           
       }
        System.out.println();
        System.out.println();
        System.out.println();
        
        
         
       
        
        return pcb.getProgramCounter()+1;
        
    }
    
    public class Frame {
        
        private int start;
        private int end;
        private PCB pcb;
        private ArrayList<Register> registers;
        public Frame(int start , int end ){
            this.start = start;
            this.end = end;
            this.registers = new ArrayList<>();
            
            
        }
        
        public void setPCB(PCB pcb){
            this.pcb = pcb;
        }
        public PCB getPCB(){
            return this.pcb;
        }
       
        public ArrayList<Register> getRegisters( ){
            return this.registers;
        }
        public void setStart(int start){
            this.start = start;
            
        }
        public void setEnd(int end){
            this.start = end;
            
        }
        public int size(){
            return this.end-this.start;
        }
        
        
    }

    
}
