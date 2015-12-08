/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.examples.advanced;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class ExtendedPerson {

	private String firstname;
	private String lastname;
	private String address;
	private ExtendedPerson father;
	private ExtendedPerson mother;
	
	public ExtendedPerson() {	}

	/**
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param father
	 * @param mother
	 */
	public ExtendedPerson(String firstname, String lastname, String address, ExtendedPerson father, ExtendedPerson mother) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.father = father;
		this.mother = mother;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the father
	 */
	public ExtendedPerson getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(ExtendedPerson father) {
		this.father = father;
	}

	/**
	 * @return the mother
	 */
	public ExtendedPerson getMother() {
		return mother;
	}

	/**
	 * @param mother the mother to set
	 */
	public void setMother(ExtendedPerson mother) {
		this.mother = mother;
	}
	
}
