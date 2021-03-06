/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.context.cli;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;
import fr.smartcontext.yatte.engine.context.ProcessingContextImpl;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class CLIBasedContextInitializer implements ContextInitializer {

	private CLIOptionsProvider optionsProvider;
	

	/**
	 * 
	 */
	public CLIBasedContextInitializer() {
		optionsProvider = null;
	}

	/**
	 * @param optionsProvider the optionsProvider to set
	 */
	public void setOptionsProvider(CLIOptionsProvider optionsProvider) {
		this.optionsProvider = optionsProvider;
	}

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ContextInitializer#initContext(java.util.List)
	 */
	@Override
	public ProcessingContext initContext(List<String> parameters) throws Exception {
		ProcessingContextImpl processingContext = new ProcessingContextImpl();
		Options options = getOptions();
		CommandLine cmdLine = getCmdLine(parameters, options);
		processingContext.setTemplatePath(Paths.get(cmdLine.getOptionValue(ApplicationParametersConstants.TEMPLATE_OPTION_NAME)));
		processingContext.setOutput(new FileOutputStream(new File(cmdLine.getOptionValue(ApplicationParametersConstants.OUTPUT_OPTION_NAME))));
		return processingContext;
	}

	/**
	 * @param bundleContext
	 * @return
	 */
	protected final Options getOptions() {
		if (optionsProvider == null) {
			optionsProvider = new DefaultOptionsProvider();
		}
		
		Options options = optionsProvider.getOptions();
		return options;
	}
	
	/**
	 * @param parameters
	 * @param options
	 * @return
	 * @throws ParseException
	 */
	protected CommandLine getCmdLine(List<String> parameters, Options options) throws ParseException {
		String[] arguments = parameters.toArray(new String[]{});
		CommandLineParser parser = new BasicParser();
		CommandLine cmdLine = parser.parse(options, arguments);
		return cmdLine;
	}

}
