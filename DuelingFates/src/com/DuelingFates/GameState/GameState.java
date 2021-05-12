package com.DuelingFates.GameState;

import javax.swing.*;
import java.awt.*;

public abstract class GameState{

    protected StateManager stateManager;               //protected, hogy a leszáramzottakban hozzáférjünk

    GameState(StateManager stateManager){              //létrehozunk egy állapotgépet, hogy használni tudjuk
        this.stateManager = stateManager;              //mindig az adott állapot fogja használni -> StateManager -> loadStates -> this

    }

    //abstract, ugyanis nem szeretnénk definiálni őket, csak a leszármazottakban
    public abstract void initialization();
    public abstract void draw(Graphics2D graphics);
    public abstract void update();
    public abstract void updateSwingUI(JFrame duelingFates, JLayeredPane layeredPane);

}
