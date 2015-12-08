/*******************************************************************************
 * Copyright (c) 2015 Goulwen Le Fur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.smartcontext.yatte.context.cli.properties;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.osgi.framework.BundleContext;

import fr.smartcontext.yatte.context.cli.ApplicationParametersConstants;
import fr.smartcontext.yatte.context.cli.CLIBasedContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * @author <a href="goulwen.lefur@gmail.com">Goulwen Le Fur</a>
 *
 */
public class PropertiesContextInitializer extends CLIBasedContextInitializer {

	/** 
	 * {@inheritDoc}
	 * @see fr.smartcontext.yatte.context.cli.CLIBasedContextInitializer#initContext(org.osgi.framework.BundleContext, java.util.List)
	 */
	@Override
	public ProcessingContext initContext(BundleContext bundleContext, List<String> parameters) throws Exception {
		ProcessingContext processingContext = super.initContext(bundleContext, parameters);
		Options options = getOptions(bundleContext);
		CommandLine cmdLine = getCmdLine(parameters, options);
		String propertiesPath = cmdLine.getOptionValue(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_NAME);
		if (propertiesPath != null) {
			URL entry = new URL("file:" + propertiesPath);
			Properties prop = new Properties();
			InputStream openStream = entry.openStream();
			prop.load(openStream);
			Collection<String> searchVariableNames = searchVariableNames(prop);
			for (String varName : searchVariableNames) {
				Collection<String> features = searchFeatureForVariable(prop, varName);
				String type = prop.getProperty(varName + TYPE_SUFFIX);
				Class<?> class_ = bundleContext.getBundle().loadClass(type);
				Object instance = class_.newInstance();
				for (String feature : features) {
					setFeature(prop, varName, class_, instance, feature);
				}
				processingContext.setVar(varName, instance);			
			}
		}
		return processingContext;
	}

	private Collection<String> searchVariableNames(Properties properties) {
		Collection<String> result = new ArrayList<>();
		Set<Object> keySet = properties.keySet();
		for (Object key : keySet) {
			if (key instanceof String) {
				String str = (String) key;
				if (str.endsWith(TYPE_SUFFIX)) {
					result.add(str.substring(0, str.length() - TYPE_SUFFIX.length()));
				}
			}
		}
		return result;
	}
	
	private Collection<String> searchFeatureForVariable(Properties properties, String varName) {
		Collection<String> result = new ArrayList<>();
		Set<Object> keySet = properties.keySet();
		for (Object key : keySet) {
			if (key instanceof String) {
				String str = (String) key;
				if (str.startsWith(varName + DOT) && !str.equals(varName + TYPE_SUFFIX)) {
					result.add(str.substring((varName + DOT).length(), str.length()));
				}
			}
		}
		return result;
	}

	private void setFeature(Properties prop, String varName, Class<?> class_, Object instance, String feature) throws NoSuchFieldException, IllegalAccessException {
		String value = prop.getProperty(varName + DOT + feature);
		Field field = class_.getDeclaredField(feature);
		field.setAccessible(true);
		field.set(instance, value);
	}

}
