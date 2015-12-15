/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.application;

import java.util.Arrays;
import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import fr.smartcontext.yatte.engine.GenerationProcess;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * This class controls all aspects of the application's execution
 * @author <a href="goulwen.lefur@mail.com">Goulwen Le Fur</a>
 *
 */
public class YatteApplication implements IApplication {

	private Bundle applicationBundle;

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public final Object start(IApplicationContext context) {
		try {
			context.applicationRunning();
			applicationBundle = FrameworkUtil.getBundle(getClass());
			BundleContext bundleContext = applicationBundle.getBundleContext();
			List<String> parameters = Arrays.asList((String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
			prepareGeneration(bundleContext, parameters);
			GenerationProcess generationProcess = new GenerationProcess();
			ProcessingContext processingContext = generationProcess.prepareContext(parameters);
			generationProcess.generate(processingContext);
		} catch (Exception e) {
			System.err.println("An error occurred.");
			e.printStackTrace();
		}
		return IApplication.EXIT_OK;
	}

	/**
	 * @return
	 */
	protected String applicationName() {
		return "yatte";
	}

	protected void prepareGeneration(BundleContext bundleContext, List<String> parameters) {
		// Default: do nothing.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}

}
