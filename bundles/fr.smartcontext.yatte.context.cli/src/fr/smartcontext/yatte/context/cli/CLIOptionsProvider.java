/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.context.cli;

import org.apache.commons.cli.Options;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public interface CLIOptionsProvider {
	
	Options getOptions();

}
