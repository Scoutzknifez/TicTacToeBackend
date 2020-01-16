package com.scoutzknifez.tictactoe.gamelogic.dtos;

import com.scoutzknifez.tictactoe.utility.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter
public class TTTBoard implements Serializable {
    // In slots, -1 is O, 0 is empty, 1 is X
    private Pieces[] slots = new Pieces[Constants.BOARD_SIZE];

    public TTTBoard() {
        Arrays.fill(slots, Pieces.BLANK);
    }

    public boolean didWin() {
        // TODO Modular check for wins based off of passed in slot
        // TOP
        if (slots[0].isNonBlankAndEqualTo(slots[1]) && slots[1].isNonBlankAndEqualTo(slots[2]))
            return true;

        // LEFT
        if (slots[0].isNonBlankAndEqualTo(slots[3]) && slots[3].isNonBlankAndEqualTo(slots[6]))
            return true;

        // RIGHT
        if (slots[2].isNonBlankAndEqualTo(slots[5]) && slots[5].isNonBlankAndEqualTo(slots[8]))
            return true;

        // BOTTOM
        if (slots[6].isNonBlankAndEqualTo(slots[7]) && slots[7].isNonBlankAndEqualTo(slots[8]))
            return true;

        // LEFT TO RIGHT CROSS
        if (slots[0].isNonBlankAndEqualTo(slots[4]) && slots[4].isNonBlankAndEqualTo(slots[8]))
            return true;

        // RIGHT TO LEFT CROSS
        if (slots[2].isNonBlankAndEqualTo(slots[4]) && slots[4].isNonBlankAndEqualTo(slots[6]))
            return true;

        // DOWN MIDDLE
        if (slots[1].isNonBlankAndEqualTo(slots[4]) && slots[4].isNonBlankAndEqualTo(slots[7]))
            return true;

        // ACROSS MIDDLE
        if (slots[3].isNonBlankAndEqualTo(slots[4]) && slots[4].isNonBlankAndEqualTo(slots[5]))
            return true;

        return false;
    }

    public boolean gameIsOver() {
        // TODO                         TEMP
        return boardIsFull() || didWin();
    }


    public void resetBoard() {
        for (int i = 0; i < getSlots().length; i++)
            slots[i] = Pieces.BLANK;
    }

    public boolean slotIsEmpty(int slot) {
        return getSlots()[slot] == Pieces.BLANK;
    }

    public boolean boardIsFull() {
        for (Pieces slot : slots)
            if (slot == Pieces.BLANK)
                return false;

        return true;
    }

    private boolean setSlot(int slot, Pieces piece) {
        if (!slotIsEmpty(slot))
            return false;

        getSlots()[slot] = piece;
        return true;
    }

    public boolean setSlotToCross(int slot) {
        return setSlot(slot, Pieces.CROSS);
    }

    public boolean setSlotToCircle(int slot) {
        return setSlot(slot, Pieces.CIRCLE);
    }

    public boolean setSlotToBlank(int slot) {
        return setSlot(slot, Pieces.BLANK);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int x = 0; x < Constants.BOARD_SIZE; x++) {

            sb.append(slots[x].getCharacter());

            if ((x % 3) < 2)
                sb.append("|");

            if (x % 3 == 2)
                sb.append("\n");
        }

        return sb.toString();
    }
}
