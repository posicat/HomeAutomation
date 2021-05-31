package org.cattech.homeAutomation.communicationHub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NodeSocketConnectionManager implements Runnable {
	private Logger log = LogManager.getLogger(this.getClass());

	protected int serverPort;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	protected ChannelController controller;

	public NodeSocketConnectionManager(ChannelController controller) {
		this.serverPort = controller.getConfig().getPort();
		this.controller = controller;
	}

	@Override
	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while (!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (isStopped()) {
					log.info("Server Stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			new Thread(new NodeInterfaceSocket(clientSocket, controller), "Client " + clientSocket.getInetAddress())
					.start();
		}
		log.info("Server Stopped.");
		this.stop();
	}

	public synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}

	private void openServerSocket() {
		try {
			log.info("Starting hub on port " + this.serverPort);
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + this.serverPort, e);
		}
	}

}