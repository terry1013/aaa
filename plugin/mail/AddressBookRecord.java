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
package plugin.mail;

import gui.*;
import gui.wlaf.*;

import javax.swing.*;



import com.jgoodies.forms.builder.*;
import com.jgoodies.forms.layout.*;

import core.*;
import core.datasource.*;

/**
 * Panel de edicion de direcciones. Clases que editan registro con direccines en debe usar esta clase para editar y
 * presentar las direcciones en forma uniforme.
 * 
 */
public class AddressBookRecord extends AbstractRecordDataInput {

	private TWebImageDrop imageDrop;
	/**
	 * nueva instancia
	 * 
	 * @param jftf - componente que sera actualizado con el identificador de registro de diereccion
	 */
	public AddressBookRecord(Record rcd, boolean newr) {
		super("mail.title01", rcd, newr);

		addInputComponent("m_abtitle", TUIUtils.getJTextField(rcd, "m_abtitle"), false, true);
		addInputComponent("m_abname", TUIUtils.getJTextField(rcd, "m_abname"), true, true);
		addInputComponent("m_abemail", TUIUtils.getJTextField(rcd, "m_abemail"), true, true);
		addInputComponent("m_aborganization", TUIUtils.getJTextField(rcd, "m_aborganization"), false, true);
		addInputComponent("m_abmobile_phone", TUIUtils.getJFormattedTextField(rcd, "m_abmobile_phone"), false, true);
		addInputComponent("m_aboffice_phone", TUIUtils.getJFormattedTextField(rcd, "m_aboffice_phone"), false, true);

		ImageIcon ii = null;
		if (!newr) {
			ii = new ImageIcon((byte[]) rcd.getFieldValue("m_abphoto"));
		}
		this.imageDrop = new TWebImageDrop(100,100, ii);

		FormLayout lay = new FormLayout("left:pref, 3dlu, left:pref, 7dlu, 70dlu", // columns
				"p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p"); // rows
		CellConstraints cc = new CellConstraints();
		PanelBuilder build = new PanelBuilder(lay);

		build.add(getLabelFor("m_abtitle"), cc.xy(1, 1));
		build.add(getInputComponent("m_abtitle"), cc.xy(3, 1));
		build.add(getLabelFor("m_abname"), cc.xy(1, 3));
		build.add(getInputComponent("m_abname"), cc.xy(3, 3));
		build.add(getLabelFor("m_abmobile_phone"), cc.xy(1, 5));
		build.add(getInputComponent("m_abmobile_phone"), cc.xy(3, 5));
		build.add(getLabelFor("m_aboffice_phone"), cc.xy(1, 7));
		build.add(getInputComponent("m_aboffice_phone"), cc.xy(3, 7));
		build.add(getLabelFor("m_abemail"), cc.xy(1, 9));
		build.add(getInputComponent("m_abemail"), cc.xyw(3, 9, 3));
		build.add(getLabelFor("m_aborganization"), cc.xy(1, 11));
		build.add(getInputComponent("m_aborganization"), cc.xyw(3, 11, 3));

		build.add(imageDrop, cc.xywh(5, 1, 1, 7));

		setDefaultActionBar();
		add(build.getPanel());
		preValidate(null);
	}
	
	@Override
	public Record getRecord() {
		Record rcd = super.getRecord();
		rcd.setFieldValue("m_abphoto", imageDrop.getImageIcon());

		return rcd;
	}
}