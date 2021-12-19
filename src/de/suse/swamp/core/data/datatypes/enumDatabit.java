/*
 * SWAMP Workflow Administration and Management Platform
 *
 * Copyright (c) 2006 Thomas Schmidt
 * Copyright (c) 2006 Novell Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of version 2 of the GNU General Public
 * License as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA 
 *
 * In addition, as a special exception, Novell Inc. gives permission to link the
 * code of this program with the following applications:
 *
 * - All applications of the Apache Software Foundation 
 *
 * and distribute such linked combinations.
 */

package de.suse.swamp.core.data.datatypes;

import java.text.*;
import java.util.*;

import de.suse.swamp.core.data.*;
import de.suse.swamp.core.workflow.*;

/**
 * The Databit
 *
 * @author Thomas Schmidt
 *
 */

public class enumDatabit extends Databit {
	
    private ArrayList enumvalues = new ArrayList();
    

    public enumDatabit(String name, String desc, String value, Integer state) throws Exception {
        super (name, desc, value, state.intValue());
        setModified(true);
    }

    
    /**
     * Checks if the given value v fits this Databit's Type.
     * 
     * @param v
     */
    public String checkDataType(String v) throws ParseException {
        // empty values are allowed
        if (!v.equals("") && !checkedValues.contains(v)) {
            
            // perform basic type checking
            if (!getEnumvalues().contains(v)) {
                throw new ParseException(i18n.tr("Assignment to Field ") + this.getName() + " (" + v + ") "
                        + i18n.tr(" must be in Enumeration-Array! ") + getEnumvalues(), 0);
            }
            // value is valid
            checkedValues.add(v);
        }
		return v;
    }
    
    public Field getField(String pathPrefix) {
        // Create a field
        Field field = super.getField(pathPrefix);
        field.setEnumvalues(this.enumvalues);
        return field;
    }

    /**
     * @return Returns the enumvalues.
     */
    public ArrayList getEnumvalues() {
        return enumvalues;
    }
    
    /**
     * @param enumvalues The enumvalues to set.
     */
    public void setEnumvalues(ArrayList enumvalues) {
        this.enumvalues = enumvalues;
        setModified(true);
    }

    public void addEnumvalue(String value) {
        if (!enumvalues.contains(value)) {
            this.enumvalues.add(value);
            setModified(true);
        }
    }
	
}
