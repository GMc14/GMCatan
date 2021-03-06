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
package soc.robot;

import soc.game.ResourceSet;

import java.util.Vector;


/**
 * This is a tree that contains possible
 * trade offers and how they're related
 * to each other.  Also contains a flag
 * for wheather or not this offer should
 * be expanded to other offers.
 *
 * @author Robert S. Thomas
 */
public class TradeTree
{
    ResourceSet resourceSet;
    TradeTree parent;
    Vector children;
    boolean needsToBeExpanded;

    /**
     * this is a constructor
     *
     * @param set     the set of resources
     * @param par     the parent of this node
     */
    public TradeTree(ResourceSet set, TradeTree par)
    {
        resourceSet = set;
        needsToBeExpanded = true;
        children = new Vector();

        if (par != null)
        {
            par.addChild(this);
        }
        else
        {
            parent = null;
        }
    }

    /**
     * this is a constructor
     *
     * @param set     the set of resources
     */
    public TradeTree(ResourceSet set)
    {
        resourceSet = set;
        parent = null;
        needsToBeExpanded = false;
        children = new Vector();
    }

    /**
     * @return the resource set
     */
    public ResourceSet getResourceSet()
    {
        return resourceSet;
    }

    /**
     * @return the parent
     */
    public TradeTree getParent()
    {
        return parent;
    }

    /**
     * @return the needsToBeExpanded flag
     */
    public boolean needsToBeExpanded()
    {
        return needsToBeExpanded;
    }

    /**
     * @return the list of children
     */
    public Vector getChildren()
    {
        return children;
    }

    /**
     * set the parent
     *
     * @param p  the parent node
     */
    public void setParent(TradeTree p)
    {
        parent = p;
    }

    /**
     * set the needs to be expanded flag
     *
     * @param value  the value of the flag
     */
    public void setNeedsToBeExpanded(boolean value)
    {
        needsToBeExpanded = value;
    }

    /**
     * add a child to this node
     *
     * @param child  the node to be added
     */
    public void addChild(TradeTree child)
    {
        children.addElement(child);
        child.setParent(this);
    }
}
