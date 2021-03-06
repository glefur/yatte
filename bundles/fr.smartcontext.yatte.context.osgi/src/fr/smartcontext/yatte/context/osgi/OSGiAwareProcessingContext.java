/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.context.osgi;

import fr.smartcontext.yatte.engine.context.ProcessingContextImpl;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class OSGiAwareProcessingContext extends ProcessingContextImpl {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.context.ProcessingContextImpl#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(String qname) {
		return super.loadClass(qname);
	}

}
