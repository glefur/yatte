/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.examples.advanced;

import java.util.List;

import org.osgi.framework.BundleContext;

import fr.smartcontext.yatte.application.YatteApplication;
import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.examples.advanced.context.ContextInitializerExtender;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class AdvancedApplication extends YatteApplication {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.application.YatteApplication#prepareGeneration(org.osgi.framework.BundleContext, java.util.List)
	 */
	@Override
	protected void prepareGeneration(BundleContext bundleContext, List<String> parameters) {
		bundleContext.registerService(ContextInitializer.class, new ContextInitializerExtender(), null);
	}

}
