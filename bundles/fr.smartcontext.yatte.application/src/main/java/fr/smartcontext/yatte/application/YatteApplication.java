package fr.smartcontext.yatte.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import fr.smartcontext.yatte.application.context.PropertiesContextInitializer;
import fr.smartcontext.yatte.engine.TemplateProcessor;
import fr.smartcontext.yatte.engine.context.ContextInitializer;
import fr.smartcontext.yatte.engine.context.ProcessingContext;

/**
 * This class controls all aspects of the application's execution
 */
public class YatteApplication implements IApplication {

	private Bundle applicationBundle;

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public final Object start(IApplicationContext context) {
		Options options = null;
		try {
			context.applicationRunning();
			applicationBundle = FrameworkUtil.getBundle(getClass());
			BundleContext bundleContext = applicationBundle.getBundleContext();

			String[] arguments = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
			options = initOptions();
			CommandLineParser parser = new BasicParser();
			CommandLine cmdLine = parser.parse(options, arguments);

			ContextInitializer contextInitializer;

			ServiceReference<ContextInitializer> ciRef = bundleContext.getServiceReference(ContextInitializer.class);
			if (ciRef != null) {
				contextInitializer = bundleContext.getService(ciRef);
			} else {
				contextInitializer = new PropertiesContextInitializer();
			}

			ProcessingContext processingContext = contextInitializer.initContext(bundleContext, cmdLine);

			ServiceReference<TemplateProcessor> templateProcessorRef = bundleContext.getServiceReference(TemplateProcessor.class);
			TemplateProcessor templateProcessor = bundleContext.getService(templateProcessorRef);

			PrintWriter fos = new PrintWriter(new FileOutputStream(new File(cmdLine.getOptionValue(ApplicationParametersConstants.OUTPUT_OPTION_NAME))));

			templateProcessor.process(Paths.get(cmdLine.getOptionValue(ApplicationParametersConstants.TEMPLATE_OPTION_NAME)), processingContext, fos);

			fos.close();
		} catch (Exception ex) {
			System.err.println("Unable to launch the publisher.");
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "p2repoPublisher", options );
		}
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

		OptionBuilder.withLongOpt(ApplicationParametersConstants.OUTPUT_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.OUTPUT_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.OUTPUT_OPTION_ARGUMENT_NAME);
		OptionBuilder.isRequired();
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.OUTPUT_OPTION_NAME));

		OptionBuilder.withLongOpt(ApplicationParametersConstants.TEMPLATE_OPTION_LONG_NAME);
		OptionBuilder.withDescription(ApplicationParametersConstants.TEMPLATE_OPTION_DESCRIPTION);
		OptionBuilder.hasArg();
		OptionBuilder.withArgName(ApplicationParametersConstants.TEMPLATE_OPTION_ARGUMENT_NAME);
		OptionBuilder.isRequired();
		options.addOption(OptionBuilder.create(ApplicationParametersConstants.TEMPLATE_OPTION_NAME));

		return options;
	}
}
