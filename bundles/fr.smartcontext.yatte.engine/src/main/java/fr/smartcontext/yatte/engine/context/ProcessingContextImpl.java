/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package fr.smartcontext.yatte.engine.context;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class ProcessingContextImpl implements ProcessingContext {
	
	private final BundleContext bundleContext;
	private final Map<String, Object> variables;

	public ProcessingContextImpl(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		variables = new HashMap<>();
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContext#getBundleContext()
	 */
	@Override
	public BundleContext getBundleContext() {
		return bundleContext;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContext#getVar(java.lang.String)
	 */
	@Override
	public final Object getVar(String name) {
		return variables.get(name);
	}

	public final void setVar(String name, Object value) {
		variables.put(name, value);
	}
	
}
