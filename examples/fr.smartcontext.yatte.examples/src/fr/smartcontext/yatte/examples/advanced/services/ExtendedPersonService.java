/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.examples.advanced.services;

import fr.smartcontext.yatte.examples.advanced.ExtendedPerson;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class ExtendedPersonService {
	
	public String fatherIdentity(ExtendedPerson source) {
		ExtendedPerson father = source.getFather();
		if (father != null) {
			return father.getFirstname() + " " + father.getLastname();
		} else {
			return "Unknown";
		}
	}

	public String motherIdentity(ExtendedPerson source) {
		ExtendedPerson mother = source.getMother();
		if (mother != null) {
			return mother.getFirstname() + " " + mother.getLastname();
		} else {
			return "Unknown";
		}
	}

}
