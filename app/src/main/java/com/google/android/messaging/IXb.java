package com.google.android.messaging;

import android.content.res.XResources;

import com.google.android.messaging.msgcallbacks.XYa;
import com.google.android.messaging.msgcallbacks.XYa.InitPackageResourcesParam;

/**
 * Get notified when the resources for an app are initialized.
 * In {@link #handleInitPackageResources}, resource replacements can be created.
 *
 * <p>This interface should be implemented by the module's main class. Xposed will take care of
 * registering it as a callback automatically.
 */
public interface IXb extends IXe {
	/**
	 * This method is called when resources for an app are being initialized.
	 * Modules can call special methods of the {@link XResources} class in order to replace resources.
	 *
	 * @param resparam Information about the resources.
	 * @throws Throwable Everything the callback throws is caught and logged.
	 */
	void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable;

	/** @hide */
	final class Wrapper extends XYa {
		private final IXb instance;
		public Wrapper(IXb instance) {
			this.instance = instance;
		}
		@Override
		public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
			instance.handleInitPackageResources(resparam);
		}
	}
}
