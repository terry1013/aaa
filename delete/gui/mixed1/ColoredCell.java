/*******************************************************************************
 * Copyright (C) 2017 terry.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     terry - initial API and implementation
 ******************************************************************************/
package delete.gui.mixed1;

import java.awt.*;

interface ColoredCell {

	public Color getForeground(int row, int column);
	public void setForeground(Color color, int row, int column);
	public void setForeground(Color color, int[] rows, int[] columns);

	public Color getBackground(int row, int column);
	public void setBackground(Color color, int row, int column);
	public void setBackground(Color color, int[] rows, int[] columns);

}
