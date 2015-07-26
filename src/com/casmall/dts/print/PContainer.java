package com.casmall.dts.print;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.graphics.Point;

/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Author: Friederich Kupzog fkmk@kupzog.de www.kupzog.de/fkmk
 */

/**
 * A Container class for PBoxes. Used for PDocument, Headers and Footers.
 * 
 * @author Friederich Kupzog
 */
public class PContainer {

	protected ArrayList<PBox> children;

	protected PDocument doc;

	/**
   * 
   */
	public PContainer(PDocument doc) {
		this.doc = doc;
		children = new ArrayList<PBox>(100);
	}

	protected double getPossibleWidth() {
		return (doc.pageWidth - doc.margins[1] - doc.margins[3]);
	}

	protected double getPossibleHeight() {
		return (doc.pageHeight - doc.margins[0] - doc.margins[2]);
	}

	public void addChild(PBox child) {
		children.add(child);
	}

	public void draw(Point origin) {
		for (Iterator<PBox> iter = children.iterator(); iter.hasNext();) {
			PBox element = (PBox) iter.next();
			element.draw(origin);
		}
	}
}