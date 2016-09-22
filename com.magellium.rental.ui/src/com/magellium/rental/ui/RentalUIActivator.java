package com.magellium.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.magellium.rental.ui.paletteManagement.PaletteDescriptor;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.magellium.rental.ui"; //$NON-NLS-1$

	public static final String IMG_CUSTOMER = "icons/Customers.png";
	public static final String IMG_RENTAL = "icons/Rentals.png";
	public static final String IMG_OBJECT = "icons/RentalObjects.png";
	public static final String IMG_AGENCY = "icons/Agency.png";
	public static final String IMG_SEARCH_AGAIN = "icons/search_again.gif"; // issu
																			// du
																			// projet
																			// org.eclipse.search

	private Map<String, PaletteDescriptor> paletteManager = new HashMap<String, PaletteDescriptor>();

	// The shared instance
	private static RentalUIActivator plugin;

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		getExtensionsQuickAccess();

		readPalettes();
	}

	// read extensions and all configuration elements
	// retourne la liste des extensions qui étendent le pt d'extension
	// org.eclipse.ui.views
	private void getExtensionsQuickAccess() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			System.out.println("IConfigurationElement Name : " + e.getNamespaceIdentifier() + " ; "
					+ "IConfigurationElement Value : " + e.getAttribute("name"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());

		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_OBJECT)));
		// reg.put(IMG_AGENCY,
		// ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_SEARCH_AGAIN)));
	}

	private void readPalettes() {

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		
		for (IConfigurationElement e : reg.getConfigurationElementsFor("com.magellium.rental.ui.palette")) {
			System.out.println("IConfigurationElement Name : " + e.getNamespaceIdentifier());

			try {
				
			PaletteDescriptor paletteDescriptor = new PaletteDescriptor();
			paletteDescriptor.setId(e.getAttribute("id"));
			paletteDescriptor.setName(e.getAttribute("name"));
			paletteDescriptor.setColorProvider((IColorProvider) e.createExecutableExtension("paletteClass"));

			getPaletteManager().put(paletteDescriptor.getId(), paletteDescriptor);

			} catch (CoreException e1) {
				getLog().log(new Status(IStatus.ERROR, e.getNamespaceIdentifier(), "Unable to create extension.", e1));
			}

		}

	}

	public Map<String, PaletteDescriptor> getPaletteManager() {
		return paletteManager;
	}
}
