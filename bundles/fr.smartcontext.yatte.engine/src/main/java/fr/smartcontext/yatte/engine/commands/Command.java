/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.smartcontext.yatte.engine.commands;

import java.util.Map;

import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public abstract class Command {
	
	protected final Map<String, String> parameters;

	public Command(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public abstract String execute(ProcessingContext context);
	
}
