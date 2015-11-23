/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.engine.context;

import org.apache.commons.cli.CommandLine;
import org.osgi.framework.BundleContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public interface ContextInitializer {
	
	String DOT = ".";
	String TYPE_SUFFIX = ".type";
	String PROPERTIES_PATH_PARAMETER = "propertiesPath";

	ProcessingContext initContext(BundleContext bundleContext, CommandLine cmdLine) throws Exception;
	
}
