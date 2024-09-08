package org.example.operation.custom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWebApplicationServer {

	private final int port;

	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

	public CustomWebApplicationServer(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			logger.info("[CustomWebApplicationServer] started {} port.", port);

			Socket clientSocket;
			logger.info("[CustomWebApplicationServer] Waiting for client connection...");

			while ((clientSocket = serverSocket.accept()) != null) {
				logger.info("[CustomWebApplicationServer] Accepted client connection.");

				// 사용자 요청이 올때마다 새로운 Thread 생성 -> 매번 요청할 때마다 생성되면 쓰레드가 많아져서 CPU 사용량 증가, 메모리 증가
				// 서버가 다운 될수도 있다
				// 쓰레드 풀 적용해서 안정적인 서비스 만들기
				executorService.execute(new ClientRequestHandler(clientSocket));
			}
		}
	}
}
