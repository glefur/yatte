package fr.smartcontext.yatte.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import fr.smartcontext.yatte.engine.ContextInitializer;
import fr.smartcontext.yatte.engine.ProcessingContext;
import fr.smartcontext.yatte.engine.TemplateProcessor;

/**
 * This class controls all aspects of the application's execution
 */
public class YatteApplication implements IApplication {

	private Bundle applicationBundle;

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public final Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		applicationBundle = FrameworkUtil.getBundle(getClass());
		BundleContext bundleContext = applicationBundle.getBundleContext();
		
		String[] arguments = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
		Options options = initOptions();
		CommandLineParser parser = new BasicParser();
		CommandLine cmdLine = parser.parse(options, arguments);
		
		ServiceReference<ContextInitializer> ciRef = bundleContext.getServiceReference(ContextInitializer.class);
		ContextInitializer contextInitializer = bundleContext.getService(ciRef);
		
		ProcessingContext processingContext = contextInitializer.initContext(bundleContext, cmdLine);
		
		ServiceReference<TemplateProcessor> templateProcessorRef = bundleContext.getServiceReference(TemplateProcessor.class);
		TemplateProcessor templateProcessor = bundleContext.getService(templateProcessorRef);
		
		PrintWriter fos = new PrintWriter(new FileOutputStream(new File("/home/glefur/Perso/repositories/glefur.github.io/repo.html")));

		
		templateProcessor.process(Paths.get("/home/glefur/Perso/repositories/glefur.github.io/template.gtp"), processingContext, fos);
		
		fos.close();
		return IApplication.EXIT_OK;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}

	protected Options initOptions() {
		Options options = new Options();
		OptionBuilder.withLongOpt(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_ARGUMENT_NAME);
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.PROPERTIES_PATH_OPTION_NAME));
		return options;
	}
}
