/**
 * Open Settlers - an open implementation of the game Settlers of Catan
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. **/
package soc.message;

import java.util.StringTokenizer;


/**
 * This message says what resource the current player wants to
 * monopolize
 *
 * @author Robert S. Thomas
 */
public class MonopolyPick extends Message
{
    private static final long serialVersionUID = 122781229525670750L;

    /**
     * Name of game
     */
    private String game;

    /**
     * The chosen resource
     */
    private int resource;

    /**
     * Create a MonopolyPick message.
     *
     * @param ga  the name of the game
     * @param rs  the resource
     */
    public MonopolyPick(String ga, int rs)
    {
        messageType = MONOPOLYPICK;
        game = ga;
        resource = rs;
    }

    /**
     * @return the name of the game
     */
    public String getGame()
    {
        return game;
    }

    /**
     * @return the chosen resource
     */
    public int getResource()
    {
        return resource;
    }

    /**
     * MONOPOLYPICK sep game sep2 resource
     *
     * @return the command string
     */
    public String toCmd()
    {
        return toCmd(game, resource);
    }

    /**
     * MONOPOLYPICK sep game sep2 resource
     *
     * @param ga  the name of the game
     * @param rs  the chosen resource
     * @return the command string
     */
    public static String toCmd(String ga, int rs)
    {
        return MONOPOLYPICK + sep + ga + sep2 + rs;
    }

    /**
     * Parse the command String into a StartGame message
     *
     * @param s   the String to parse
     * @return    a StartGame message, or null of the data is garbled
     */
    public static MonopolyPick parseDataStr(String s)
    {
        String ga; // the game name
        int rs; // the chosen resource

        StringTokenizer st = new StringTokenizer(s, sep2);

        try
        {
            ga = st.nextToken();
            rs = Integer.parseInt(st.nextToken());
        }
        catch (Exception e)
        {
            return null;
        }

        return new MonopolyPick(ga, rs);
    }

    /**
     * @return a human readable form of the message
     */
    public String toString()
    {
        return "MonopolyPick:game=" + game + "|resource=" + resource;
    }
}
