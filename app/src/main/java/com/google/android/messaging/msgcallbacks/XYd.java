package com.google.android.messaging.msgcallbacks;

import android.content.pm.ApplicationInfo;

import com.google.android.messaging.IXc;
import com.google.android.messaging.XB.CopyOnWriteSortedSet;

/**
 * This class is only used for internal purposes, except for the {@link LoadPackageParam}
 * subclass.
 */
public abstract class XYd extends XYc implements IXc {
	/**
	 * Creates a new callback with default priority.
	 * @hide
	 */
	@SuppressWarnings("deprecation")
	public XYd() {
		super();
	}

	/**
	 * Creates a new callback with a specific priority.
	 *
	 * @param priority See {@link XYc#priority}.
	 * @hide
	 */
	public XYd(int priority) {
		super(priority);
	}

	/**
	 * Wraps information about the app being loaded.
	 */
	public static final class LoadPackageParam extends XYc.Param {
		/** @hide */
		public LoadPackageParam(CopyOnWriteSortedSet<XYd> callbacks) {
			super(callbacks);
		}

		/** The name of the package being loaded. */
		public String packageName;

		/** The process in which the package is executed. */
		public String processName;

		/** The ClassLoader used for this package. */
		public ClassLoader classLoader;

		/** More information about the application being loaded. */
		public ApplicationInfo appInfo;

		/** Set to {@code true} if this is the first (and main) application for this process. */
		public boolean isFirstApplication;
	}

	/** @hide */
	@Override
	protected void call(Param param) throws Throwable {
		if (param instanceof LoadPackageParam)
			handleLoadPackage((LoadPackageParam) param);
	}
}
