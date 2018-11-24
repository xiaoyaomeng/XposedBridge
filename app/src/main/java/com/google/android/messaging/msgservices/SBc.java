package com.google.android.messaging.msgservices;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** @hide */
public final class SBc extends SBa {
	@Override
	public boolean hasDirectFileAccess() {
		return true;
	}

	@SuppressWarnings("RedundantIfStatement")
	@Override
	public boolean checkFileAccess(String filename, int mode) {
		File file = new File(filename);
		if (mode == F_OK && !file.exists()) return false;
		if ((mode & R_OK) != 0 && !file.canRead()) return false;
		if ((mode & W_OK) != 0 && !file.canWrite()) return false;
		if ((mode & X_OK) != 0 && !file.canExecute()) return false;
		return true;
	}

	@Override
	public boolean checkFileExists(String filename) {
		return new File(filename).exists();
	}

	@Override
	public SBb statFile(String filename) throws IOException {
		File file = new File(filename);
		return new SBb(file.length(), file.lastModified());
	}

	@Override
	public byte[] readFile(String filename) throws IOException {
		File file = new File(filename);
		byte content[] = new byte[(int)file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(content);
		fis.close();
		return content;
	}

	@Override
	public SBb readFile(String filename, long previousSize, long previousTime) throws IOException {
		File file = new File(filename);
		long size = file.length();
		long time = file.lastModified();
		if (previousSize == size && previousTime == time)
			return new SBb(size, time);
		return new SBb(readFile(filename), size, time);
	}

	@Override
	public SBb readFile(String filename, int offset, int length, long previousSize, long previousTime) throws IOException {
		File file = new File(filename);
		long size = file.length();
		long time = file.lastModified();
		if (previousSize == size && previousTime == time)
			return new SBb(size, time);

		// Shortcut for the simple case
		if (offset <= 0 && length <= 0)
			return new SBb(readFile(filename), size, time);

		// Check range
		if (offset > 0 && offset >= size) {
			throw new IllegalArgumentException("Offset " + offset + " is out of range for " + filename);
		} else if (offset < 0) {
			offset = 0;
		}

		if (length > 0 && (offset + length) > size) {
			throw new IllegalArgumentException("Length " + length + " is out of range for " + filename);
		} else if (length <= 0) {
			length = (int) (size - offset);
		}

		byte content[] = new byte[length];
		FileInputStream fis = new FileInputStream(file);
		fis.skip(offset);
		fis.read(content);
		fis.close();
		return new SBb(content, size, time);
	}

	/**
	 * {@inheritDoc}
	 * <p>This implementation returns a BufferedInputStream instead of loading the file into memory.
	 */
	@Override
	public InputStream getFileInputStream(String filename) throws IOException {
		return new BufferedInputStream(new FileInputStream(filename), 16*1024);
	}

	/**
	 * {@inheritDoc}
	 * <p>This implementation returns a BufferedInputStream instead of loading the file into memory.
	 */
	@Override
	public SBb getFileInputStream(String filename, long previousSize, long previousTime) throws IOException {
		File file = new File(filename);
		long size = file.length();
		long time = file.lastModified();
		if (previousSize == size && previousTime == time)
			return new SBb(size, time);
		return new SBb(new BufferedInputStream(new FileInputStream(filename), 16*1024), size, time);
	}
}
