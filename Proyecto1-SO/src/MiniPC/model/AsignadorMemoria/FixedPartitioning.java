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
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author ricardosoto
 */
public class FixedPartitioning implements AsignadorMemoria{

    
    private Memory memory;
    private int partitionSize = 0;
    private int index = 0;
    private int count = 0;

    public FixedPartitioning(Memory mem, int particion){
        this.memory = mem;
        this.partitionSize = particion;
    }
    @Override
    public boolean allocatePCB(PCB pcb) {
        if(pcb==null){
            return false;
        }
        
        ArrayList<Integer> pcbData = pcb.getPCBData();
        ArrayList<MemoryRegister> instructions = pcb.getLoader().getInstrucionSet();
       
        if(this.memory.getCurrentIndex()+pcbData.size()+instructions.size() >this.memory.getSize()){                    
            return false;
        }
        
        
        if(this.memory.getCurrentIndex()+pcbData.size()+instructions.size() > this.partitionSize){                    
            
            //String mensaje = "No se pudo cargar el proceso->"+pcb.getFileName()+" porque no cabe en la particion de tamaño:"+this.partitionSize;
            //JOptionPane.showMessageDialog(null, mensaje,"MiniPC", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        
        
        int k = 0;
        int now = this.partitionSize*this.count;
        
        System.out.println(now > this.memory.getSize());
        System.out.println("NOW =>"+ now);
        System.out.println("MEM =>"+ this.memory.getSize());
        
        if ((now+this.partitionSize) >= this.memory.getSize()) {
            return false;
        }
        
        
        while( k  < pcbData.size()){
            if(this.memory.getRegisters().get(now).isEmpty()){
                Register infoRegister = new InformationRegister();
                infoRegister.setPCB(pcb);
                infoRegister.setValue(pcbData.get(k));
                this.memory.getRegisters().set(now,Optional.of(infoRegister));
                k++;
            }
            
            if(k>= this.index){
                this.index++;    
            }
            
            now++;
        }
        
        
        //while(!this.memory.getRegisters().get(currentIndex).isEmpty()){currentIndex++;}
        System.out.println("NOW =>"+ now);
        System.out.println("PCB =>"+ (pcb.getPCBData().size()+pcb.getInstructions().size()));
        System.out.println("COUNT =>"+ this.count);
        System.out.println("COUNT2 =>"+ (this.partitionSize*this.count));
        
        
        pcb.setProgramCounter(now);
        
        k = 0;
        System.out.println("Fija");
        while( k  < instructions.size()){
              if(this.memory.getRegisters().get(now).isEmpty()){
                                
                Register memoryRegister = new MemoryRegister();           
                memoryRegister = instructions.get(k);
                memoryRegister.setPCB(pcb);
                                
                this.memory.getRegisters().set(now,Optional.of(memoryRegister));
                k++;
                if(k>= this.index){
                    this.index++;
                }
            }
            
            now++;
            
            
        }
        
        
        
        pcb.setMemory(this.memory);    
        this.memory.getPcbs().add(pcb);
        
        
        this.count++;
        
        this.index = this.partitionSize;
        return true;
        
        
        
    }

    
    @Override
    public void deallocatePCB(PCB pcb) {
        int index = 0;
        this.count--;
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
        return pcb.getProgramCounter()+1;
    }
    
    
}
