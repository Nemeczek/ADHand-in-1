package com.company;

/**
 * Created by B006572 on 02-09-2015.
 */
public class Printer {

        public void print() {
            System.out.print("-");
            try { Thread.sleep(50); } catch (InterruptedException exn) { }
            System.out.print("|");
        }
}
