/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.engine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;
import fr.smartcontext.yatte.internal.engine.context.SimplestContextInitializer;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class GenerationProcess {
	
	/**
	 * @param bundleContext
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public ProcessingContext prepareContext(BundleContext bundleContext, List<String> parameters) throws Exception {
		ContextInitializer contextInitializer;
		ServiceReference<ContextInitializer> ciRef = bundleContext.getServiceReference(ContextInitializer.class);
		if (ciRef != null) {
			contextInitializer = bundleContext.getService(ciRef);
		} else {
			contextInitializer = new SimplestContextInitializer();
		}
		ProcessingContext processingContext = contextInitializer.initContext(bundleContext, parameters);
		return processingContext;
	}

	/**
	 * @param processingContext
	 * @throws IOException
	 */
	public void generate(ProcessingContext processingContext) throws IOException {
		ServiceReference<TemplateProcessor> templateProcessorRef = processingContext.getBundleContext().getServiceReference(TemplateProcessor.class);
		TemplateProcessor templateProcessor = processingContext.getBundleContext().getService(templateProcessorRef);
		PrintWriter fos = new PrintWriter(processingContext.getOutput());
		templateProcessor.process(processingContext.getTemplatePath(), processingContext, fos);
		fos.close();
	}

}
