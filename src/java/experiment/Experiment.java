package experiment;

import java.util.logging.Level;
import java.util.logging.Logger;

import smartsettlers.boardlayout.GameStateConstants;
import soc.client.SOCPlayerClient;
import soc.client.SOCPlayerInterface;
import soc.game.SOCGame;
import soc.message.SOCJoinGame;

/**
 *
 * @author szityu
 */
public class Experiment implements GameStateConstants {

    
    Experiment()
    {
        
    }
    
    public static void main(String[] args) throws InterruptedException
    {
//        ServerThread st = new ServerThread();
//        st.start();
        
//        SmartSettlersClientThread cl0 = new SmartSettlersClientThread(0);
//        SmartSettlersClientThread cl1 = new SmartSettlersClientThread(1);
//        SmartSettlersClientThread cl2 = new SmartSettlersClientThread(2);
        ClientThread cl0 = new ClientThread(0);
        ClientThread cl1 = new ClientThread(1);
        ClientThread cl2 = new ClientThread(2);
        ClientThread cl3 = new ClientThread(3);
        TimerThread ttr = new TimerThread();
        
        
        cl0.start();
        cl1.start();
        cl2.start();
        cl3.start();
        ttr.start();
        Thread.sleep(6000);
//        st.server.setLoggerClient(cl0.client);
//        System.out.println(cl0.client + "   " + st.server);
        
        String gamename;
        if (args.length==0)
            gamename = "game1";
        else
            gamename = args[0];
        
        
        SOCPlayerClient client = new SOCPlayerClient();
        client.host = "localhost";
        client.port = 8880;
        client.nickname = "Experimenter";
        String password = "pass";
        client.initVisualElements(); // after the background is set
        client.connect();
        //client.put(SOCJoin.toCmd(client.nickname, "", client.host, "channel0"));
        //client.put(SOCJoinGameAuth.toCmd(gamename));
        SOCGame ga = new SOCGame(gamename);

        if (ga != null)
        {
//            SOCPlayerInterface pi = new SOCPlayerInterface(gamename, client, ga);
//            pi.setVisible(true);
//            client.playerInterfaces.put(gamename, pi);
            client.games.put(gamename, ga);
        }
        
        //SOCGame ga = (SOCGame) client.games.get(gamename);
        client.put(SOCJoinGame.toCmd(client.nickname, password, client.host, gamename), false);
        ga = (SOCGame) client.games.get(gamename);
        //client.sitDown(ga, 0);
        SOCPlayerInterface pi = (SOCPlayerInterface) client.playerInterfaces.get(gamename);
        Thread.sleep(5000);
        client.startGame(ga);


    }
}

class TimerThread extends Thread
{
    @Override
    public void run()
    {
        try {
            sleep(1000*60*45);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("TIMEOUT!!!!");
        System.exit(-1);
    }
}
