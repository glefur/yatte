/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.engine.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import fr.smartcontext.yatte.internal.engine.TemplateProcessorImpl;
import fr.smartcontext.yatte.internal.engine.commands.GenFeatureCommand;
import fr.smartcontext.yatte.internal.engine.commands.GenServiceCommand;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class CommandFactory {
	
	
	public Command createCommand(String directive) {
		String commandStr = directive.substring(TemplateProcessorImpl.DIRECTIVE_PREFIX.length(), directive.length() - TemplateProcessorImpl.DIRECTIVE_SUFFIX.length());
		StringTokenizer str = new StringTokenizer(commandStr, " ");
		String type = str.nextToken();
		Map<String, String> parameters = new HashMap<>();
		while (str.hasMoreTokens()) {
			String nextToken = str.nextToken();
			parameters.put(nextToken.substring(0, nextToken.indexOf('=')), nextToken.substring(nextToken.indexOf('=') + 1));
		}
		Command result = null;
		switch (type) {
			case "feature":
				result = new GenFeatureCommand(parameters);
				break;
			case "service":
				result = new GenServiceCommand(parameters);
				break;
		}
		return result;
	}

}
