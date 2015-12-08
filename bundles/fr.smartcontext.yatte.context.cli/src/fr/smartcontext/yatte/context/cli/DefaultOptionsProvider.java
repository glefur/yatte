/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.context.cli;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class DefaultOptionsProvider implements CLIOptionsProvider {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.context.cli.CLIOptionsProvider#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options options = new Options();
		OptionBuilder.withLongOpt(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_ARGUMENT_NAME);
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_NAME));

		OptionBuilder.withLongOpt(ApplicationParametersConstants.OUTPUT_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.OUTPUT_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.OUTPUT_OPTION_ARGUMENT_NAME);
		OptionBuilder.isRequired();
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.OUTPUT_OPTION_NAME));

		OptionBuilder.withLongOpt(ApplicationParametersConstants.TEMPLATE_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.TEMPLATE_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.TEMPLATE_OPTION_ARGUMENT_NAME);
		OptionBuilder.isRequired();
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.TEMPLATE_OPTION_NAME));

		return options;
	}

}
