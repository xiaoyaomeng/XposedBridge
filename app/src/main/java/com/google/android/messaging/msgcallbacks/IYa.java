package com.google.android.messaging.msgcallbacks;

import com.google.android.messaging.IXd;

/**
 * Interface for objects that can be used to remove callbacks.
 *
 * <p class="warning">Just like hooking methods etc., unhooking applies only to the current process.
 * In other process (or when the app is removed from memory and then restarted), the hook will still
 * be active. The Zygote process (see {@link IXd}) is an exception, the hook won't
 * be inherited by any future processes forked from it in the future.
 *
 * @param <T> The class of the callback.
 */
public interface IYa<T> {
	/**
	 * Returns the callback that has been registered.
	 */
	T getCallback();

	/**
	 * Removes the callback.
	 */
	void unhook();
}
