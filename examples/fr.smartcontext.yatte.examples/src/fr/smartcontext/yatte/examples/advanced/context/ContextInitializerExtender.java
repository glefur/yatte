/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.examples.advanced.context;

import java.util.List;

import org.osgi.framework.BundleContext;

import fr.smartcontext.yatte.context.cli.properties.PropertiesContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;
import fr.smartcontext.yatte.examples.advanced.ExtendedPerson;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class ContextInitializerExtender extends PropertiesContextInitializer {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.context.cli.properties.PropertiesContextInitializer#initContext(org.osgi.framework.BundleContext, java.util.List)
	 */
	@Override
	public ProcessingContext initContext(BundleContext bundleContext, List<String> parameters) throws Exception {
		ProcessingContext initContext = super.initContext(bundleContext, parameters);
		ExtendedPerson john = (ExtendedPerson) initContext.getVar("john");
		ExtendedPerson george = (ExtendedPerson) initContext.getVar("george");
		ExtendedPerson jane = (ExtendedPerson) initContext.getVar("jane");
		john.setFather(george);
		john.setMother(jane);
		return initContext;
	}

}
