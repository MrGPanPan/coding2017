package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.IOException;

public class ConnectionImpl implements Connection{

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		return null;
	}

	@Override
	public int getContentLength() {

		return 0;
	}

	@Override
	public void close() {


	}

}