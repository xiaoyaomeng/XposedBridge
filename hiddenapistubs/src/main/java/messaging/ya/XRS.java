package messaging.ya;

import android.content.res.Resources;

/**
 * This class is used as super class of XResources.
 *
 * This implementation isn't included in the .dex file. Instead, it's created on the device.
 * Usually, it will extend Resources, but some ROMs use their own Resources subclass.
 * In that case, XRS will extend the ROM's subclass in an attempt to increase
 * compatibility.
 */
public class XRS extends Resources {
	/** Dummy, will never be called (objects are transferred to this class only). */
	protected XRS() {
		super(null, null, null);
		throw new UnsupportedOperationException();
	}
}
