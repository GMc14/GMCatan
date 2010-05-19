/**
 * Java Settlers - An online multiplayer version of the game Settlers of Catan
 * Copyright (C) 2003  Robert S. Thomas
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * The author of this program can be reached at thomas@infolab.northwestern.edu
 **/
package soc.message;

import soc.robot.SSRobotClient;


/**
 * This message tells the server that the client is a robot
 *
 * @author Robert S Thomas
 */
public class SOCImSmartSettlers extends SOCMessage
{
    /**
     * Name of built-in robot brain class.
     * This robot is the original robot, distributed with the JSettlers server,
     * which permits some optimized communications.
     * Other (3rd-party) robots must use a different class in their IMAROBOT messages.
     * See the class javadoc for more details.
     * @since 1.1.09
     */
    public static final String RBCLASS = "smartsettler";
    
    private SSRobotClient client;

    /**
     * Create a ImARobot message.
     *
     * @param client nickname
     */
    public SOCImSmartSettlers(SSRobotClient client)
    {
        messageType = IMAROBOT;
        this.client = client;
    }

    /**
     * @return the nickname
     */
//    public String getNickname()
//    {
//        return nickname;
//    }
    
    public SSRobotClient getClient()
    {
        return client;
    }

    /**
     * IMAROBOT sep nickname
     *
     * @return the command String
     */
    public String toCmd()
    {
        return toCmd(client.toString());
    }

    /**
     * IMAROBOT sep nickname
     *
     * @param nn  the neckname
     * @return    the command string
     */
    public static String toCmd(String nn)
    {
        return IMSMARTSETTLERS + sep + nn;
    }

    /**
     * Parse the command String into a ImARobot message
     *
     * @param s   the String to parse
     * @return    a ImARobot message, or null of the data is garbled
     */
    public static SOCImARobot parseDataStr(String s)
    {
        return new SOCImARobot(s, RBCLASS);
    }

    /**
     * @return a human readable form of the message
     */
    public String toString()
    {
        String s = "SOCImSmartSettlers:client=" + client;

        return s;
    }
}
