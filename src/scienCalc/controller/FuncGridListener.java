package scienCalc.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import scienCalc.model.FuncGridConstants;


public class FuncGridListener implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        String cmdID = ((Button)e.getSource()).getId();
        FuncGridConstants c = FuncGridConstants.valueOf(cmdID);
        // Entscheidung welche Funktion aufgerufen wird.
        switch (c) {
            case SQR:
                // DO Something
                System.out.println("SQR");
                break;
            case CUBIC:
                // DO Something
                System.out.println("CUBIC");
                break;
            case SIN:
                // DO Something
                System.out.println("SIN");
                break;
            case COS:
                // DO Something
                System.out.println("COS");
                break;
            case TAN:
                // DO Something
                System.out.println("TAN");
                break;
            case XPOWY:
                // DO Something
                System.out.println("XPOWY");
                break;
            case RECVAL:
                // DO Something
                System.out.println("RECVAL");
                break;
            case ARCSIN:
                // DO Something
                System.out.println("ARCSIN");
                break;
            case ARCCOS:
                // DO Something
                System.out.println("ARCCOS");
                break;
            case ARCTAN:
                // DO Something
                System.out.println("ARCTAN");
                break;
            case SQRT:
                // DO Something
                System.out.println("SQRT");
                break;
            case YSQRT:
                // DO Something
                System.out.println("YSQRT");
                break;
            case EXP:
                // DO Something
                System.out.println("EXP");
                break;
            case DMS:
                // DO Something
                System.out.println("DMS");
                break;
            case DEG:
                // DO Something
                System.out.println("DEG");
                break;
            case EXPF:
                // DO Something
                System.out.println("EXPF");
                break;
            case LOG:
                // DO Something
                System.out.println("LOG");
                break;
            case MOD:
                // DO Something
                System.out.println("MOD");
                break;
            case OBRACKET:
                // DO Something
                System.out.println("OBRACKET");
                break;
            case CBRACKET:
                // DO Something
                System.out.println("CBRACKET");
                break;
            case EX:
                // DO Something
                System.out.println("EX");
                break;
            case LN:
                // DO Something
                System.out.println("LN");
                break;
            case FACT:
                // DO Something
                System.out.println("FACT");
                break;
            case PI:
                // DO Something
                System.out.println("PI");
                break;
            case SIGNCHANGE:
                // DO Something
                System.out.println("SIGNCHANGE");
                break;
        }
        e.consume();
    }
}
