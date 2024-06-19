/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edfconvert;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Almada
 */

public class EDF2ASCII {
    
    int time;
    String edfFile;

    public static final int LINES = 15360;
	
    public EDF2ASCII(int time, String edfFile) {
        this.time = time;
        this.edfFile = edfFile;
    }
	
    public void edf2ascii() {
        try {
            Process process = Runtime.getRuntime().exec("edf2ascii " + edfFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	processData(this.edfFile);
    }
    
    public void processData(String fname) {
        try {
            FileInputStream fis = new FileInputStream(fname);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = "", data = "";
            int lines = 0, totalLines = time * LINES;
            while (lines <= totalLines) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                if (lines != 0) {
                    data += line + "\n";
                }
                lines++;
                //System.out.printf("[+] line %d\n",lines++);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("EDF_OUTPUT.txt"), false));
            bw.write(data);
            bw.close();
            fis.close();
            br.close();
            JOptionPane.showMessageDialog(null, "EDF_OUTPUT.txt file generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
