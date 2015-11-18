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
package fr.smartcontext.yatte.engine;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public interface TemplateProcessor {

	/**
	 * Processes the given template in the target output.
	 * @param templatePath {@link Path} of the template to process
	 * @param context {@link ProcessingContext} for the current template.
	 * @param target {@link PrintWriter} where to process the template.
	 * @return the target.
	 * @throws IOException ar error occurred during template processing.
	 */
	public PrintWriter process(Path templatePath, ProcessingContext context, PrintWriter target) throws IOException;
	
}
