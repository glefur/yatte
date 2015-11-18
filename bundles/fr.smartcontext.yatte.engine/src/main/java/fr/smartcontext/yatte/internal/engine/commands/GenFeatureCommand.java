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

package fr.smartcontext.yatte.internal.engine.commands;

import java.lang.reflect.Field;
import java.util.Map;

import fr.smartcontext.yatte.engine.commands.Command;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class GenFeatureCommand extends Command {

	public GenFeatureCommand(Map<String, String> parameters) {
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.commands.Command#execute(fr.smartcontext.yatte.engine.context.ProcessingContext)
	 */
	@Override
	public String execute(ProcessingContext context) {
		String target = parameters.get("target");
		String feature = parameters.get("feature");
		Object var = context.getVar(target);
		try {
			Field field = var.getClass().getDeclaredField(feature);
			field.setAccessible(true);
			Object value = field.get(var);
			return value.toString();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			return null;
		}
	}

}
