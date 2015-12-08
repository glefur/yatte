/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.internal.engine.context;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import org.osgi.framework.BundleContext;

import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;
import fr.smartcontext.yatte.engine.context.ProcessingContextImpl;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class SimplestContextInitializer implements ContextInitializer {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ContextInitializer#initContext(org.osgi.framework.BundleContext, java.util.List)
	 * In this implementation, we expect to find the path to the template in the first place of <code>parameters</code> and that's it.
	 * Optionally, we'll search for a filepath as 2nd parameter, otherwise the output will be System::out
	 */
	@Override
	public ProcessingContext initContext(BundleContext bundleContext, List<String> parameters) throws Exception {
		ProcessingContextImpl processingContext = new ProcessingContextImpl(bundleContext);
		if (parameters.size() > 0) {
			processingContext.setTemplatePath(Paths.get((String)parameters.get(0)));
			if (parameters.size() > 1) {
				String param = parameters.get(1);
				processingContext.setOutput(new BufferedOutputStream(new FileOutputStream(new File(param))));
			}
		}
		if (processingContext.getOutput() == null)
			processingContext.setOutput(System.out);
		return processingContext;
	}

}
