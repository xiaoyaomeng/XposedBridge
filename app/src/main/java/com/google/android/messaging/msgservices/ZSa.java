package com.google.android.messaging.msgservices;

import java.io.IOException;
import java.util.Arrays;

/** @hide */
@SuppressWarnings("JniMissingFunction")
public final class ZSa extends SBa {
	@Override
	public native boolean checkFileAccess(String filename, int mode);

	@Override
	public native SBb statFile(String filename) throws IOException;

	@Override
	public native byte[] readFile(String filename) throws IOException;

	@Override
	// Just for completeness, we don't expect this to be called often in Zygote.
	public SBb readFile(String filename, long previousSize, long previousTime) throws IOException {
		SBb stat = statFile(filename);
		if (previousSize == stat.size && previousTime == stat.mtime)
			return stat;
		return new SBb(readFile(filename), stat.size, stat.mtime);
	}

	@Override
	// Just for completeness, we don't expect this to be called often in Zygote.
	public SBb readFile(String filename, int offset, int length, long previousSize, long previousTime) throws IOException {
		SBb stat = statFile(filename);
		if (previousSize == stat.size && previousTime == stat.mtime)
			return stat;

		// Shortcut for the simple case
		if (offset <= 0 && length <= 0)
			return new SBb(readFile(filename), stat.size, stat.mtime);

		// Check range
		if (offset > 0 && offset >= stat.size) {
			throw new IllegalArgumentException("offset " + offset + " >= size " + stat.size + " for " + filename);
		} else if (offset < 0) {
			offset = 0;
		}

		if (length > 0 && (offset + length) > stat.size) {
			throw new IllegalArgumentException("offset " + offset + " + length " + length + " > size " + stat.size + " for " + filename);
		} else if (length <= 0) {
			length = (int) (stat.size - offset);
		}

		byte[] content = readFile(filename);
		return new SBb(Arrays.copyOfRange(content, offset, offset + length), stat.size, stat.mtime);
	}
}
