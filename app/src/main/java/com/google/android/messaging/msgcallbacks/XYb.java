package com.google.android.messaging.msgcallbacks;

import android.content.res.XResources;
import android.content.res.XResources.ResourceNames;
import android.view.View;

import com.google.android.messaging.XB.CopyOnWriteSortedSet;

/**
 * Callback for hooking layouts. Such callbacks can be passed to {@link XResources#hookLayout}
 * and its variants.
 */
public abstract class XYb extends XYc {
	/**
	 * Creates a new callback with default priority.
	 */
	@SuppressWarnings("deprecation")
	public XYb() {
		super();
	}

	/**
	 * Creates a new callback with a specific priority.
	 *
	 * @param priority See {@link XYc#priority}.
	 */
	public XYb(int priority) {
		super(priority);
	}

	/**
	 * Wraps information about the inflated layout.
	 */
	public static final class LayoutInflatedParam extends XYc.Param {
		/** @hide */
		public LayoutInflatedParam(CopyOnWriteSortedSet<XYb> callbacks) {
			super(callbacks);
		}

		/** The view that has been created from the layout. */
		public View view;

		/** Container with the ID and name of the underlying resource. */
		public ResourceNames resNames;

		/** Directory from which the layout was actually loaded (e.g. "layout-sw600dp"). */
		public String variant;

		/** Resources containing the layout. */
		public XResources res;
	}

	/** @hide */
	@Override
	protected void call(Param param) throws Throwable {
		if (param instanceof LayoutInflatedParam)
			handleLayoutInflated((LayoutInflatedParam) param);
	}

	/**
	 * This method is called when the hooked layout has been inflated.
	 *
	 * @param liparam Information about the layout and the inflated view.
	 * @throws Throwable Everything the callback throws is caught and logged.
	 */
	public abstract void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable;

	/**
	 * An object with which the callback can be removed.
	 */
	public class Unhook implements IYa<XYb> {
		private final String resDir;
		private final int id;

		/** @hide */
		public Unhook(String resDir, int id) {
			this.resDir = resDir;
			this.id = id;
		}

		/**
		 * Returns the resource ID of the hooked layout.
		 */
		public int getId() {
			return id;
		}

		@Override
		public XYb getCallback() {
			return XYb.this;
		}

		@Override
		public void unhook() {
			XResources.unhookLayout(resDir, id, XYb.this);
		}

	}
}
