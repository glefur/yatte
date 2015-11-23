/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package fr.smartcontext.yatte.internal.engine;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.smartcontext.yatte.engine.TemplateProcessor;
import fr.smartcontext.yatte.engine.commands.Command;
import fr.smartcontext.yatte.engine.commands.CommandFactory;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class TemplateProcessorImpl implements TemplateProcessor {
	
	public static final String DIRECTIVE_PREFIX = "<!--gen:";
	public static final String DIRECTIVE_SUFFIX = "-->";
	
	private Pattern pattern;
	private CommandFactory commandFactory;

	public TemplateProcessorImpl() {
		pattern = Pattern.compile(DIRECTIVE_PREFIX + "[^<]*" + DIRECTIVE_SUFFIX);
	}

	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

	/**
	 * @param templatePath
	 * @param context
	 * @param target
	 * @return
	 * @throws IOException
	 */
	@Override
	public PrintWriter process(Path templatePath, ProcessingContext context, PrintWriter target) throws IOException {
		Files.lines(templatePath, Charset.defaultCharset()).map(
				line -> sustitute(context, line)).forEach(target::println);;
		return target;
	}
	
	private String sustitute(ProcessingContext context, String input) {
		Matcher matcher = pattern.matcher(input);
		int i = 0;
		while (matcher.find()) {
			String directive = input.substring(matcher.start(i), matcher.end(i));
			Command cmd = commandFactory.createCommand(directive);
			String replacement = cmd.execute(context);
			StringBuilder builder = new StringBuilder(input); 
			builder.replace(matcher.start(i), matcher.end(i), (replacement == null)?"":replacement);
			input = builder.toString();
			matcher = pattern.matcher(input);
		}
		return input;
	}
	
}
