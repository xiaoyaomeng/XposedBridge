package com.google.android.messaging.msgcallbacks;

import android.content.res.XR;

import com.google.android.messaging.IXb;
import com.google.android.messaging.XB.CopyOnWriteSortedSet;

/**
 * This class is only used for internal purposes, except for the {@link InitPackageResourcesParam}
 * subclass.
 */
public abstract class XYa extends XYc implements IXb {
	/**
	 * Creates a new callback with default priority.
	 * @hide
	 */
	@SuppressWarnings("deprecation")
	public XYa() {
		super();
	}

	/**
	 * Creates a new callback with a specific priority.
	 *
	 * @param priority See {@link XYc#priority}.
	 * @hide
	 */
	public XYa(int priority) {
		super(priority);
	}

	/**
	 * Wraps information about the resources being initialized.
	 */
	public static final class InitPackageResourcesParam extends XYc.Param {
		/** @hide */
		public InitPackageResourcesParam(CopyOnWriteSortedSet<XYa> callbacks) {
			super(callbacks);
		}

		/** The name of the package for which resources are being loaded. */
		public String packageName;

		/**
		 * Reference to the resources that can be used for calls to
		 * {@link XR#setReplacement(String, String, String, Object)}.
		 */
		public XR res;
	}

	/** @hide */
	@Override
	protected void call(Param param) throws Throwable {
		if (param instanceof InitPackageResourcesParam)
			handleInitPackageResources((InitPackageResourcesParam) param);
	}
}
