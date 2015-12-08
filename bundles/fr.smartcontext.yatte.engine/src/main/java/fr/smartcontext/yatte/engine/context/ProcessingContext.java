/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.engine.context;

import java.io.OutputStream;
import java.nio.file.Path;

import org.osgi.framework.BundleContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public interface ProcessingContext {
	
	BundleContext getBundleContext();
	
	void setVar(String varName, Object instance);

	Object getVar(String name);
	
	Path getTemplatePath();
	
	void setTemplatePath(Path path);
	
	OutputStream getOutput();
	
	void setOutput(OutputStream outputStream);


}
