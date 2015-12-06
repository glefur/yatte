/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.internal.engine.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import fr.smartcontext.yatte.engine.commands.Command;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class GenServiceCommand extends Command {

	public GenServiceCommand(Map<String, String> parameters) {
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.engine.commands.Command#execute(fr.smartcontext.yatte.engine.context.ProcessingContext)
	 */
	@Override
	public String execute(ProcessingContext context) {
		String qname = parameters.get("qname");
		String operation = parameters.get("operation");
		String target = parameters.get("target");
		Object var = null;
		if (target != null) {
			var = context.getVar(target);
		}
		if (qname != null && operation != null && var != null) {
			try {
				Class<?> class_ = context.getBundleContext().getBundle().loadClass(qname);
				Method method = getMethod(class_, operation, var);
				if (method != null) {
					method.setAccessible(true);
					if (Modifier.isStatic(method.getModifiers())) {
						return (String) method.invoke(null, var);
					} else {
						Object newInstance = class_.newInstance();
						return (String) method.invoke(newInstance, var);
					}
				}
			} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				return null;
			}
		}
		return null;
	}
	
	private Method getMethod(Class<?> class_, String methodName, Object... param) {
		Method[] methods = class_.getDeclaredMethods();
		for (Method method : methods) {
			if (methodName.equals(method.getName())) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length == param.length) {
					boolean valid = true;
					for (int i = 0; i < parameterTypes.length && valid; i++) {
						if (!parameterTypes[i].isInstance(param[i])) {
							valid = false;
						}
					}
					if (valid) {
						return method;
					}
				}
			}
		}
		return null;
	}

}
