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
    
    public Paging(Memory mem){
        this.memory = mem;
        this.pageTable = new HashMap<>();
        this.pageSize = 1;
        this.frameSize = 1;
        this.pageIndex = 0;
        
        
    }
        

    @Override
    public boolean allocatePCB(PCB pcb) {
        if(pcb==null){
            return false;
        }
        HashMap<Integer,Integer> pcbPageTable  =new HashMap<>();
        this.pageTable.put(pcb, pcbPageTable);
         ArrayList<Integer> pcbData = pcb.getPCBData();
        ArrayList<MemoryRegister> instructions = pcb.getLoader().getInstrucionSet();
        
        if(this.memory.getCurrentIndex()+pcbData.size()+instructions.size() >this.memory.getFreeSpaceCount()){                    
            //Error por desbordamiento de memoria
            return false;
        }
        
         //pcb.setMemoryStartingIndex(this.memory.getCurrentIndex());
         
         int currentIndex= 0;
         int k = 0;
         int now = 0;
        while( k  < pcbData.size()){
           
            
            if(this.memory.getRegisters().get(now).isEmpty()){
                 Register infoRegister = new InformationRegister();
                infoRegister.setPCB(pcb);
                
                infoRegister.setValue(pcbData.get(k));
                this.memory.getRegisters().set(now,Optional.of(infoRegister));
                pcbPageTable.put(this.pageIndex,now);
                k++;
                
                
                if(k>= this.pageSize){
                    this.pageIndex++;
                    
                }
                
                

            }
            now++;
            
            
        }
       
       while(!this.memory.getRegisters().get(currentIndex).isEmpty()){currentIndex++;}
       
         pcb.setProgramCounter(currentIndex);
        k = 0;
        now = currentIndex;
        System.out.println("Paging");
        while( k  < instructions.size()){
              if(this.memory.getRegisters().get(now).isEmpty()){
                                
                Register memoryRegister = new MemoryRegister();           
                memoryRegister = instructions.get(k);
                memoryRegister.setPCB(pcb);
                                
                this.memory.getRegisters().set(now,Optional.of(memoryRegister));
                 pcbPageTable.put(this.pageIndex,now);
                k++;
                
               
                if(k>= this.pageSize){
                    this.pageIndex++;
                    
                }
                
                

            }
            
            now++;
            
            
        }
        
      
        pcb.setMemory(this.memory);    
         this.pageTable.put(pcb, pcbPageTable);
        this.memory.getPcbs().add(pcb);
        
        
        
        this.pageIndex = 0;
        return true;
        
        
    }

    @Override
    public void deallocatePCB(PCB pcb) {
        int index = 0;
        for(Optional<Register> reg: this.memory.getRegisters()){
            if(reg.isPresent()){
                if(reg.get().getPCB().equals(pcb)){
                    this.memory.getRegisters().set(index, Optional.empty());
                
                }
            
            }
            index++;
        }
        this.memory.getPcbs().remove(pcb);

    }

    @Override
    public int getNextPC(PCB pcb) {
        
        
         
        System.out.println();
        
        if(this.pageTable.get(pcb).get(pcb.getCurrentInstruction()+pcb.getPCBData().size()+1)!= null){
        
        
        
        

        
        
            if(pcb.getProgramCounter()+1 != this.pageTable.get(pcb).get(pcb.getCurrentInstruction()+pcb.getPCBData().size()+1)){
        
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
    
}
