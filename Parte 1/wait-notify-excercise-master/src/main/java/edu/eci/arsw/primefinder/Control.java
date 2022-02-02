/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Control extends Thread {
    
    private final static int NTHREADS =3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;
    private long inicio;
    private long fin;
    Scanner scanner;
    String in;

    private PrimeFinderThread pft[];
    
    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];

        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1);
    }
    
    public static Control newControl() {
        return new Control();
    }

    @Override
    public void run() {
        inicio = System.currentTimeMillis();

        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
        }
        checkWait();
    }

    public void checkWait () {

        while (fin - inicio  < TMILISECONDS){
            fin = System.currentTimeMillis();

        }

        for(int i = 0;i < NTHREADS;i++ ){
            pft[i].getwait();
            }

        scanner = new Scanner(System.in);
        in = scanner.nextLine();
        if(in.equals("")) {
            for(int i = 0;i < NTHREADS;i++ ){
                pft[i].getstart();
            }
            }

    }

    
}
