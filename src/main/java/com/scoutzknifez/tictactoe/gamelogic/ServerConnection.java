package com.scoutzknifez.tictactoe.gamelogic;

import com.scoutzknifez.tictactoe.gamelogic.dtos.Sample;
import com.scoutzknifez.tictactoe.gamelogic.dtos.Value;
import com.scoutzknifez.tictactoe.utility.Utils;
import com.scoutzknifez.tictactoe.utility.exceptions.ObjectConstructionException;
import lombok.Data;

import java.io.*;
import java.net.Socket;

@Data
public class ServerConnection extends Thread {
    private GameServer gameServer;
    private Socket socket;

    /**
     * These are used for all forms of input and output on this thread.
     * Can be accessed from getters
     */
    private InputStream input;
    private ObjectInputStream ois;
    private OutputStream output;
    private ObjectOutputStream oos;

    public ServerConnection(GameServer gameServer, Socket socket) {
        setGameServer(gameServer);
        setSocket(socket);

        try {
            setOutput(getSocket().getOutputStream());
            setOos(new ObjectOutputStream(getOutput()));
            setInput(getSocket().getInputStream());
            setOis(new ObjectInputStream(getInput()));
        } catch (Exception e) {
            Utils.log("Could not get the input channels for the socket! %s", e);
            e.printStackTrace();
            throw new ObjectConstructionException(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (getGameServer().isAlive() && !getSocket().isClosed()) {
            try {
                Object object = getOis().readObject();
                if (object instanceof Sample) {
                    Sample sample = (Sample) object;
                    Utils.log(sample);

                    sendOutput(new Value<String>("Okay!"));
                }


            } catch (EOFException e) {
                Utils.log("The socket closed unexpectedly! %s", e);
                closeSocket();
            } catch (Exception e) {
                Utils.log("Input/Output error! %s", e);
            }
        }
        closeSocket();
    }

    private void closeSocket() {
        try {
            getSocket().close();
        } catch (Exception e) {
            Utils.log("Could not close the socket! %s", e);
        }
    }

    public void sendOutput(Object object) {
        try {
            getOos().writeObject(object);
            getOos().flush();
        } catch (Exception e) {
            Utils.log("Could not send the object (%s) to the client! %s", object, e);
        }
    }
}
