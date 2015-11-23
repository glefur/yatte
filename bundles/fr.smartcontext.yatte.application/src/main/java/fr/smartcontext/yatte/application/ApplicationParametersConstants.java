/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.application;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public interface ApplicationParametersConstants {

	String PROPERTIES_PATH_OPTION_NAME = "p";
	String PROPERTIES_PATH_OPTION_LONG_NAME = "properties-path";
	String PROPERTIES_PATH_OPTION_DESCRIPTION = "Path of the properties file to use to init the processing context";
	String PROPERTIES_PATH_OPTION_ARGUMENT_NAME = "properties";

	String OUTPUT_OPTION_NAME = "o";
	String OUTPUT_OPTION_LONG_NAME = "output";
	String OUTPUT_OPTION_DESCRIPTION = "Path where generate the target file";
	String OUTPUT_OPTION_ARGUMENT_NAME = "output";

	String TEMPLATE_OPTION_NAME = "t";
	String TEMPLATE_OPTION_LONG_NAME = "template";
	String TEMPLATE_OPTION_DESCRIPTION = "Path of the yatte template to use";
	String TEMPLATE_OPTION_ARGUMENT_NAME = "template";
}
