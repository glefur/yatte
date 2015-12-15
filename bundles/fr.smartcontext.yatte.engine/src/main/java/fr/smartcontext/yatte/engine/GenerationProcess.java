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

import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;
import fr.smartcontext.yatte.internal.engine.context.SimplestContextInitializer;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class GenerationProcess {
	
	private ContextInitializer contextInitializer;
	private TemplateProcessor templateProcessor;
	
	/**
	 * 
	 */
	public GenerationProcess() {
		contextInitializer = null;
		templateProcessor = null;
	}

	/**
	 * @param contextInitializer the contextInitializer to set
	 */
	public void setContextInitializer(ContextInitializer contextInitializer) {
		this.contextInitializer = contextInitializer;
	}

	/**
	 * @param templateProcessor the templateProcessor to set
	 */
	public void setTemplateProcessor(TemplateProcessor templateProcessor) {
		this.templateProcessor = templateProcessor;
	}

	/**
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public ProcessingContext prepareContext(List<String> parameters) throws Exception {
		if (contextInitializer == null) {
			contextInitializer = new SimplestContextInitializer();
		}
		ProcessingContext processingContext = contextInitializer.initContext(parameters);
		return processingContext;
	}

	/**
	 * @param processingContext
	 * @throws IOException
	 */
	public void generate(ProcessingContext processingContext) throws IOException {
		if (templateProcessor != null) {
			PrintWriter fos = new PrintWriter(processingContext.getOutput());
			templateProcessor.process(processingContext.getTemplatePath(), processingContext, fos);
			fos.close();
		}
	}

}
