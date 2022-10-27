/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MiniPC.view;

import MiniPC.model.MemoryRegister;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import MiniPC.model.PCB;
import MiniPC.model.Register;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class CPU_Menu extends javax.swing.JFrame {

    
    int size;
    File file;
    PCB cpu;
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int j) {return false;};
    };
    
    
    CPU_Menu(PCB cpu, File file) {
        
        initComponents();
        
        this.cpu = cpu;
        this.size = cpu.getMemory().getSize();
        this.file = file;
        
        
        loadTable();      
        fillTable();
        loadTextFile();
        
        
        this.cpu.setCPUMemory(this.file.getAbsolutePath(), this.size, "bla", 0);
        String filename = this.file.getName();
        loadMemory();
        
        lblFilename.setText(filename);
        lbSize.setText(String.valueOf(size));
        
    }

    private void loadMemory(){
        int index = this.cpu.getMemory().getAllocationIndex();        
        int size = this.cpu.getLoader().getInstrucionSet().size();
                
        Optional<Register> register;
        for( int i  = index; i < (size+index); i++){            
            
            
            register =  this.cpu.getMemory().getInstructions().get(i);
            MemoryRegister reg = (MemoryRegister) register.get();
            table.setValueAt(reg.toBinaryString(), i, 0);            

        }
        System.out.println(this.cpu.getMemory().getAllocationIndex()+ "damdmdk");

         

    }
    
    public void loadTextFile() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String str;
            while ((str = in.readLine()) != null) {
                txtArchivo.append(str);
                txtArchivo.append("\n");
            }
        } catch (IOException e) {
        } finally {
            try { in.close(); } catch (Exception ex) { }
        }
    }
    
    
    public void loadTable() {
        
        this.model.setRowCount(this.size);
        this.model.addColumn("Instrucción en código binario");
        this.model.addColumn("Posición");
        this.table.setModel(model);    
        this.table.getColumnModel().getColumn(1).setResizable(true);
        this.table.getColumnModel().getColumn(1).setMaxWidth(60);
        
    }

    public void fillTable() {
        
        for (int i = 0; i < this.size; i++) {
            table.setValueAt(i+1, i, 1);
        }
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAX = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBX = new javax.swing.JLabel();
        txtCX = new javax.swing.JLabel();
        txtDX = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIR = new javax.swing.JLabel();
        txtAC = new javax.swing.JLabel();
        txtPC = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btmClean = new javax.swing.JButton();
        btmBack = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbSize = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblFilename = new javax.swing.JLabel();
        btmNext = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 51));

        txtArchivo.setEditable(false);
        txtArchivo.setColumns(20);
        txtArchivo.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("DX");

        txtAX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAX.setText("___");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("BX");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("CX");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("AX");

        txtBX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBX.setText("___");

        txtCX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCX.setText("___");

        txtDX.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDX.setText("___");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBX)
                    .addComponent(txtAX)
                    .addComponent(txtCX)
                    .addComponent(txtDX))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAX)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBX)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCX)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDX)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("IR");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("AC");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("PC");

        txtIR.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIR.setText("_______");

        txtAC.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAC.setText("_______");

        txtPC.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPC.setText("_______");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIR)
                    .addComponent(txtPC)
                    .addComponent(txtAC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtPC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtIR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtAC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );

        btmClean.setFont(new java.awt.Font("Inter", 0, 11)); // NOI18N
        btmClean.setText("Limpiar");
        btmClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmCleanActionPerformed(evt);
            }
        });

        btmBack.setFont(new java.awt.Font("Inter", 0, 11)); // NOI18N
        btmBack.setText("Volver");
        btmBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmBackActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel19.setText("Tamaño de memoria:");

        jLabel20.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        lbSize.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        lbSize.setText("_______");

        jLabel22.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel22.setText("Programa ejecutando:");

        lblFilename.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        lblFilename.setText("_______");

        btmNext.setFont(new java.awt.Font("Inter", 0, 11)); // NOI18N
        btmNext.setText("Siguiente");
        btmNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmNextActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        table.setEditingColumn(0);
        table.setEditingRow(0);
        table.setShowGrid(true);
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFilename)
                        .addGap(166, 166, 166)
                        .addComponent(btmNext))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btmClean)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btmBack, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(lbSize)
                    .addComponent(jLabel22)
                    .addComponent(lblFilename)
                    .addComponent(btmNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btmBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btmClean, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btmNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmNextActionPerformed
        
        if(this.cpu.programFinished()){
             JOptionPane.showMessageDialog(this, "No hay mas instrucciones por ejecutar","MiniPC", 2);
             return;

        }
        /*
        ArrayList<String> instruction = this.cpu.executeInstruction();
        txtAX.setText(instruction.get(0));
        txtBX.setText(instruction.get(1));
        txtCX.setText(instruction.get(2));
        txtDX.setText(instruction.get(3));
        txtAC.setText(instruction.get(4));
        txtIR.setText(instruction.get(5));
        Integer pc = Integer.parseInt(instruction.get(6))+1;
        txtPC.setText(pc.toString());        
        
        table.setRowSelectionInterval(pc-1, pc-1);
        */
        
        
        
    }//GEN-LAST:event_btmNextActionPerformed

    private void btmBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmBackActionPerformed
        this.setVisible(false);
        new Main_Menu().setVisible(true);
    }//GEN-LAST:event_btmBackActionPerformed

    private void btmCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmCleanActionPerformed
        txtAX.setText("____");
        txtBX.setText("____");
        txtCX.setText("____");
        txtDX.setText("____");
        txtAC.setText("____");
        txtIR.setText("____");
        txtPC.setText("____");  
    }//GEN-LAST:event_btmCleanActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmBack;
    private javax.swing.JButton btmClean;
    private javax.swing.JButton btmNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbSize;
    private javax.swing.JLabel lblFilename;
    private javax.swing.JTable table;
    public javax.swing.JLabel txtAC;
    public javax.swing.JLabel txtAX;
    private javax.swing.JTextArea txtArchivo;
    public javax.swing.JLabel txtBX;
    public javax.swing.JLabel txtCX;
    public javax.swing.JLabel txtDX;
    public javax.swing.JLabel txtIR;
    public javax.swing.JLabel txtPC;
    // End of variables declaration//GEN-END:variables
}
