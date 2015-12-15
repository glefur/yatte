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
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class ProcessingContextImpl implements ProcessingContext {
	
	private final Map<String, Object> variables;
	private Path templatePath;
	private OutputStream output;

	public ProcessingContextImpl() {
		variables = new HashMap<>();
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContext#getVar(java.lang.String)
	 */
	@Override
	public final Object getVar(String name) {
		return variables.get(name);
	}

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContext#setVar(java.lang.String, java.lang.Object)
	 */
	@Override
	public final void setVar(String name, Object value) {
		variables.put(name, value);
	}

	/**
	 * @return the templatePath
	 */
	public Path getTemplatePath() {
		return templatePath;
	}

	/**
	 * @param templatePath the templatePath to set
	 */
	public void setTemplatePath(Path templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * @return the output
	 */
	public OutputStream getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(OutputStream output) {
		this.output = output;
	}

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContext#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(String qname) {
		return null;
	}
	
}
