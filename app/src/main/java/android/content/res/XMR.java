package android.content.res;

import android.app.AA;
import android.util.DisplayMetrics;

import com.google.android.messaging.IXb;
import com.google.android.messaging.IXd;
import com.google.android.messaging.IXd.StartupParam;
import com.google.android.messaging.msgcallbacks.XYa.InitPackageResourcesParam;

/**
 * Provides access to resources from a certain path (usually the module's own path).
 */
public class XMR extends Resources {
	private XMR(AssetManager assets, DisplayMetrics metrics, Configuration config) {
		super(assets, metrics, config);
	}

	/**
	 * Creates a new instance.
	 *
	 * <p>This is usually called with {@link StartupParam#modulePath} from
	 * {@link IXd#initZygote} and {@link InitPackageResourcesParam#res} from
	 * {@link IXb#handleInitPackageResources} (or {@code null} for
	 * system-wide replacements).
	 *
	 * @param path The path to the APK from which the resources should be loaded.
	 * @param origRes The resources object from which settings like the display metrics and the
	 *                configuration should be copied. May be {@code null}.
	 */
	public static XMR createInstance(String path, XR origRes) {
		if (path == null)
			throw new IllegalArgumentException("path must not be null");

		AssetManager assets = new AssetManager();
		assets.addAssetPath(path);

		XMR res;
		if (origRes != null)
			res = new XMR(assets, origRes.getDisplayMetrics(),	origRes.getConfiguration());
		else
			res = new XMR(assets, null, null);

		AA.addActiveResource(path, res);
		return res;
	}

	/**
	 * Creates an {@link XRF} instance that forwards requests to {@code id} in this resource.
	 */
	public XRF fwd(int id) {
		return new XRF(this, id);
	}
}
